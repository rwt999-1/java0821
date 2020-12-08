package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        User admin = userDao.queryUserByUsername("admin1234");
        if (admin == null) {
            System.out.println("用户名可用！！！");
        } else {
            System.out.println("用户名不可用");
        }
    }

    @Test
    public void saveUser() {
        userDao.saveUser(new User(null,"wzg168","666666","wzg168@qq.com"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("wzg168","666666") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("登录成功");
        }
    }
}