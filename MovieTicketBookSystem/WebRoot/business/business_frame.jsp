<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商家操作页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="StyleSheet" href="js/business/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="js/business/dtree/dtree.js"></script>
	
	<style type="text/css">
		/* body css */
		body{
			 /* background-image: url(./images/other/sidai.jpg); */
			 background-color: #D7D7D7;
		}
		/* 顶部css  */
		.top{
			width: 1280px;
			margin:0 auto;
			height: 50px;
			background-color: #EB6120;
		}
		.top .user{
			float: left;
			margin-left: 80px;
			margin-top: 18px;
		}
		.top .user span{
		
			font-size: 18px;
		}
		/* 主体 css  */
		.all{
			width: 1280px;
			height: 1480px;
			margin:50px auto;
			background-color: white;
			/* border: 1px solid blue; */
		}
		/* 左边css  */
		.left{
			width: 270px;
			height: 100%;
			float:left;
			background-color: #FFF6EE;
			/* border: 1px solid green; */
		}
		
		.top_logo {
		    height: 100%;
		    margin: 0 auto;
		    position: relative;
		    width: 1190px;
		    z-index: 0;
		}
		.top_logo a {
		    color: #fff;
		}
		.top_logo .logo img {
			margin-top:-41px;
			background-clip:
		    background: url("images/other/logos_white.png?v=0001") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
		    border: 0 none;
		    float: left;
		    height: 286px;
		}
		.dtree{
			font-size: 16px;
			margin-left: 20px;
			font-size: 16px;
			margin-top: 30px;
			letter-spacing: 2px;
		}
		/* 右边css  */
		.right{
			width: 950px;
			height: 100%;
			float:right;
			/* border: 1px solid orange;  */
		}
	
	</style>

  </head>
  
  <body>
  		<div class="top">
     		<div class="user">
     			<span>${seller.account}欢迎您！,</span>
     			<span><a href="business/loginServlet?op=exit" style="text-decoration: none;">注销</a></span>
     			<span style="font-size: 16px;font-weight: bold;color: blue;">
     				<c:if test="${cinema==null }">
     					&nbsp;&nbsp;&nbsp;您还没有影院信息，请添加！
     				</c:if> 
     			</span>
     		</div>	
     	</div>
     	
     	<div class="all" >
     		
	   		<div class="left">
	   				<div class="dtree">
	   				<script type="text/javascript">
								
					d = new dTree('d');
			
					d.add(0,-1,'商家影院信息管理');
					d.add(1,0,'影院首页','business/business_index.jsp',null,'contentFrame');
					d.add(2,0,'影片管理');
					d.add(3,2,'影片列表','business/MovieServlet?op=mList',null,'contentFrame');
					d.add(5,0,'排期管理');
					d.add(6,5,'排期信息','business/ScreeningServlet?op=listing',null,'contentFrame');
					d.add(7,0,'统计信息');
					d.add(9,7,'售票统计','business/StatisticsServlet?op=ticket',null,'contentFrame');
					d.add(10,7,'订单列表','business/StatisticsServlet?op=order',null,'contentFrame');
					d.add(11,7,'评价列表','business/StatisticsServlet?op=evaluate',null,'contentFrame');
					d.add(12,0,'放映厅');
					d.add(13,12,'放映厅列表','business/VideoHallServlet?op=hallList&cinemaId=${cinema.cinemaId} ',null,'contentFrame');
					d.add(14,12,'添加放映厅','business/VideoHallServlet?op=addHall', null, 'contentFrame');
					d.add(15,0,'商家信息管理');
					d.add(16,15,'账户管理','business/business_account.jsp',null,'contentFrame');
					d.add(17,15,'影院信息','business/BusinessInformationServlet?op=cinemaInfo',null,'contentFrame');
					document.write(d);
					
				</script>
				</div>
	   		</div>
	   			
	   		<div class="right">
	   				<iframe name="contentFrame" src="business/business_index.jsp" style="width: 100%; height: 100%;border: none;">
					
					</iframe>
	   		</div>
     	</div>
  </body>
</html>
