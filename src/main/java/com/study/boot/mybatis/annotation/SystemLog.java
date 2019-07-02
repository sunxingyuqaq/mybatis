package com.study.boot.mybatis.annotation;

import com.study.boot.mybatis.enums.LogScopeEnum;
import com.study.boot.mybatis.enums.LogTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xingyu Sun
 * @date 2019/1/9 13:59
 */
@Target({ElementType.METHOD}) //注解作用于方法级别
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
public @interface SystemLog {

    /**
     * 是否输出日志
     */
    boolean enable() default true;

    /**
     * 日志信息描述,可以记录该方法的作用等信息。
     */
    String desc() default "";

    /**
     * 日志类型，可能存在多种接口类型都需要记录日志，比如dubbo接口，web接口
     */
    LogTypeEnum type() default LogTypeEnum.WEB;

    /**
     * 日志等级
     */
    String level() default "INFO";

    /**
     * 日志输出范围,用于标记需要记录的日志信息范围，包含入参、返回值等。
     * ALL-入参和出参, BEFORE-入参, AFTER-出参
     */
    LogScopeEnum scope() default LogScopeEnum.ALL;

    /**
     * 入参输出范围，值为入参变量名，多个则逗号分割。不为空时，入参日志仅打印include中的变量
     */
    String include() default "";

    /**
     * 是否存入数据库
     */
    boolean db() default true;

    /**
     * 是否输出到控制台
     */
    boolean console() default true;

}
