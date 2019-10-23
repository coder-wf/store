<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png" />
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png" />
    </div>
    <div class="col-md-3" style="padding-top:20px">

        <!--登录成功-->
        <c:if test="${not empty  user}">
            <ol class="list-inline">
                <li>尊敬的,${user.username }</li>
                <li><a href="${pageContext.request.contextPath}/userServlet?method=logout">注销</a></li>
                <li><a href="${pageContext.request.contextPath}/orderServlet?method=findByPage&curPage=1">我的订单</a></li>
                <li><a href="cart.jsp">购物车</a></li>
            </ol>
        </c:if>

        <!--没有登录成功  -->
        <c:if test="${empty  user}">
            <ol class="list-inline">
                <li><a href="${pageContext.request.contextPath}/userServlet?method=loginUI">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/userServlet?method=registUI">注册</a></li>
                <li><a href="cart.jsp">购物车</a></li>
            </ol>
        </c:if>
    </div>
</div>
<!--
    时间：2015-12-30
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="ulId">
                    <!-- 	<li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>
                        <li><a href="#">电脑办公</a></li>
                        <li><a href="#">电脑办公</a></li>
                        <li><a href="#">电脑办公</a></li> -->
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>

</body>
<script type="text/javascript">


    $.post("${pageContext.request.contextPath}/categoryServlet",{"method":"findAll"},function(result){
        //解析result, 填充页面
        //[{"cid":"1","cname":"手机数码"},{"cid":"172934bd636d485c98fd2d3d9cccd409","cname":"运动户外"},{"cid":"2","cname":"电脑办公"},{"cid":"3","cname":"家具家居"},{"cid":"4","cname":"鞋靴箱包"},{"cid":"5","cname":"图书音像"},{"cid":"6","cname":"母婴孕婴"},{"cid":"afdba41a139b4320a74904485bdb7719","cname":"汽车用品"}]
        $(result).each(function(i,obj){
            //每遍历一次 就向ul里面append一个li

            //console.log(obj);

            $("#ulId").append("<li><a href='${pageContext.request.contextPath}/productServlet?method=findByPage&curPage=1&cid="+obj.cid+"'>"+obj.cname+"</a></li>");

        });

    },"json");

</script>

</html>

