<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的订单_格瓦拉生活网</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="css/user/personIndex.css">

  </head>
  
  <body>
    <!-- 顶部 -->
    <jsp:include page="nav.jsp"></jsp:include>
    
    <!-- 内容 -->
    <div class="fixed_content">
    	<!-- 侧边栏 -->
    	<div class="ui_left">
    		<div class="userInfo">
				<a title="${user.account}" href="javascript:void(0);" target="_blank" class="pic">
					<img width="50" height="50" src="${user.headPath}" alt="${user.account}">
				</a>
				<a title="${user.account}" href="javascript:void(0);" class="ui_oneLine">${user.account}</a>
				<hr>
				<p><a href="user/ShowUserOrderServlet" target="contentFrame">我的订单</a></p>
				<p><a href="user/ShowUserMovieRemarkServlet" target="contentFrame">我的影评</a></p>
				<p><a href="user/ShowUserCinemaRemarkServlet" target="contentFrame">我的院评</a></p>
				<p><a href="user/userPwd.html" target="contentFrame">修改密码</a></p>
			</div>
			
    	</div>
    	
    	<!-- 右边 -->
    	<div class="ui_right">
			<iframe name="contentFrame" src="user/ShowUserOrderServlet" class="contentFrame">
					
			</iframe>    		
    	</div>
    </div>    
    
    <!-- 底部 -->
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
