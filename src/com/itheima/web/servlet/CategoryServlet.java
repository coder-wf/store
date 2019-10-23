package com.itheima.web.servlet;

import com.itheima.service.CategoryService;
import com.itheima.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryServlet extends BaseServlet {

    /**
     * 查询所有分类
     * @return
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response){

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            CategoryService categoryService = new CategoryServiceImpl();
            String data = categoryService.findAll();

            System.out.println(data);

            response.getWriter().print(data);
            return null;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }
}
