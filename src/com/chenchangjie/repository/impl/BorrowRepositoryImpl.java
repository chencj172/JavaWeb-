package com.chenchangjie.repository.impl;

import com.chenchangjie.entity.Book;
import com.chenchangjie.entity.Borrow;
import com.chenchangjie.entity.Reader;
import com.chenchangjie.repository.BorrowRepository;
import com.chenchangjie.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {
    @Override
    public void Borrow(Integer bookId, Integer readerId, String borrowTime, String returnTime, Integer adminId, Integer state) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state)" +
                "values(?,?,?,?,0)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,bookId);
            statement.setInt(2,readerId);
            statement.setString(3,borrowTime);
            statement.setString(4,returnTime);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                JDBCUtil.Close(connection,statement,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<Borrow> findBorrowBookByReadId(int readerId,int index,int limit) {
        List<Borrow> list = new ArrayList<>();
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select br.id,b.`name`,b.author,b.publish,r.`name`,r.tel,r.cardid,br.borrowtime,br.returntime,br.state from borrow br,reader r,book b " +
                "where br.bookid=b.id and br.readerid=r.id and br.readerid=? limit ?,?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,readerId);
            statement.setInt(2,index);
            statement.setInt(3,limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Borrow borrow = new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)),
                        resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10));
                list.add(borrow);
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
    public int countBorrow(int readerId) {
        int res = 0;
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select count(*) from borrow br,reader r,book b where br.bookid=b.id and br.readerid=r.id and br.readerid=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,readerId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                res = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public List<Borrow> findAllBorrowByState(int index,int limit,int state) {
        List<Borrow> list = new ArrayList<>();
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select br.id,b.`name`,b.author,b.publish,r.`name`,r.tel,r.cardid,br.borrowtime,br.returntime,br.state from borrow br,reader r,book b " +
                "where br.bookid=b.id and br.readerid=r.id and state=? limit ?,?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(2,index);
            statement.setInt(3,limit);
            statement.setInt(1,state);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Borrow borrow = new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)),
                        resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10));
                list.add(borrow);
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
    public int countByState(int state) {
        int res = 0;
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select count(*) from borrow br,reader r,book b where br.bookid=b.id and br.readerid=r.id and state=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,state);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                res = resultSet.getInt(1);
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

        return res;
    }

    @Override
    public void handle(Integer borrowId, Integer adminId, Integer state) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement statement = null;
        String sql = "update borrow set state=?,adminid=? where id=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,state);
            statement.setInt(2,adminId);
            statement.setInt(3,borrowId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JDBCUtil.Close(connection,statement,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
