package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    /**
     * 一次性把map中的数据都注入到Bean属性值
     */
    public static <T> T copyParamToBean(T bean, Map params){
        try {
            /**
             * populate()方法把请求的参数map对象，一次性注入到指定的Bean对象属性中<br/>
             *  第一个参数是需要赋值的Bean对象 <br/>
             *  第二个参数，是由请求参数封装成为的Map对象<br/>
             */
            BeanUtils.populate(bean,params);
            System.out.println("注入值之后 => " + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Integer parseInt(String intStr,Integer defaultValue){
        try {
            return Integer.parseInt(intStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
