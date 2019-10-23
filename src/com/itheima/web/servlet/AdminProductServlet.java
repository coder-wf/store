package com.itheima.web.servlet;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.service.CategoryService;
import com.itheima.service.ProductService;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author wf
 * @Date 2019/9/12 16:19
 * @Description 后台商品管理
 */
public class AdminProductServlet extends BaseServlet{

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        try {
            ProductService productService = new ProductServiceImpl();
            List<Product> list =  productService.findAll();

            request.getSession().setAttribute("list",list);
            //response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");

            return "/admin/product/list.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String addUI(HttpServletRequest request,HttpServletResponse response){

        try {
            //调用业务获得所有的类别 List<Category> list
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> list = categoryService.findList();
            //把list存到request域里面, 转发 /admin/product/add.jsp展示类别
            request.setAttribute("list", list);

            return "/admin/product/add.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
