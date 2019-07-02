package com.study.boot.mybatis.type;

import com.study.boot.mybatis.entity.BaseEntity;

/**
 * @author Xingyu Sun
 * @date 2019/4/8 14:16
 */
public class Animal<K extends BaseEntity> {
    private K body;

    public K getBody() {
        return body;
    }

    public void setBody(K body) {
        this.body = body;
    }
}
