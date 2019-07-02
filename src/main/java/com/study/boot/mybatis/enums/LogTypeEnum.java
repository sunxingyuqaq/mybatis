package com.study.boot.mybatis.enums;

/**
 * @author Xingyu Sun
 * @date 2019/1/9 14:01
 */
public enum  LogTypeEnum {

    /**
     * web
     */
    WEB("-1"),
    /**
     * dubbo
     */
    DUBBO("1"),
    /**
     * mq
     */
    MQ("2");
    private String value;

    LogTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
