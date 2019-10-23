package com.itheima.service.impl;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.PageBean;
import com.itheima.constant.Constants;
import com.itheima.dao.OrderDao;
import com.itheima.dao.impl.OrderDaoImpl;
import com.itheima.service.OrderService;
import com.itheima.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean save(Order order) {
        Connection connection = null;

        try {
            //开启事务
            connection = ConnectionManager.getConnectionByLocalThread();
            connection.setAutoCommit(false);

            //调用Dao(在orders表里面中插入一条数据,在orderitem中间表里面中插入多条(1到n)数据)
            OrderDao orderDao = new OrderDaoImpl();
            orderDao.saveOrder(order);

            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem:orderItems){
                orderDao.saveOrderItem(orderItem);
            }

            //提交
            connection.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public PageBean<Order> findByPage(int curPage, String uid) throws Exception {
        OrderDao orderDao= new OrderDaoImpl();

        //当前用户订单一页显示的数量
        int curSize = Constants.ORDER_CUR_SIZE;
        //当前用户的订单的总数量(查)
        int count = orderDao.findCount(uid);
        //总页码
        int sumPage  = 0;
        if(count % curSize == 0){
            sumPage = count/curSize;
        }else{
            sumPage = count/curSize+1;
        }

        //订单的List ;    where uid = ? limit a ,b
        int b = curSize;
        int a = (curPage -1)*b;
        List<Order> list = orderDao.findLimit(uid,a,b);

        //1.调用Dao, 封装PageBean
        PageBean<Order> pageBean = new PageBean<Order>();

        //1.1 封装当前页码
        pageBean.setCurPage(curPage);
        //1.2 封装一页显示的数量
        pageBean.setCurSize(curSize);
        //1.3 封装当前用户的订单的总数量
        pageBean.setCount(count);
        //1.4 封装总页码
        pageBean.setSumPage(sumPage);
        //1.5 封装订单的List
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public Order findByOid(String oid) throws Exception {
        OrderDao orderDao= new OrderDaoImpl();
        return orderDao.findByOid(oid);
    }

    @Override
    public void update(Order order) throws Exception {
        OrderDao orderDao= new OrderDaoImpl();
        orderDao.update(order);
    }

    @Override
    public List<Order> findByState(String state) throws SQLException {
        OrderDao orderDao = new OrderDaoImpl();
        return orderDao.findByState(state);
    }
}
