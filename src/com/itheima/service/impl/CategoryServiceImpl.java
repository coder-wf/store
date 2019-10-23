package com.itheima.service.impl;

import com.itheima.bean.Category;
import com.itheima.constant.Constants;
import com.itheima.dao.CategoryDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.service.CategoryService;
import com.itheima.utils.JedisUtils;
import com.itheima.utils.JsonUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public String findAll() throws Exception {

        /*CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list = categoryDao.findAll();
        String data = JsonUtil.list2json(list);
        return data;*/

        String data = null;
        Jedis jedis = null;


        jedis = JedisUtils.getJedis();
        data = getFromRedis(jedis);

        try {
            if(data==null){
                System.out.println("Redis里面没有数据,从MySql获得,再存到了Redis里面...");
                data = getFromMysql();
                saveToRedis(jedis,data);
            }else{
                System.out.println("Redis里面有数据的,直接获得的了...");
            }
        } catch (Exception e) {
            System.out.println("Redis服务器挂了或者没开启,从mysql获得...");
            data = getFromMysql();
            e.printStackTrace();
        } finally {
            JedisUtils.closeJedis(jedis);
        }


        return data;

    }

    @Override
    public List<Category> findList() throws Exception {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list =  categoryDao.findAll();
        return list;
    }

    @Override
    public void add(Category category) throws Exception {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.add(category);

        //更新redis里面的数据(清空)
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            if(jedis != null){
                jedis.del(Constants.STORE_CATEGORY_KEY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JedisUtils.closeJedis(jedis);
        }

    }

    //把数据存到redis中
    private void saveToRedis(Jedis jedis,String data){
        if(jedis!=null){
            jedis.set(Constants.STORE_CATEGORY_KEY,data);
        }
    }

    //从redis中取数据
    private String getFromRedis(Jedis jedis){

        if(jedis!=null){
        return jedis.get(Constants.STORE_CATEGORY_KEY);
        }

        return null;
    }

    //从mysql中取数据
    private String getFromMysql() throws Exception {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list = categoryDao.findAll();
        String data = JsonUtil.list2json(list);
        return data;
    }
}
