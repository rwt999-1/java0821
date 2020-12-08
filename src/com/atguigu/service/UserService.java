package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {

    //注册用户
    public void registUser(User user);


    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return 返回true表示用户名已存在，不可用 <br/>
     * false 表示用户可用
     */
    public boolean existsUsername(String username);

    /**
     * 用户登录
     *
     * @param user
     * @return 返回有值，说明登录成功 <br/> 返回null,说明登录失败！！！
     */
    public User login(User user);

}
