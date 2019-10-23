package com.itheima.web.servlet;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductServlet extends BaseServlet{

    public String index(HttpServletRequest request, HttpServletResponse response){

        try {
            ProductService productService = new ProductServiceImpl();
            List<Product> hotList =  productService.findHots();
            List<Product> newList = productService.findNew();

            request.setAttribute("hotList",hotList);
            request.setAttribute("newList",newList);

            return "/jsp/index.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","服务器异常!");
            return "/jsp/msg.jsp";
        }
    }

    public String findByPid(HttpServletRequest request,HttpServletResponse response){
        try {
            String pid = request.getParameter("pid");

            ProductService productService = new ProductServiceImpl();
            Product product = productService.findByPid(pid);

            request.setAttribute("p",product);
            return "/jsp/product_info.jsp";

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("msg","服务器异常!");
            return "/jsp/msg.jsp";

        }
    }

    public String findByPage(HttpServletRequest request,HttpServletResponse response){

        try {

            String cid = request.getParameter("cid");
            int curPage = Integer.parseInt(request.getParameter("curPage"));

            ProductService productService = new ProductServiceImpl();
            PageBean<Product> pageBean = productService.findByPage(cid,curPage);

            request.setAttribute("pageBean",pageBean);
            return "/jsp/product_list.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "服务器异常!");
            return "/jsp/msg.jsp";
        }
    }

}
