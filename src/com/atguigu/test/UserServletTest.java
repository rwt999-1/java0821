package com.atguigu.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServletTest {

    public void login(){
        System.out.println(" login() 方法 ");
    }
    public void regist(){
        System.out.println(" regist() 方法 ");
    }

    public void updatePassword(){
        System.out.println(" updatePassword() 方法 ");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String action = "login";
        /**
         * getDeclaredMethod() 获取方法的反射对象
         *  第一个参数是方法名<br>
         *  后面的参数是方法的参数类型列表
         */
        Method method = UserServletTest.class.getDeclaredMethod(action);
//        System.out.println(method);

        UserServletTest userServletTest = new UserServletTest();

        /**
         * invoke()方法用于调用反射方法实例<br/>
         *  第一个参数是，方法所属的实例对象<br/>
         *  后面的参数是，方法所需要的实参<br/>
         */
        method.invoke(userServletTest);
    }

}
