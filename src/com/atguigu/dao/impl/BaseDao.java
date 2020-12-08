package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public abstract class BaseDao {

    /**
     * QueryRunner是 DbUtils 包中提供的一个专门用来执行sql语句的工具类<br/>
     *  int update() 用于执行insert,delete,update写操作的sql语句<br/>
     *  query() 用于执行select查询操作 <br/>
     *      查询返回值分为三种情况处理<br/>
     *      分别是：
     *          返回一个JavaBean对象 <br/>
     *          返回多个JavaBean对象 <br/>
     *          返回一行单列的数据 <br/>
     *  在DBUtils包中，有一个接口，叫 ResultSetHandler 接口，它负责将查询回来的结果。转换为我们需要的数据<br/>
     *          返回一个JavaBean对象      BeanHandler<T>(type) <br/>
     *          返回多个JavaBean对象      BeanListHandler<T>(type) <br/>
     *          返回一行单列的数据         ScalarHandler()   <br/>
     */
    QueryRunner queryRunner = new QueryRunner();


    /**
     * 执行返回一行单列的数据的sql语句
     *
     * @param sql  sql 语句
     * @param args sql 占位符对应的参数值
     * @return 返回的数据
     */
    protected Object queryList( String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("当前执行sql语句[" + sql + "] ， 参数值是："
                    + Arrays.asList(args) + " . 发生了错误！！！", e);
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * 执行返回多个JavaBean对象sql语句
     *
     * @param type 返回的数据类型
     * @param sql  sql 语句
     * @param args sql 占位符对应的参数值
     * @param <T>  泛型类型
     * @return 返回的数据
     */
    protected <T> List<T> queryList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("当前执行sql语句[" + sql + "] ， 参数值是："
                    + Arrays.asList(args) + " . 发生了错误！！！", e);
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * 执行返回一个JavaBean对象sql语句
     *
     * @param type 返回的数据类型
     * @param sql  sql 语句
     * @param args sql 占位符对应的参数值
     * @param <T>  泛型类型
     * @return 返回的数据
     */
    protected <T> T queryOne(Class<T> type, String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("当前执行sql语句[" + sql + "] ， 参数值是："
                    + Arrays.asList(args) + " . 发生了错误！！！", e);
        } finally {
            JdbcUtils.closeConnection(conn);
        }
    }

    /**
     * 用于执行insert,delete,update写操作的sql语句
     *
     * @param sql  要执行的sql语句
     * @param args sql语句中对应的?占位符的参数值
     * @return 返回sql语句影响的行数
     */
    protected int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("当前执行sql语句[" + sql + "] ， 参数值是："
                    + Arrays.asList(args) + " . 发生了错误！！！",e);
        } finally {
            JdbcUtils.closeConnection(connection);
        }
    }

}
