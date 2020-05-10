package com.aaron.MyBatisTest.repository;

import com.aaron.MyBatisTest.entity.Customer;

public interface CustomerReposity {
    public Customer getGoodsByCustomerId(int id);
}
