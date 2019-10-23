package com.itheima.dao;

import com.itheima.bean.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List<Product> findHots() throws Exception;

    List<Product> findNew() throws Exception;

    Product findByPid(String pid) throws Exception;


    int findCount(String cid) throws Exception;

    List<Product> findList(String cid, int a, int b) throws Exception;

    List<Product> findAll() throws Exception;

    void save(Product product) throws SQLException;
}
