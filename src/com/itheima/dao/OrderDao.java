package com.itheima.dao;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    void saveOrder (Order order) throws Exception;

    void saveOrderItem (OrderItem orderItem) throws Exception;

    int findCount(String uid) throws Exception;

    List<Order> findLimit(String uid, int a, int b) throws Exception;

    Order findByOid(String oid) throws Exception;

    void update(Order order) throws Exception;

    List<Order> findByState(String state) throws SQLException;
}
