package com.aaron.MyBatisTest.entity;

import lombok.Data;

import java.util.List;

@Data
public class Goods {
    private int id;
    private String name;
    private List<Customer> customers;

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }
}
