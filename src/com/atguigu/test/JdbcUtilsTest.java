package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {

    @Test
    public void test() {
        for (int i = 100; i > 0; i--) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.closeConnection(connection);
        }
    }

}
