package com.aaron.MyBatisTest.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Account   {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
}
