package com.study.boot.mybatis.aop;

import com.alibaba.fastjson.JSON;
import com.study.boot.mybatis.annotation.SystemLog;
import com.study.boot.mybatis.annotation.domain.LogMessage;
import com.study.boot.mybatis.constants.BaseConstants;
import com.study.boot.mybatis.dao.LogMessageMapper;
import com.study.boot.mybatis.enums.LogScopeEnum;
import com.study.boot.mybatis.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author Xingyu Sun
 * @date 2019/1/9 14:23
 */
@Aspect
@Component
public class WebLogAspect {

    @Autowired
    private LogMessageMapper logMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    public WebLogAspect() {
        LOGGER.info("aspect instance...");
    }

    /**
     * 开始时间
     */
    private long startTime = 0L;

    /**
     * Controller层切点
     */
    @Pointcut("execution(* *..controller..*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String s = Arrays.toString(args);
        LOGGER.info("before ...args " + s);
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        Assert.notNull(servletRequestAttributes,"servletRequestAttributes can not be null");
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //目标方法实体
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        boolean annotationPresent = method.isAnnotationPresent(SystemLog.class);
        //没加注解 直接执行返回结果
        if (!annotationPresent) {
            return point.proceed();
        }

        //日志打印外部开关默认关闭
        String logSwitch = StringUtils.equals(RedisUtil.get(BaseConstants.CACHE_WEB_LOG_SWITCH), BaseConstants.YES) ? BaseConstants.YES : BaseConstants.NO;

        //记录日志信息
        LogMessage logMessage = new LogMessage();

        //方法注解实体
        SystemLog systemLog = method.getAnnotation(SystemLog.class);

        //处理入参日志
        handleRequestLog(point, systemLog, request, logMessage, logSwitch);

        //执行目标方法内容，获取执行结果
        Object result = point.proceed();

        //处理接口响应日志
        handleResponseLog(logSwitch, logMessage, systemLog, result);
        return result;
    }

    /**
     * 处理入参日志
     *
     * @param point      &emsp;切点
     * @param systemLog  &emsp;日志注解
     * @param logMessage &emsp;日志信息记录实体
     */
    private void handleRequestLog(ProceedingJoinPoint point, SystemLog systemLog, HttpServletRequest request,
                                  LogMessage logMessage, String logSwitch) throws Exception {
        LOGGER.info("request info ..." + request.getQueryString());
        String paramsText = "";
        //参数列表
        String includeParam = systemLog.include();
        Map<String, Object> methodParamNames = getMethodParamNames(
                point.getTarget().getClass(), point.getSignature().getName(), includeParam);
        Map<String, Object> params = getArgsMap(point, methodParamNames);
        if (params != null) {
            //序列化参数列表
            paramsText = JSON.toJSONString(params);
        }
        logMessage.setParameter(paramsText);
        //判断是否输出日志
        if (systemLog.enable()
                && systemLog.scope().contains(LogScopeEnum.BEFORE)
                && systemLog.console()
                && StringUtils.equals(logSwitch, BaseConstants.YES)) {
            //打印入参日志
            LOGGER.info("【{}】 接口入参成功!, 方法名称:【{}】, 请求参数:【{}】", systemLog.desc(), point.getSignature().getName(), paramsText);
        }
        startTime = System.currentTimeMillis();
        //接口描述
        logMessage.setDescription(systemLog.desc());

        //...省略部分构造logMessage信息代码
    }

    /**
     * 处理响应日志
     *
     * @param logSwitch  外部日志开关，用于外部动态开启日志打印
     * @param logMessage 日志记录信息实体
     * @param systemLog  日志注解实体
     * @param result     &emsp; 接口执行结果
     */
    private void handleResponseLog(String logSwitch, LogMessage logMessage, SystemLog systemLog, Object result) {
        /*
         * 结束时间
         */
        long endTime = System.currentTimeMillis();
        //结束时间
        logMessage.setEndTime(new Date());
        //消耗时间
        logMessage.setSpendTime(endTime - startTime);
        logMessage.setResult(JSON.toJSONString(result));
        //是否输出日志
        if (systemLog.enable()
                && systemLog.scope().contains(LogScopeEnum.AFTER)) {
            //判断是否入库
            if (systemLog.db()) {
                //...省略入库代码
                LOGGER.info("insert db " + result);
                logMapper.insert(logMessage);
                LOGGER.info("logMessage to db success ..." + logMessage);
            }
            //判断是否输出到控制台
            if (systemLog.console()
                    && StringUtils.equals(logSwitch, BaseConstants.YES)) {
                //...省略打印日志代码
                LOGGER.info("console print ");
            }
        }
    }

    /**
     * 获取方法入参变量名
     *
     * @param cls        触发的类
     * @param methodName 触发的方法名
     * @param include    需要打印的变量名
     * @return map
     * @throws Exception e
     */
    private Map<String, Object> getMethodParamNames(Class cls, String methodName, String include) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(cls));
        CtMethod cm = pool.get(cls.getName()).getDeclaredMethod(methodName);
        LocalVariableAttribute attr = (LocalVariableAttribute) cm
                .getMethodInfo().getCodeAttribute()
                .getAttribute(LocalVariableAttribute.tag);

        if (attr == null) {
            throw new Exception("attr is null");
        } else {
            Map<String, Object> paramNames = new HashMap<>(16);
            int paramNamesLen = cm.getParameterTypes().length;
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            if (StringUtils.isEmpty(include)) {
                for (int i = 0; i < paramNamesLen; i++) {
                    paramNames.put(attr.variableName(i + pos), i);
                }
            } else { // 若include不为空
                for (int i = 0; i < paramNamesLen; i++) {
                    String paramName = attr.variableName(i + pos);
                    if (include.contains(paramName)) {
                        paramNames.put(paramName, i);
                    }
                }
            }
            return paramNames;
        }
    }

    /**
     * 组装入参Map
     *
     * @param point&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;切点
     * @param methodParamNames&emsp;参数名称集合
     * @return map
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getArgsMap(ProceedingJoinPoint point, Map<String, Object> methodParamNames) {
        Object[] args = point.getArgs();
        if (null == methodParamNames) {
            return Collections.EMPTY_MAP;
        }
        for (Map.Entry<String, Object> entry : methodParamNames.entrySet()) {
            int index = Integer.valueOf(String.valueOf(entry.getValue()));
            if (args != null && args.length > 0) {
                Object arg = (null == args[index] ? "" : args[index]);
                methodParamNames.put(entry.getKey(), arg);
            }
        }
        return methodParamNames;
    }

}
