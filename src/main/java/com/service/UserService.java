package com.service;

import com.pojo.User;

public interface UserService {
    User queryUserById(String id);
    boolean updateCountById(String id, double money);
    boolean createUser(User user);
}
