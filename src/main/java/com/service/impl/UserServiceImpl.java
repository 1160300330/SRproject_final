package com.service.impl;

import com.dao.UserDao;
import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public User queryUserById(String id){
        return userDao.getUserById(id);
    }
    public boolean updateCountById(String id,double money){
        User user = userDao.getUserById(id);
        if(user!=null){
//            double count = user.getCount();
//            count = money;
            userDao.updateCountById(id, money);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean createUser(User user){
        User user2 = userDao.getUserById(user.getId());
        if(user2==null){
            userDao.createUser(user.getId(),user.getPassword(),user.getName());
            return true;
        }
        else{
            return false;
        }
    }
}
