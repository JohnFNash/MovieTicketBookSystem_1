<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加放映厅</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="css/business/addVideoHall.css">
	
	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/business/addVideoHall.js"></script>
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
		/* 选择条件 选座 css  */
		.content{
			/* border: 1px solid blue; */
			float:left;
			width:100%;
			margin-top: 30px;
		}
		
		.content label{
			font-weight: bold;
		}
	</style>
	
  
  </head>
  
  <body>
    <div class="all">
    		<div class="title">
    			<div class="t1"><h2>我的影院-放映厅</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>添加影厅</span></div>
	   			</div>
	    	</div>
		   <div class="content">
		    	<form action="business/VideoHallServlet" method="post">
		    		<label>放映厅编号:</label><input type="text" placeholder="请输入影厅编号" name="no"  size="10px;" />
		    		<label>放映厅行数:</label><input type="text" name="row"  width="80px" maxlength="2" placeholder="请输入10-20的数字"/>
		    		<label>放映厅列数:</label><input type="text" name="col"  width="80px" maxlength="2" placeholder="请输入10-20的数字"/>
		    		<input type="button" value="开始选座" id="startSelect" />
		    		<!-- 座位列表 -->
		    		<div class="seatInfo">
						<span><span class="un">&nbsp;&nbsp;&nbsp;</span>没座位</span>
						<span><span class="on">&nbsp;&nbsp;&nbsp;</span>有座位</span>
					</div>
		    		<div class="seatList" id="seatList">
		    			
		    		</div>  
		    		<input type="hidden" name="seat" />
		    		<div class="footer">
		    			<input type="submit" value="提交座位信息" />
		    			<input type="button" value="重置" id="resetBtn" />
		    		</div>  	    		
		    	</form>
		    </div>
	 </div>
  </body>
</html>
