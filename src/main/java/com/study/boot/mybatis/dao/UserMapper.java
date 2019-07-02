package com.study.boot.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.boot.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xingyu Sun
 * @date 2018/12/20 21:28
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * findALl
     *
     * @return l
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * findById
     * @param id i
     * @return u
     */
    User findById(@Param("id") Long id);
}
