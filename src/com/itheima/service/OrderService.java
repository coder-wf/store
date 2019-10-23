package com.itheima.service;

import com.itheima.bean.Order;
import com.itheima.bean.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    boolean save(Order order);

    PageBean<Order> findByPage(int curPage, String uid) throws Exception;

    Order findByOid(String oid) throws Exception;

    void update(Order order) throws Exception;

    List<Order> findByState(String state) throws SQLException;
}
