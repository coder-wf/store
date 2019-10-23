package com.itheima.web.servlet;

import com.itheima.bean.Category;
import com.itheima.service.CategoryService;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品分类
 */
public class AdminCategoryServlet extends BaseServlet {

    /**
     * 查询所有商品分类
     * @param request
     * @param response
     * @return
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response){

        try {
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> list = categoryService.findList();
            request.getSession().setAttribute("list",list);

            return "/admin/category/list.jsp";

        } catch (Exception e) {
            e.printStackTrace();

            request.getSession().setAttribute("msg","服务器异常!");
            return request.getContextPath()+"/jsp/msg.jsp";

        }
    }

    public String saveUI(HttpServletRequest request,HttpServletResponse response){
        return "/admin/category/add.jsp";
    }

    public String add(HttpServletRequest request,HttpServletResponse response){

        try {
            String cname = request.getParameter("cname");

            Category category = new Category();
            category.setCid(UUIDUtils.getId());
            category.setCname(cname);

            CategoryService categoryService = new CategoryServiceImpl();
            categoryService.add(category);

            //再查询所有展示
            response.sendRedirect(request.getContextPath()+"/adminCategoryServlet?method=findAll");
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("msg","服务器异常!");
            return request.getContextPath()+"/jsp/msg.jsp";
        }

    }
}
