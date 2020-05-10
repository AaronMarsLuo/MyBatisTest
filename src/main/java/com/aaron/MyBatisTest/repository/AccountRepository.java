package com.aaron.MyBatisTest.repository;

import com.aaron.MyBatisTest.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountRepository {
    public int insert(Account account);
    public int update(Account account);
    public int deleteById(int id);
    public List<Account> findAll();
    public Account findById(int id);
    public Account findByName(String name);
    public List<Account> findByNameAndAge(@Param("name") String name,@Param("age") int age);
    public Account findDynamically(Account account);
    public Account findTrimly(Account account);
    public List<Account> findByIds(List<Integer> ids);
}
