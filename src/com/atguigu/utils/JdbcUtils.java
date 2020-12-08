package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    static DruidDataSource dataSource;

    static {
        try {
            // 创建工具类properties，用于加载 jdbc.properties属性信息
            Properties properties = new Properties();

            // 通过类加载器，读取jdbc.properties属性配置文件
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

            // 解析流中的数据，得到属性信息
            properties.load(resourceAsStream);

            //关闭流操作
            resourceAsStream.close();

            // 根据数据库连接信息，创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

//            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 从数据库连接池中获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取连接失败！！");
        }

        return conn;
    }

    /**
     * 关闭连接，放回池中
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
