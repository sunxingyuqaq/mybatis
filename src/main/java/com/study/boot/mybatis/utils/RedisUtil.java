package com.study.boot.mybatis.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Xingyu Sun
 * @date 2019/1/9 15:07
 */
public class RedisUtil {

    public static String get(String key){
        if(StringUtils.isNotBlank(key)){
            return "yes";
        }
        return "no";
    }
}
