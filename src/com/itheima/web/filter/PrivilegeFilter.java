package com.itheima.web.filter;

import com.itheima.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrivilegeFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		//0. 强转两个参数
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//1. 判断用户是否登录
		User user = (User) request.getSession().getAttribute("user");
		
		//2. 登录 ,放行
		if(user != null){
			chain.doFilter(request, response);
			return;
		}
		
		//3. 没有登录, 拦截下来, 跳转到登录页面
		response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
		
		
	}

	
	public void destroy() {
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
