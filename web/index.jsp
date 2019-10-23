<%--
  Created by IntelliJ IDEA.
  User: coder_wf
  Date: 2019/9/10
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <!--重定向到网站首页  -->
  <%--<% response.sendRedirect(request.getContextPath()+"/jsp/index.jsp"); %>--%>
  <% response.sendRedirect(request.getContextPath()+"/productServlet?method=index"); %>;
  </body>
</html>
