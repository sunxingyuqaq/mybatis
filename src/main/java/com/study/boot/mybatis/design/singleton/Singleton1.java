package com.study.boot.mybatis.design.singleton;

/**
 * @author Xingyu Sun
 * @date 2019/2/12 14:54
 */
public class Singleton1 {

    private static Singleton1 single = null;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (single == null) {
            synchronized (Singleton1.class) {
                if (single == null) {
                    single = new Singleton1();
                }
            }
        }
        return single;
    }
}
