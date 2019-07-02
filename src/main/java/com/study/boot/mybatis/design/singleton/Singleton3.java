package com.study.boot.mybatis.design.singleton;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 11:07
 */
public class Singleton3 {

    private static class Holder{
        private static final Singleton3 SINGLETON_3 = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return Holder.SINGLETON_3;
    }
}
