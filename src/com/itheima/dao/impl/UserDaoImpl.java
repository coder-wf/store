package com.itheima.dao.impl;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {user.getUid(),
                user.getUsername(),user.getPassword(),user.getName(),
                user.getEmail(),user.getTelephone(),user.getBirthday(),
                user.getSex(),user.getState(),user.getCode()
        };

        queryRunner.update(sql,params);
    }


    @Override
    public User findByCode(String code) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select * from user where code= ?";
        return queryRunner.query(sql,new BeanHandler<>(User.class),code);
    }

    @Override
    public void login(User user) {

    }

    @Override
    public void update(User user) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "update user set username = ?, password = ?,name = ?,email = ?,telephone = ? ,birthday = ?, sex = ?,state= ?,code= ? where uid = ?";
        Object[] params = {
                user.getUsername(),user.getPassword(),user.getName(),
                user.getEmail(),user.getTelephone(),user.getBirthday(),
                user.getSex(),user.getState(),user.getCode(),user.getUid()
        };

        queryRunner.update(sql,params);
    }

    @Override
    public User findByUnameAndPwd(String username, String password) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select * from user where username = ? and password = ?";

        return queryRunner.query(sql,new BeanHandler<>(User.class),username,password);
    }
}
