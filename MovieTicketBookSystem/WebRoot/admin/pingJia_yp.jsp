<%@ page language="java" import="java.util.*,com.team3.mbts.util.*,com.team3.mbts.entity.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>查看顾客评价</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
    <link rel="stylesheet" type="text/css" href="../css/admin/CinemaRemark.css">

    <style type="text/css">
		<!--
		a:link {
			color: #000000;
			text-decoration: none;
		} 
		a:visited {
			text-decoration: none;
			color: #000000;
		}
		a:hover {
			text-decoration: underline;
			color: #FF9900;
		}
		a:active {
			text-decoration: none;
		}
		-->
	</style>
	
	<style type="text/javascript">
		
	</style>
  </head>
  
  <body>
  <form action="SelectAllMoveServlet" method="post">
  	<div class="box">
    	<div class="title">&nbsp;影片评价列</div>
				<hr>
		<div class="index">
			<div class="index_left">
				<span>搜索影片：</span>
		<input name="movie" type="text" placeholder="请输入影片名" style="width:140px; height:27px;">
          <input name="submit" type="submit" style=" height:26px;"  value=" 确定"/>
			</div>
			
		</div>
		
		<div class="main">
			<div class="cinema">
				<div class="cinema_pic">
					<img src="../images/cinema/scene_wanda.jpg" width="300" height="200">
				</div>
				
				<div class="cinema_text">
					<b>万达影城</b>
					<span>评分：</span>
					<em><sub>7</sub><sup> .5</sup></em>
				</div>
			</div>
			<%-- <c:forEach items="" var="" > --%>
			<div class="user">
				<div class="user_pic">
					<img src="../images/head/default_head.jpg" width="50px">
				</div>
				<div class="user_text">
					<h2>小马锅</h2>
					<p>《博物馆奇妙夜3》：这么多上古凶兽，现在都萌萌</p>
					<p>你故意根本就不该计划并根据客观不哭各板块经过北京国际控股看具体vrctrecresxaerxafe111111</p>
					<p>
						<span class="pulish">发表于
							<span>1015-1-20</span>
						</span>
						<a href="">删除</a>
					</p>
				</div>
			</div>
			<%-- </c:forEach> --%>
		</div>
		
		<div class="more" style="text-align: center; cursor: pointer; visibility: visible; opacity: 1;">加载更多评论数据！</div>
		          
	</div>
	</form>
  </body>
</html>
