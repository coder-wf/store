package com.itheima.service.impl;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.constant.Constants;
import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService{

    @Override
    public List<Product> findHots() throws Exception {

        ProductDao productDao = new ProductDaoImpl();
        return productDao.findHots();

    }

    @Override
    public List<Product> findNew() throws Exception {

        ProductDao productDao = new ProductDaoImpl();
        return productDao.findNew();

    }

    @Override
    public Product findByPid(String pid) throws Exception {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findByPid(pid);
    }

    @Override
    public PageBean<Product> findByPage(String cid, int curPage) throws Exception {
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.findCount(cid);

        int curSize = Constants.PRODUCT_CUR_SIZE;

        int sumPage = 0;

        if(count/curSize==0){
            sumPage = count/curSize;
        }else{
            sumPage = count/curSize+1;
        }

        int b = curSize;
        int a = (curPage-1)*b;

        List<Product> list = productDao.findList(cid,a,b);

        PageBean<Product> pageBean = new PageBean<>();

        pageBean.setList(list);
        pageBean.setCurPage(curPage);
        pageBean.setSumPage(sumPage);
        pageBean.setCount(count);
        pageBean.setCurSize(curSize);

        return pageBean;

    }

    @Override
    public List<Product> findAll() throws Exception {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws SQLException {
        ProductDao productDao  = new ProductDaoImpl();
        productDao.save(product);
    }
}
