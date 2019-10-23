package com.itheima.web.servlet;

import com.itheima.bean.*;
import com.itheima.constant.Constants;
import com.itheima.service.OrderService;
import com.itheima.service.impl.OrderServiceImpl;
import com.itheima.utils.PaymentUtil;
import com.itheima.utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderServlet extends BaseServlet {

    /**
     * 保存订单
     */
    public String save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
            return null;
        }

        Cart cart = (Cart)request.getSession().getAttribute("cart");

        //1. 封装order
        Order order = new Order();
        //1.1 封装订单oid
        order.setOid(UUIDUtils.getId());
        //1.2  封装订单的时间

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sf.format(new Date());
        Date parse = sf.parse(format);
        //System.out.println("1:"+new Date());
        //System.out.println("2:"+parse);

        order.setOrdertime(parse);
        //1.3 封装订单状态(未付款)
        order.setState(Constants.WEI_FU_KUAN);
        //1.4 封装当前的订单属于哪一个用户
        order.setUser(user);
        //1.5  封装总金额(购物车)
        order.setTotal(cart.getTotal());

        //1.6 封装订单项集合(购物车)
        List<OrderItem> orderItems = new ArrayList<>();
        Collection<CartItem> values = cart.getValues();

        for(CartItem cartItem:values){
            //一个购物项对应一个订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUIDUtils.getId());//订单项id
            orderItem.setCount(cartItem.getCount());//购买数量
            orderItem.setOrder(order);//当前订单项属于哪一个订单
            orderItem.setProduct(cartItem.getProduct());//购买商品
            orderItem.setSubtotal(cartItem.getSubtotal());//小计

            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        OrderService orderService = new OrderServiceImpl();
        Boolean isSuccess = orderService.save(order);

        if(isSuccess){
            request.getSession().setAttribute("o",order);
            response.sendRedirect(request.getContextPath()+"/jsp/order_info.jsp");
            return null;
        }else{
            request.getSession().setAttribute("msg","服务器异常");
            response.sendRedirect(request.getContextPath()+"/jsp/msg.jsp");
            return null;
        }
    }

    /**
     * 分页查询当前用户的订单
     * @param request
     * @param response
     * @return
     */
    public String findByPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获得当前用户的uid(session里面)
            User user = (User) request.getSession().getAttribute("user");
            String uid = user.getUid();

            // 获得请求参数(curPage)
            int curPage = Integer.parseInt(request.getParameter("curPage"));

            //调用业务, 获得分页的数据 PageBean<Order> pageBean
            OrderService orderService = new OrderServiceImpl();
            PageBean<Order> pageBean =  orderService.findByPage(curPage,uid);
            //把pageBean存到request里面, 转发 /jsp/order_list.jsp页面
            request.setAttribute("pageBean", pageBean);
            return "/jsp/order_list.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据oid查询订单详情
     * @param request
     * @param response
     * @return
     */
    public String findByOid(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获得请求参数(oid)
            String oid = request.getParameter("oid");

            // 调用业务, 获得当前订单对象 Order order
            OrderService orderService = new OrderServiceImpl();
            Order order =  orderService.findByOid(oid);
            //把order存在request里面, 转发到/jsp/order_info.jsp
            request.setAttribute("o", order);
            return "/jsp/order_info.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "获取订单详情失败!");
            return "/jsp/msg.jsp";
        }
    }

    /**
     * 在线支付
     * @param request
     * @param response
     * @return
     */
    public String pay(HttpServletRequest request, HttpServletResponse response) {
        try {
            //接受参数
            String address=request.getParameter("address");
            String name=request.getParameter("name");
            String telephone=request.getParameter("telephone");
            String oid=request.getParameter("oid");

            //通过id获取order
            OrderService orderService = new OrderServiceImpl();
            Order order = orderService.findByOid(oid);

            order.setAddress(address);
            order.setName(name);
            order.setTelephone(telephone);

            //更新order
            orderService.update(order);


            // 组织发送支付公司需要哪些数据
            String pd_FrpId = request.getParameter("pd_FrpId");
            String p0_Cmd = "Buy";
            String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
            String p2_Order = oid;
            String p3_Amt = "0.01";
            String p4_Cur = "CNY";
            String p5_Pid = "";
            String p6_Pcat = "";
            String p7_Pdesc = "";
            // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
            // 第三方支付可以访问网址
            String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
            String p9_SAF = "";
            String pa_MP = "";
            String pr_NeedResponse = "1";
            // 加密hmac 需要密钥
            String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
            String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                    p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                    pd_FrpId, pr_NeedResponse, keyValue);


            //发送给第三方
            StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
            sb.append("p0_Cmd=").append(p0_Cmd).append("&");
            sb.append("p1_MerId=").append(p1_MerId).append("&");
            sb.append("p2_Order=").append(p2_Order).append("&");
            sb.append("p3_Amt=").append(p3_Amt).append("&");
            sb.append("p4_Cur=").append(p4_Cur).append("&");
            sb.append("p5_Pid=").append(p5_Pid).append("&");
            sb.append("p6_Pcat=").append(p6_Pcat).append("&");
            sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
            sb.append("p8_Url=").append(p8_Url).append("&");
            sb.append("p9_SAF=").append(p9_SAF).append("&");
            sb.append("pa_MP=").append(pa_MP).append("&");
            sb.append("pd_FrpId=").append(pd_FrpId).append("&");
            sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
            sb.append("hmac=").append(hmac);

            response.sendRedirect(sb.toString());

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 支付之后的方法处理
     * @param request
     * @param response
     * @return
     */
    public String callback(HttpServletRequest request, HttpServletResponse response) {
        try {
            String p1_MerId = request.getParameter("p1_MerId");
            String r0_Cmd = request.getParameter("r0_Cmd");
            String r1_Code = request.getParameter("r1_Code");
            String r2_TrxId = request.getParameter("r2_TrxId");
            String r3_Amt = request.getParameter("r3_Amt");
            String r4_Cur = request.getParameter("r4_Cur");
            String r5_Pid = request.getParameter("r5_Pid");
            String r6_Order = request.getParameter("r6_Order");
            String r7_Uid = request.getParameter("r7_Uid");
            String r8_MP = request.getParameter("r8_MP");
            String r9_BType = request.getParameter("r9_BType");
            String rb_BankId = request.getParameter("rb_BankId");
            String ro_BankOrderId = request.getParameter("ro_BankOrderId");
            String rp_PayDate = request.getParameter("rp_PayDate");
            String rq_CardNo = request.getParameter("rq_CardNo");
            String ru_Trxtime = request.getParameter("ru_Trxtime");
            // 身份校验 --- 判断是不是支付公司通知你
            String hmac = request.getParameter("hmac");
            String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
                    "keyValue");

            // 自己对上面数据进行加密 --- 比较支付公司发过来hamc
            boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                    r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                    r8_MP, r9_BType, keyValue);
            if (isValid) {
                // 响应数据有效
                if (r9_BType.equals("1")) {
                    // 浏览器重定向
                    System.out.println("111");
                    request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");

                } else if (r9_BType.equals("2")) {
                    // 服务器点对点 --- 支付公司通知你
                    System.out.println("付款成功！222");
                    // 修改订单状态 为已付款
                    // 回复支付公司
                    response.getWriter().print("success");
                }

                //修改订单状态
                OrderService orderService = new OrderServiceImpl();
                Order order = orderService.findByOid(r6_Order);
                order.setState(Constants.YI_FU_KUAN);
                orderService.update(order);

            } else {
                // 数据无效
                System.out.println("数据被篡改！");
            }

            return "/jsp/msg.jsp";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
