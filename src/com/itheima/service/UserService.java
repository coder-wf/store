package com.itheima.service;

import com.itheima.bean.User;

public interface UserService {

    /**
     * 用户注册
     */
     void regist(User user) throws Exception;

    /**
     * 用户激活
     * @return
     */
     User fingByCode(String code) throws Exception;

    /**
     * 用户登录
     * @return
     */


     User login(String username, String password) throws Exception;
}
