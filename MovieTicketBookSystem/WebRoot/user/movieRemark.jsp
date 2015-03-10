<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>影片评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="css/user/movieRemark.css">		
  </head>
  
  <body>
    <!-- 导航栏 -->
    <jsp:include page="nav.jsp"></jsp:include>
    
    <!-- 评论内容 -->
    <div class="content">
    	<div class="mod_hd">
    		<h2>${movieRemark.title}</h2>
    		<h3>
    			<img src="${movieRemark.user.headPath}" width="30px" height="30px" />
    			<span>${movieRemark.user.account}</span>&nbsp;<span class="remark">评</span>&nbsp;&nbsp;<span>${movieRemark.time.toString().substring(0,19)}</span>
    		</h3>
    	</div>
    	
    	<div class="mod_bd">
    		${movieRemark.content}
    	</div>
    </div>
	
	<!-- 底部 -->
	<jsp:include page="footer.jsp"></jsp:include>    
  </body>
</html>
