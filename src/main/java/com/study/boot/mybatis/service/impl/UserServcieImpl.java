package com.study.boot.mybatis.service.impl;

import com.study.boot.mybatis.dao.UserMapper;
import com.study.boot.mybatis.entity.User;
import com.study.boot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Xingyu Sun
 * @date 2018/12/21 10:51
 */
@Service
public class UserServcieImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * list
     *
     * @return
     */
    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeAge(User first, User second) {
        System.out.println("----------");
        System.out.println("old-" + first);
        System.out.println("----------");
        first.setAge(26);
        userMapper.updateById(first);
        System.out.println("----------");
        System.out.println("new-" + first);
        System.out.println("----------");
        second.setAge(26);
        userMapper.updateById(second);
    }
}
