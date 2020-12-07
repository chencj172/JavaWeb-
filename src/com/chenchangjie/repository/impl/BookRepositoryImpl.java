package com.chenchangjie.repository.impl;

import com.chenchangjie.entity.Book;
import com.chenchangjie.entity.BookCase;
import com.chenchangjie.repository.BookRepository;
import com.chenchangjie.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> FindAll(int index,int limit) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id limit ?,?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,index);
            statement.setInt(2,limit);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                //把书封装成对象装入集合中
                list.add(new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),
                        new BookCase(resultSet.getInt(9),resultSet.getString(10))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JDBCUtil.Close(connection,statement,resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int count() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select count(*) from book,bookcase where book.bookcaseid = bookcase.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int res = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                res = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                JDBCUtil.Close(connection,statement,resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return res;
    }
}
