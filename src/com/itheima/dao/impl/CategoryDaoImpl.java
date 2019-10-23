package com.itheima.dao.impl;

import com.itheima.bean.Category;
import com.itheima.dao.CategoryDao;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() throws Exception {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select * from category";

        return queryRunner.query(sql,new BeanListHandler<>(Category.class));
    }

    @Override
    public void add(Category category) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql= "insert into category values(?,?)";
        queryRunner.update(sql, category.getCid(),category.getCname());
    }
}
