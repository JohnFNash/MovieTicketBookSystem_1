<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'business_showSeat.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/business/addVideoHall.css">
	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>
	<!-- <script type="text/javascript" src="js/business/showSeat.js"></script> -->
	<script type="text/javascript" src="js/business/json2.js"></script>
	
	<style type="text/css">
	/* 整体框架css  */
		.all{
			width: 95%;
			height: 100%;
			/* border: 1px solid orange; */
		}
		/* 标题css  */
		.title{
			width: 100%;
			height: 100px;
			
			/* border: 1px solid pink; */
		}
		.title .t1{
			width:100%;
			height: 55px;
			float: left;
			text-align:center;
			/* border: 1px solid red; */
		}
		.title .m10{
			width:100%;
			height: 42px;
			margin-top:40px;
			border-bottom:2px solid #EB6120;
			float: left;
			
		}
		.title .m10 .m11{
			width: 120px;
			height: 42px;
			float: left;
			background-color:#EB6120;
			color:white;
			font-family: "微软雅黑","宋体";
		    font-size: 16px;
		    font-weight: bold;
		}
		.title .m10 .m11 span{
			position: relative;
			top: 10px;
			left: 27px;
		}
	
		.content{
			float: left;
			width:100%;
			margin-top: 20px;
		}
		
		.content .button{
			margin-top: 20px;
		}
		.content .button .but{
			background-color: #EB6120;
			height: 25px;
			width: 50px;
			font-family: "微软雅黑","宋体";
			float: right;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			var $divNode = $("#seatList");
		
			var rows = '${videoHall.row}';
			var cols = '${videoHall.col}';
			
			var flag = true; 
			for(var i=1; i<=rows; i++) {											
				var $dlNode =$("<dl></dl>");
				$divNode.append($dlNode);
				for(var j=1; j<=cols; j++) {	
					var $ddNode = $("<dd><div class='un'></div></dd>");
					$dlNode.append($ddNode);
					$ddNode.attr("title", i + "排" + j +"列");							    	
		    	};
	       };
	       
	       <c:forEach items="${seatList }" var="seat">
	       		var $dd = $("dl:eq(${seat.row-1}) > dd:eq(${seat.col-1})"); 
	       		$dd.find("div").removeClass("un");
	       		$dd.find("div").addClass("on");				
    		</c:forEach>
		});
	</script>
	
  </head>
  
  <body>
    <div class="all">
    		<div class="title">
    			<div class="t1"><h2>我的影院-影厅座位分布</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>座位分布</span></div>
	   			</div>
    		</div>
    		
	    	<div class="content">
	   			<!-- 座位列表 -->
		    	<div class="seatInfo">
						<span><span class="un">&nbsp;&nbsp;&nbsp;</span>没座位</span>
						<span><span class="on">&nbsp;&nbsp;&nbsp;</span>有座位</span>
				</div>
				
	    		<div class="seatList" id="seatList">
			    	
			   	</div> 
			   	
			   	<div class="button">
			   		<input type="button" class="but" value="返回" onclick="location.href='business/VideoHallServlet?op=hallList'"/>
			   	</div>
		   	</div>
    </div>
  </body>
</html>
