package com.study.boot.mybatis.design;

/**
 * @author Xingyu Sun
 * @date 2019/2/13 11:10
 */
abstract class BaseDoorItem extends BaseItem {

    public BaseDoorItem(String name) {
        super(name);
    }

    @Override
    public void alert() {
        System.out.println("BaseDoorItem alert " + name);
    }
}
