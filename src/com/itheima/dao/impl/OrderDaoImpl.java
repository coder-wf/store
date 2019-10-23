package com.itheima.dao.impl;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.Product;
import com.itheima.dao.OrderDao;
import com.itheima.utils.C3P0Utils;
import com.itheima.utils.ConnectionManager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void saveOrder (Order order) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        Object[] params = { order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
                order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid() };
        queryRunner.update(ConnectionManager.getConnectionByLocalThread(), sql, params);
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orderitem values(?,?,?,?,?)";
        Object[] params = { orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(),
                orderItem.getProduct().getPid(), orderItem.getOrder().getOid() };
        queryRunner.update(ConnectionManager.getConnectionByLocalThread(), sql, params);
    }

    @Override
    public int findCount(String uid) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select count(*) from orders where uid = ?";
        Long n = (Long) queryRunner.query(sql, new ScalarHandler(),uid);
        return n.intValue();
    }

    @Override
    public List<Order> findLimit(String uid, int a, int b) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from orders where uid = ? limit ?,?";
        List<Order> list = queryRunner.query(sql, new BeanListHandler<>(Order.class),uid,a,b);
        //封装每一个订单的订单项集合,需要把每一个订单的oid作为查询条件
        for (Order order : list) {
            sql = "SELECT * FROM orderitem oi, product p WHERE oi.oid = ? AND oi.pid = p.pid";
            List<Map<String, Object>> mapList = queryRunner.query(sql,new MapListHandler() ,order.getOid());
            //每一个map封装成一个订单项
            for (Map<String, Object> map : mapList) {

                OrderItem orderItem = new OrderItem();
                BeanUtils.populate(orderItem, map);// 把map里面和OrderItem对应的属性封装起来了(但是没有封装product)

                Product product = new Product();
                BeanUtils.populate(product, map);//把map里面和product相关的数据 封装进去

                orderItem.setProduct(product); //把product 封装到orderItem

                //把orderItem 添加到order的订单项集合里面
                order.getOrderItems().add(orderItem);
            }
        }

        return list;
    }

    @Override
    public Order findByOid(String oid) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT * from orders where oid = ?";
        Order order = queryRunner.query(sql, new BeanHandler<>(Order.class),oid);
        //查询当前订单的订单项
        sql = "SELECT * from orderitem oi, product p where oi.oid = ? AND oi.pid = p.pid";
        List<Map<String, Object>> mapList = queryRunner.query(sql, new MapListHandler(),order.getOid());

        for (Map<String, Object> map : mapList) {
            //一个map对应一条数据, 封装成一个OrderItem对象
            OrderItem orderItem = new OrderItem();
            BeanUtils.populate(orderItem, map);//只能封装orderItem属性和map的key一致的值(没有封装product)

            Product product = new Product();
            BeanUtils.populate(product, map);//把map里面和product相关的数据封装成product对象

            orderItem.setProduct(product); //把product 设置到orderItem

            order.getOrderItems().add(orderItem); //把orderItem添加到订单的订单项集合里面


        }

        return order;
    }

    @Override
    public void update(Order order) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update orders set state = ?,address = ?, name= ?,telephone = ? where oid = ?";
        Object[] params = {order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
        queryRunner.update(sql, params);
    }

    @Override
    public List<Order> findByState(String state) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from orders";

        if(state != null){
            sql+=" where state = ?";
            return queryRunner.query(sql, new BeanListHandler<>(Order.class),state);
        }

        return queryRunner.query(sql, new BeanListHandler<>(Order.class));
    }
}
