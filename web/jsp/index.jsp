<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>WEB01</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="container-fluid">

			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<%--<div class="container-fluid">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath}/img/logo2.png" />
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					&lt;%&ndash;<ol class="list-inline">
						<li><a href="${pageContext.request.contextPath }/userServlet?method=loginUI">登录</a></li>
						<li><a href="../userServlet?method=registUI">注册</a></li>
						<li><a href="cart.htm">购物车</a></li>
					</ol>&ndash;%&gt;

					<!--登录成功-->
					<c:if test="${not empty  user}">
						<ol class="list-inline">
							<li>尊敬的,${user.username}</li>
							<li><a href="${pageContext.request.contextPath}/userServlet?method=logout">注销</a></li>
							<li><a href="${pageContext.request.contextPath}/userServlet?method=registUI">我的订单</a></li>
							<li><a href="cart.htm">购物车</a></li>
						</ol>
					</c:if>

					<!--没有登录成功  -->
					<c:if test="${empty  user}">
						<ol class="list-inline">
							<li><a href="${pageContext.request.contextPath}/userServlet?method=loginUI">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/userServlet?method=registUI">注册</a></li>
							<li><a href="cart.htm">购物车</a></li>
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
							<ul class="nav navbar-nav">
								<li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
								<li><a href="#">电脑办公</a></li>
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
			</div>--%>

			<%@ include file="header.jsp" %>

			<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：轮播条
            -->
			<div class="container-fluid">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="${pageContext.request.contextPath}/img/1.jpg">
							<div class="carousel-caption">

							</div>
						</div>
						<div class="item">
							<img src="${pageContext.request.contextPath}/img/2.jpg">
							<div class="carousel-caption">

							</div>
						</div>
						<div class="item">
							<img src="${pageContext.request.contextPath}/img/3.jpg">
							<div class="carousel-caption">

							</div>
						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
			<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：商品显示
            -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>热门商品&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/title2.jpg"/></h2>
				</div>
				<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
					<img src="${pageContext.request.contextPath}/products/hao/big01.jpg" width="205" height="404" style="display: inline-block;"/>
				</div>
				<div class="col-md-10">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/middle01.jpg" width="516px" height="200px" style="display: inline-block;">
						</a>
					</div>
				
					<%--<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small03.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small04.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small05.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small03.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small04.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small05.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small03.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small04.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small05.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>--%>

					<c:forEach items="${hotList}" var="p">
						<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
							<a href="${pageContext.request.contextPath}/productServlet?method=findByPid&pid=${p.pid}">
								<img src="${pageContext.request.contextPath}/${p.pimage}" width="130" height="130" style="display: inline-block;">
							</a>
							<p><a href="${pageContext.request.contextPath}/productServlet?method=findByPid&pid=${p.pid}" style='color:#666'>${fn:substring(p.pname,0,10)}...</a></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${p.shop_price}</font></p>
						</div>
					</c:forEach>

				</div>
			</div>
			<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：广告部分
            -->
            <div class="container-fluid">
				<img src="${pageContext.request.contextPath}/products/hao/ad.jpg" width="100%"/>
			</div>
			<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：商品显示
            -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>最新商品&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/title2.jpg"/></h2>
				</div>
				<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
					<img src="${pageContext.request.contextPath}/products/hao/big01.jpg" width="205" height="404" style="display: inline-block;"/>
				</div>
				<div class="col-md-10">
					<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/middle01.jpg" width="516px" height="200px" style="display: inline-block;">
						</a>
					</div>
				
					<%--<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small03.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>
	
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small04.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small05.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>
					
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small03.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>
	
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small04.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small05.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small03.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>
	
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small04.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>

					<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="product_info.htm">
							<img src="${pageContext.request.contextPath}/products/hao/small05.jpg" width="130" height="130" style="display: inline-block;">
						</a>
						<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
					</div>--%>

					<c:forEach items="${newList }" var="p">
						<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
							<a href="${pageContext.request.contextPath}/productServlet?method=findByPid&pid=${p.pid}">
								<img src="${pageContext.request.contextPath}/${p.pimage}" width="130" height="130" style="display: inline-block;">
							</a>
							<p><a href="${pageContext.request.contextPath}/productServlet?method=findByPid&pid=${p.pid}" style='color:#666'>${fn:substring(p.pname,0,10)}...</a></p>
							<p><font color="#E4393C" style="font-size:16px">&yen;${p.shop_price}</font></p>
						</div>
					</c:forEach>

				</div>
			</div>			
			<!--
            	作者：ci2713@163.com
            	时间：2015-12-30
            	描述：页脚部分
            -->
			<div class="container-fluid">
				<div style="margin-top:50px;">
					<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
				</div>
		
				<div style="text-align: center;margin-top: 5px;">
					<ul class="list-inline">
						<li><a href="info.html">关于我们</a></li>
						<li><a>联系我们</a></li>
						<li><a>招贤纳士</a></li>
						<li><a>法律声明</a></li>
						<li><a>友情链接</a></li>
						<li><a>支付方式</a></li>
						<li><a>配送方式</a></li>
						<li><a>服务声明</a></li>
						<li><a>广告声明</a></li>
					</ul>
				</div>
				<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
					Copyright &copy; 2005-2016 传智商城 版权所有
				</div>
			</div>
		</div>
	</body>

</html>