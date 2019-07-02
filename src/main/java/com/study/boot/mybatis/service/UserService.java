package com.study.boot.mybatis.service;

import com.study.boot.mybatis.entity.User;

import java.util.List;

/**
 * @author Xingyu Sun
 * @date 2018/12/21 10:49
 */
public interface UserService {

    /**
     * list
     * @return
     */
    List<User> list();

    /**
     * changeAge
     * @param first f
     * @param second s
     */
    void changeAge(User first,User second);
}
