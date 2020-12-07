package com.chenchangjie.repository;

import com.chenchangjie.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    /**
     * 通过service传来的数据进行表的插入操作
     * @param bookId
     * @param readerId
     * @param borrowTime
     * @param returnTime
     * @param adminId
     * @param state
     */
    void Borrow(Integer bookId,Integer readerId,String borrowTime,String returnTime,Integer adminId,Integer state);

    /**
     * 根据用户的id返回用户借了哪些书
     * @return
     */
    List<Borrow> findBorrowBookByReadId(int readerID,int index,int limit);

    /**
     * 返回某用户借书的总数
     * @return
     */
    int countBorrow(int readerId);

    /**
     * 返回给管理员未审核的状态列表
     * @return
     */
    List<Borrow> findAllBorrowByState(int index,int limit,int state);

    /**
     * 返回给service借书的总页数
     * @param state
     * @return
     */
    int countByState(int state);

    /**
     * 根据borrowId改变数据库borrow表中state的值，并且添加adminID
     * @param borrowId
     * @param adminId
     * @param state
     */
    void handle(Integer borrowId,Integer adminId,Integer state);
}
