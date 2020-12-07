package com.chenchangjie.service.impl;

import com.chenchangjie.repository.AdminRepository;
import com.chenchangjie.repository.ReaderRepository;
import com.chenchangjie.repository.impl.AdminRepositoryImpl;
import com.chenchangjie.repository.impl.ReaderRepositoryImpl;
import com.chenchangjie.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    /**
     * 处理逻辑，数据交给ReadRepository进行验证
     * @param username
     * @param password
     * @return
     */
    @Override
    public Object login(String username, String password,String type) {
        Object obj = null;
        switch (type){
            case "reader":
                obj = readerRepository.login(username, password);
                break;
            case "admin":
                obj =  adminRepository.login(username, password);
                break;
        }
        return obj;
    }
}
