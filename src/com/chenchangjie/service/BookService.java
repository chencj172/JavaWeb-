package com.chenchangjie.service;

import com.chenchangjie.entity.Book;
import com.chenchangjie.entity.Borrow;

import java.util.List;

public interface BookService {
    /**
     * 根据page,计算出相应的开始下标
     * @param page
     * @return
     */
    List<Book> FindAll(int page);

    /**
     * 根据repository返回总的记录条数计算总的页数
     * @return
     */
    int getPages();

    /**
     * 借书
     */
    void Borrow(int bookId,int readerId);

    /**
     * 根据用户id和需要展示的页数返回该用户借书的列表
     * @param readerID
     * @param page
     * @return
     */
    List<Borrow> ShowBorrowBookByReadId(int readerID,int page);

    /**
     * readerId传给repository层，根据repository返回总的记录条数计算总的借书页数
     * @return
     */
    int getBorrowBooksPages(int readerId);

    /**
     * 返回给管理员未审核的集合
     * @param state
     * @return
     */
    List<Borrow> findAllBorrowByState(int page,int state);

    /**
     * 返回给管理员未审核的页数
     * @return
     */
    int getBorrowPagesByState(int state);

    /**
     * 管理员处理借书请求
     * @param borrowId
     * @param adminId
     * @param state
     */
    void handleBorrow(Integer borrowId,Integer adminId,Integer state);
}
