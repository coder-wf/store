package com.itheima.service.impl;

import com.itheima.bean.User;
import com.itheima.constant.Constants;
import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.service.UserService;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService {


    @Override
    public void regist(User user) throws Exception {
        UserDao userDao = new UserDaoImpl();
        userDao.save(user);

        // 发送激活邮件
        MailUtils.sendMail(user.getEmail(),
                "尊敬的" + user.getUsername()
                        + ":欢迎注册黑马商城. 请点击如下链接进行激活<a href='http://localhost:8080/store/userServlet?method=active&code="
                        + user.getCode() + "'>点击激活</a>");
    }

    @Override
    public User fingByCode(String code) throws Exception {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findByCode(code);

        if(user!=null){
            user.setState(Constants.USER_ACTIVED);
            user.setCode(null);

            userDao.update(user);
        }

        return user;
    }

    @Override
    public User login(String username, String password) throws Exception {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findByUnameAndPwd(username,password);

        return user;
    }

}
