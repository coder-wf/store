package com.itheima.web.servlet;

import com.itheima.bean.User;
import com.itheima.constant.Constants;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class UserServlet extends BaseServlet{


    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @return
     */
    public String registUI(HttpServletRequest request, HttpServletResponse response){
        return "/jsp/register.jsp";
    }

    /**
     * 注册
     * @param request
     * @param response
     * @return
     */
    public String regist(HttpServletRequest request,HttpServletResponse response){
        try {
            //获取参数
            Map<String,String[]> map = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,map);

            user.setUid(UUIDUtils.getId());
            user.setState(Constants.USER_NOT_ACTIVE);
            user.setCode(UUIDUtils.getCode());

            //调用业务,进行注册
            UserService userService = new UserServiceImpl();
            userService.regist(user);

            //跳转页面给用户提示
            request.setAttribute("msg","恭喜,注册成功,请登录邮箱激活!");
            return "/jsp/msg.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","注册失败,请稍后再试!");
            return "/jsp/msg.jsp";
        }
    }
    /**
     * 用户激活
     */
    public String active(HttpServletRequest request,HttpServletResponse response) {
        try {
            String code = request.getParameter("code");

            UserService userService =new UserServiceImpl();
            User user = userService.fingByCode(code);

            //判断是否激活成功了,给用户提示
            if(user!=null){
                response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
                return null;
            }else{
                request.setAttribute("msg","激活失败,请重新注册!");
                return "/jsp/msg.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","激活失败,请重新注册!");
            return "/jsp/msg.jsp";
        }

    }

    /**
     * 跳转到登录页面
     */
    public String loginUI(HttpServletRequest request,HttpServletResponse response){
        return "/jsp/login.jsp";
    }

    /**
     * 登录
     */
    public String login(HttpServletRequest request,HttpServletResponse response) throws Exception {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserServiceImpl();
            User user = userService.login(username,password);

            //判断是否登录成功
            if(user != null){
                //用户名和密码匹配, 不确定是否激活
                if(Constants.USER_ACTIVED == user.getState()){
                    //************记住用户名*************
                    //判断用户是否勾选了复选框
                    String rem = request.getParameter("rem");
                    if(rem != null && "ok".equals(rem)){
                        System.out.println("用户勾选了记住用户名...");
                        //把用户名存到cookie
                        Cookie cookie = new Cookie("username", username);
                        cookie.setMaxAge(60*60*24*7);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }else{
                        Cookie cookie = new Cookie("username", "");
                        cookie.setMaxAge(0);
                        cookie.setPath(request.getContextPath());
                        response.addCookie(cookie);
                    }
                    //************记住用户名结束*************

                    //登录成功
                    //a. 通过session保存user
                    request.getSession().setAttribute("user", user);
                    //b. 重定向到登录页面展示
                    response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
                    return null;
                }else{
                    //没有激活
                    request.setAttribute("msg", "您还没有激活!请先登录邮箱激活");
                    return "/jsp/msg.jsp";
                }

            }else{
                //用户名和密码不匹配
                request.setAttribute("msg", "用户名和密码不匹配");
                return "/jsp/login.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "服务器异常!登录失败!");
            return "/jsp/msg.jsp";
        }
    }

    /**
     * 退出登录
     */
    public String logout(HttpServletRequest request,HttpServletResponse response){
        return "/jsp/login.jsp";
    }
}
