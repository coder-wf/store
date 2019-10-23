package com.itheima.dao.impl;

import com.itheima.bean.Product;
import com.itheima.constant.Constants;
import com.itheima.dao.ProductDao;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao{
    @Override
    public List<Product> findHots() throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select * from product where is_hot = ? limit ?,?";

        return queryRunner.query(sql,new BeanListHandler<>(Product.class), Constants.PRODUCT_IS_HOT,0,9);

    }

    @Override
    public List<Product> findNew() throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "select * from product order by pdate desc limit ?,?";

        return queryRunner.query(sql,new BeanListHandler<>(Product.class),0,9);

    }

    @Override
    public Product findByPid(String pid) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from product where pid = ?";
        return queryRunner.query(sql,new BeanHandler<>(Product.class),pid);
    }

    @Override
    public int findCount(String cid) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select count(*) from product where cid = ?";
        Long count = (Long)queryRunner.query(sql,new ScalarHandler(),cid);
        return count.intValue();
    }

    @Override
    public List<Product> findList(String cid, int a, int b) throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from product where cid = ? limit ?,?";
        return queryRunner.query(sql,new BeanListHandler<>(Product.class),cid,a,b);
    }

    @Override
    public List<Product> findAll() throws Exception {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from product";
        return queryRunner.query(sql,new BeanListHandler<>(Product.class));
    }

    @Override
    public void save(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = { product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
                product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
                product.getCid() };
        queryRunner.update(sql, params);
    }
}
