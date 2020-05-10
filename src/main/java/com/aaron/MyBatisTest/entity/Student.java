package com.aaron.MyBatisTest.entity;

import lombok.Builder;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

@Data
public class Student {
    private int id;
    private String name;
    private Classes classes;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classes=" + classes +
                '}';
    }
}
