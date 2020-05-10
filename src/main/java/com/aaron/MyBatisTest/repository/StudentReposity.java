package com.aaron.MyBatisTest.repository;

import com.aaron.MyBatisTest.entity.Student;

public interface StudentReposity {
    Student findStudentById(int id);
    Student findStudentByIdLazy(int id);
}
