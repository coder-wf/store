package com.itheima.service;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> findHots() throws Exception;

    List<Product> findNew() throws Exception;

    Product findByPid(String pid) throws Exception;

    PageBean<Product> findByPage(String cid, int curPage) throws Exception;

    List<Product> findAll() throws Exception;

    void save(Product product) throws SQLException;
}
