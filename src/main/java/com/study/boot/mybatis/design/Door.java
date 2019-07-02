package com.study.boot.mybatis.design;

/**
 * @author Xingyu Sun
 * @date 2019/2/13 11:13
 */
public class Door extends BaseDoorItem{

    public Door(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Door{" +
                "name='" + name + '\'' +
                '}';
    }
}
