package com.chenchangjie.repository.impl;

import com.chenchangjie.entity.Reader;
import com.chenchangjie.repository.ReaderRepository;
import com.chenchangjie.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class ReaderRepositoryImpl implements ReaderRepository {
    /**
     * 检测所需要的数据并且进行返回
     * @param username
     * @param password
     * @return
     */
    @Override
    public Reader login(String username, String password) {
        Connection connection = null;
        Reader reader = null;
        connection = JDBCUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from reader where username=? and password=?";
        try {
            //使用工具类DBUtil进行数据的返回
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JDBCUtil.Close(connection,null,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return reader;
    }
}
