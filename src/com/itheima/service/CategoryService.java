package com.itheima.service;

import com.itheima.bean.Category;

import java.util.List;

public interface CategoryService {

    String findAll() throws Exception;

    List<Category> findList() throws Exception;

    void add(Category category) throws Exception;
}
