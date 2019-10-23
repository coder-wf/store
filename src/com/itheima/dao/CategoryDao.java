package com.itheima.dao;

import com.itheima.bean.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> findAll() throws Exception;

    void add(Category category) throws Exception;
}
