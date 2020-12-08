package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/userServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    /**
     * 这是登录操作
     */
    protected void login(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {

        //  1 获取请求的参数 用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //  2 调用UserService.login():User; 登录业务
        User loginUser = userService.login(new User(null, username, password, null));
        //  3 根据login()方法的返回值。决定是否登录成功
        if (loginUser == null) {
            //   失败
            System.out.println("登录失败");
            // 保存页面需要显示的信息
            request.setAttribute("msg","用户名或密码错误，登录失败");
            request.setAttribute("username",username);

            //   跳回 login.jsp页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        } else {
            //   成功
            System.out.println("登录成功");
            //  跳到 login_success.jsp页面
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }

    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        1 获取请求的参数（封装为User对象）
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");


        User user = WebUtils.copyParamToBean(new User(),request.getParameterMap());



//        2 检查验证码是否正确 abcde
        if ("abcde".equalsIgnoreCase(code)) {
            //                正确
            //        3 检查用户名是否可用。
            if (userService.existsUsername(username)) {
                //                不可用
                System.out.println("用户名[" + username + "]不可用");

                request.setAttribute("msg","用户名不可用");
                request.setAttribute("username",username);
                request.setAttribute("email",email);

                //        跳回regist.jsp页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            } else {
                //        4 调用XxxService.registUser();处理业务
                userService.registUser(new User(null,username,password,email));
                //        5 跳到注册成功页面
                //        regist_success.jsp
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        } else {
            //                不正确
            System.out.println("验证码[" + code + "]不正确");

            request.setAttribute("msg","验证码不正确");
            request.setAttribute("username",username);
            request.setAttribute("email",email);

            //        跳回regist.jsp页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }




}
