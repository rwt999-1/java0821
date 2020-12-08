package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"bbj168","123456","bbj168@qq.com"));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("bbj1688")) {
            System.out.println(" 已存在，不可用 ");
        } else {
            System.out.println(" 用户名，可用！ ");
        }
    }

    @Test
    public void login() {
        if (userService.login(new User(null,"admin5","admin",null)) == null) {
            System.out.println("登录失败");
        } else {
            System.out.println("登录成功！");
        }
    }
}