package com.study.boot.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Xingyu Sun
 * @date 2018/12/20 21:28
 */
@Getter
@Setter
public class User extends BaseEntity{

    private static final long serialVersionUID = -2748458442952337022L;

    private String name;
    private Integer age;
    private String email;
    private Boolean deleted;
    private Boolean enable;

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", deleted=" + deleted +
                ", enable=" + enable +
                '}';
    }
}
