package com.itheima.web.servlet;


import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.constant.Constants;
import com.itheima.service.OrderService;
import com.itheima.service.impl.OrderServiceImpl;
import com.itheima.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author wf
 * @Date 2019/9/12 19:11
 * @Description 订单管理
 */
public class AdminOrderServlet extends BaseServlet{

    public String findByState(HttpServletRequest request, HttpServletResponse response){

        try {
            String state = request.getParameter("state");

            OrderService orderService = new OrderServiceImpl();
            List<Order> list = orderService.findByState(state);

            request.getSession().setAttribute("list",list);
            return "/admin/order/list.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据oid查询订单, 响应订单项集合对应的json
     * @param request
     * @param response
     * @return
     */
    public String findByOid(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            //1. 获得请求参数(oid)
            String oid = request.getParameter("oid");
            //2. 调用业务, 根据oid获得订单对象 Order order
            OrderService orderService = new OrderServiceImpl();
            Order order = orderService.findByOid(oid);
            //3.  获得订单里面的订单项集合, 通过json_lib转成json ,响应给前端
            List<OrderItem> list = order.getOrderItems();
            String data = JsonUtil.list2json(list);
            //System.out.println("data="+data);
            response.getWriter().print(data);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 后台改订单的状态(去发货)
     * @param request
     * @param response
     * @return
     */
    public String update(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获得请求参数(oid)
            String oid = request.getParameter("oid");

            //调用业务, 根据oid获得订单Order
            OrderService orderService = new OrderServiceImpl();
            Order order = orderService.findByOid(oid);
            //设置order对象的state=2
            order.setState(Constants.YI_FA_HUO);
            //调用业务,进行更新
            orderService.update(order);
            //再查询展示
            response.sendRedirect(request.getContextPath()+"/adminOrderServlet?method=findByState&state=1");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
