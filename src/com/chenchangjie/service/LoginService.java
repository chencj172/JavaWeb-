package com.chenchangjie.service;


public interface LoginService {
    /**
     * type对应用户和管理员，username和 password传给repository检测数据库中是否有数据
     * @param username
     * @param password
     * @param type
     * @return
     */
    Object login(String username,String password,String type);
}
