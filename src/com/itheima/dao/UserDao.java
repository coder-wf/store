package com.itheima.dao;

import com.itheima.bean.User;

public interface UserDao {

    /**
     * 用户注册
     */
    void save(User user) throws Exception;

    /**
     * 用户激活
     * @return
     */
    User findByCode(String code) throws Exception;

    /**
     * 用户登录
     */
    void login(User user);

    void update(User user) throws Exception;

    User findByUnameAndPwd(String username, String password) throws Exception;
}
