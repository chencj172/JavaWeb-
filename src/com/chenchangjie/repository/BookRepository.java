package com.chenchangjie.repository;

import com.chenchangjie.entity.Book;

import java.util.List;

public interface BookRepository {
    /**
     * 根据index 和 limit显示对应的数据
     * index 是开始的下标，limit 是限制的显示的记录条数
     * @param index
     * @return
     */
    List<Book> FindAll(int index,int limit);

    /**
     * 返回总记录条数
     * @return
     */
    int count();
}
