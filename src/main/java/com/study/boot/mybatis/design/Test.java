package com.study.boot.mybatis.design;

/**
 * @author Xingyu Sun
 * @date 2019/2/13 10:42
 */
public class Test {

    public static void main(String[] args) {
        Alerm a = new Item("item");
        a.alert();
        Alerm d = new Door("door");
        d.alert();
    }
}
