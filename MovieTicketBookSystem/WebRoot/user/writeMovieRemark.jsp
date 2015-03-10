<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>发表评论 - 格瓦拉生活网</title>
		
		<link rel="stylesheet" href="css/user/writeMovieRemark.css" />	
		
		<link rel="stylesheet" href="js/business/kindeditor/themes/default/default.css" />
		<link rel="stylesheet" href="js/business/kindeditor/plugins/code/prettify.css" />
		
		<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>
		<script charset="utf-8" src="js/business/kindeditor/kindeditor.js"></script>	
		<script charset="utf-8" src="js/business/kindeditor/lang/zh_CN.js"></script>	
		<script charset="utf-8" src="js/business/kindeditor/plugins/code/prettify.js"></script>		
		
		<script type="text/javascript" src="js/user/loadKindeditor.js"></script>
	
		<script type="text/javascript" src="js/user/writeMovieRemark.js"></script>		
	</head>
	
	<body>
		<!-- 导航栏 -->
    	<jsp:include page="nav.jsp"></jsp:include>
    	
    	<!-- 具体内容 -->
    	<div class="content">
    		<div class="mod_hd">
				<a class="picmin" title=""><img width="30" height="30" src="images/head/default_head.jpg" alt="smileagain"></a>
				<span>评</span>
				<% String movieName =request.getParameter("movieName"); movieName=new String(movieName.getBytes("iso-8859-1"),"utf-8");%>
				<a title="重返20岁" target="_blank" href="user/GetMovieServlet?movieId=${param.movieId}"><%=movieName %></a>
			</div>
			<form name="remarkForm" action="user/WriteMovieRemarkServlet" method="post">
				<div class="modify_alt">
					<span class="left">打个分吧：</span>
					<ul class="ui_big_ratings">
						<li lang="不推荐，不考虑" class="" id="1"></li>
						<li lang="不推荐，不考虑" id="2" class="no"></li>
						<li lang="不推荐，不考虑" class="" id="3"></li>
						<li lang="凑合，别无选择可考虑" id="4" class="no"></li>
						<li lang="凑合，别无选择可考虑" class="" id="5"></li>
						<li lang="不错，值得考虑" id="6" class="no"></li>
						<li lang="不错，值得考虑" class="" id="7"></li>
						<li lang="很棒，推荐大家去" id="8" class="no"></li>
						<li lang="很棒，推荐大家去" class="" id="9"></li>
						<li lang="最佳，绝对首选" id="10" class="no"></li>
					</ul>
					<sub class="myPoint"></sub>
					<input type="hidden" name="point" value="7.0" />
					<span class="brown left"></span>
				</div>
				<div class="remarkArea">
					<div class="title">
						<label for="title">标题：</label><input type="text" name="title" maxlength="25" placeholder="标题(不多于25个字)" />
					</div>
					<div class="remark_content">
						<br/><h4>内容：</h4>
						<textarea id="editor_id" name="content" maxlength="4000" placeholder="请输入评论(不多于4000个字)"></textarea>
					</div>
					<input type="hidden" name="movieId" value="${param.movieId}"/>
					<input class="redBt right mt10" type="submit" value="发&nbsp;布" />
				</div>				
			</form>
    	</div>
    	
    	<!-- 底部 -->
  		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>