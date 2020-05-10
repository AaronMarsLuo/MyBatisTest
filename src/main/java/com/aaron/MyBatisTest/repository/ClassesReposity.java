package com.aaron.MyBatisTest.repository;

import com.aaron.MyBatisTest.entity.Classes;

public interface ClassesReposity {
    public Classes findClassById(int id);
    public Classes findClassByIdLazy(int id);
}
