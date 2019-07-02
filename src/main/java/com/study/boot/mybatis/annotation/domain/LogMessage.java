package com.study.boot.mybatis.annotation.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.study.boot.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xingyu Sun
 * @date 2019/1/9 14:54
 */
@Getter
@Setter
@ToString
@TableName("log_message")
public class LogMessage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3745110660390215491L;
    private String parameter;

    private String description;

    @TableField(value = "end_time")
    private Date endTime;
    @TableField(value = "spend_time")
    private Long spendTime;

    private String result;
}
