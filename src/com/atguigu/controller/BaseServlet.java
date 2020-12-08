package com.atguigu.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决post请求的中文乱码
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            // 通过action业务鉴别字符串，得到相应的反射方法
            Method declaredMethod =
                    this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 反射调用目标方法
            declaredMethod.invoke(this , request,response );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
