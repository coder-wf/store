package com.itheima.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response){

        try {
            String methodStr = request.getParameter("method");

            Class clazz = this.getClass();

            Method method = clazz.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);

            String path = (String) method.invoke(this,request,response);

            if(path!=null){
                request.getRequestDispatcher(path).forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
