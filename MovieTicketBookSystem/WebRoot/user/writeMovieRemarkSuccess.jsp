<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>评论发表成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<style type="text/css">
		.content {width:600px; min-height:442px; margin:0 auto; margin-top:30px;}
		a.detail {padding: 5px 15px; border:1px solid #ccc; cursor:pointer; border-radius:3px; text-decoration: none; color:#000;}
	</style>
  </head>
  
  <body>
    <div class="content">
    	<div style="height:100px;overflow:hidden;line-height:100px;font-size:28px;font-weight:bold;margin-top:10px;">
    		<img height="100" style="float:left;" src="images/other/register_succ.png">评论，已发布成功！
    	</div>
    	<div>
    		<span>您的评论可能要经过一段时间才会显示在电影详情页上。</span>
    		<a class="detail" href="user/GetMovieRemarkServlet?movieRemarkId=${param.id}">查看评论详情</a>
    	</div>
    </div>
    
    <!-- 底部 -->
	<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
