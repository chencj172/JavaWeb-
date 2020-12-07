package com.chenchangjie.repository.impl;

import com.chenchangjie.entity.Admin;
import com.chenchangjie.entity.Reader;
import com.chenchangjie.repository.AdminRepository;
import com.chenchangjie.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        Connection connection = null;
        Admin admin = null;
        connection = JDBCUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from bookadmin where username=? and password=?";
        try {
            //使用工具类DBUtil进行数据的返回
            admin = queryRunner.query(connection,sql,new BeanHandler<>(Admin.class),username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JDBCUtil.Close(connection,null,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return admin;
    }
}
