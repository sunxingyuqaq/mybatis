package com.study.boot.mybatis.enums;

/**
 * @author Xingyu Sun
 * @date 2019/1/9 14:04
 */
public enum LogScopeEnum {
    /**
     * all
     */
    ALL,
    /**
     * before
     */
    BEFORE,
    /**
     * after
     */
    AFTER;

    public boolean contains(LogScopeEnum scope) {
        if (this == ALL) {
            return true;
        } else {
            return this == scope;
        }
    }

    @Override
    public String toString() {
        String str = "";
        switch (this) {
            case ALL:
                str = "ALL";
                break;
            case BEFORE:
                str = "REQUEST";
                break;
            case AFTER:
                str = "RESPONSE";
                break;
            default:
                break;
        }
        return str;
    }

}
