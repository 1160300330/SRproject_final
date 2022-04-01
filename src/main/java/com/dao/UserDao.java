package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User getUserById(@Param(value ="id") String id);
    void updateCountById(@Param(value ="id") String id,@Param(value ="count")double count);
    void createUser(@Param(value = "id")String id, @Param(value = "password")String password,@Param(value = "name")String name);
}