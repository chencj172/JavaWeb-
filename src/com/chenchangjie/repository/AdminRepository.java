package com.chenchangjie.repository;

import com.chenchangjie.entity.Admin;

public interface AdminRepository {
    /**
     * 连接数据库检测是否有管理员数据
     * @param username
     * @param password
     * @return
     */
    Admin login(String username,String password);
}
