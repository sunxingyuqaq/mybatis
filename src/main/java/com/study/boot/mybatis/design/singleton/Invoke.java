package com.study.boot.mybatis.design.singleton;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 11:36
 */
public class Invoke implements InvocationHandler {

    private Object target;

    public Invoke(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }
}
