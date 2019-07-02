package com.study.boot.mybatis.design;

/**
 * @author Xingyu Sun
 * @date 2019/2/13 10:40
 */
public abstract class BaseItem implements Alerm {

    protected String name;

    public BaseItem(String name) {
        this.name = name;
    }

    @Override
    public void alert() {
        System.out.println("base alert " + name);
    }
}
