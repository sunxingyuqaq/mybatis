package com.study.boot.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.boot.mybatis.annotation.domain.LogMessage;
import org.springframework.stereotype.Repository;

/**
 * @author Xingyu Sun
 * @date 2019/1/10 8:41
 */
@Repository
public interface LogMessageMapper extends BaseMapper<LogMessage> {
}
