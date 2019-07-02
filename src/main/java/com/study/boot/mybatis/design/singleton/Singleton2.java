package com.study.boot.mybatis.design.singleton;

/**
 * @author Xingyu Sun
 * @date 2019/2/12 15:00
 */
public class Singleton2 {
    private static Singleton2 ourInstance = new Singleton2();

    public static Singleton2 getInstance() {
        return ourInstance;
    }

    private Singleton2() {
    }
}
