package com.chenchangjie.repository;

import com.chenchangjie.entity.Reader;

public interface ReaderRepository {
    /**
     * 连接数据库检查是否有相应的用户数据
     * @param username
     * @param password
     * @return
     */
    Reader login(String username,String password);
}
