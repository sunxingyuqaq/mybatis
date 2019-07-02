package com.study.boot.mybatis.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingyu Sun
 * @date 2018/12/24 11:08
 */
@Data
@ApiModel(value="user对象",description="用户对象user")
public class UserModel implements Serializable {

    private static final long serialVersionUID = -543543272259388950L;

    @ApiModelProperty(value = "主键",name = "id",example = "1L")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    @ApiModelProperty(value = "姓名",name = "name",example = "test")
    private String name;
    @ApiModelProperty(value = "年龄",name = "age",example = "11")
    private Integer age;
}
