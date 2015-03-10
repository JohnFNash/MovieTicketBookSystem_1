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
    
    <title>影厅列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="js/user/jquery-1.11.1.js">
	</script>
	<script type="text/javascript">
		$(function(){
			$("tbody>tr:odd").addClass("odd");
			$("tbody>tr:even").addClass("even");
				
		});
	</script>

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
		/* table css */
		.even{ background-color:#FFF38F;}
		.odd{ background-color:#FFFFEE;}
		table{border-collapse:collapse;}
		thead{background-color:#ccc;}
		.a{ height:30px;}
		.b{ height:30px;}.c{ height:30px;}.d{ height:30px;}
		
		.table{
			/* border: 1px solid pink; */
			width:100%;
			margin-top: 30px;
			float: left;
		}
		.table a{
			text-decoration: none;
			cursor: pointer;
			color: white;
			font-family: "微软雅黑","宋体";
		}
		.table .operate{
			border-radius:5px;
			margin-left:30px;
			padding-top:5px;
			width: 60px;
			height: 25px;
			background-color: #EB6120;
		}
		
		.table .operate1{
			border-radius:5px;
			margin-left:30px;
			padding-top:5px;
			width: 90px;
			height: 25px;
			background-color: #EB6120;
		}
		/* 操作按钮css  */
		.operate2{
			/* border: 1px solid skyblue; */
			margin-top: 20px;
			float: right;
			margin-top:20px;
			margin-right: 100px;
		}
		.blue {  
		    color: #d9eef7;  
		    border: solid 1px #EB6120;  
		    background: #0095cd;  
		    background: -webkit-gradient(linear, left top, left bottom, from(#EB6120), to(#EB6120));  
		    background: -moz-linear-gradient(top, #EB6120,  #EB6120);  
		    filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#EB6120', endColorstr='#EB6120');  
		} 
		.button {  
		    display: inline-block;  
		    zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */  
		    *display: inline;  
		    vertical-align: baseline;  
		    margin: 0 2px;  
		    outline: none;  
		    cursor: pointer;  
		    text-align: center;  
		    text-decoration: none;  
		    font: 14px/100% Arial, Helvetica, sans-serif;  
		    padding: .5em 1em .55em;  
		    text-shadow: 0 1px 1px rgba(0,0,0,.3);  
		    -webkit-border-radius: .5em;   
		    -moz-border-radius: .5em;  
		    border-radius: .5em;  
		    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);  
		    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);  
		    box-shadow: 0 1px 2px rgba(0,0,0,.2);  
		}  
		.button:hover {  
		    text-decoration: none;  
		}  
		.button:active {  
		    position: relative;  
		    top: 1px;  
		}  

		a {  
			    color: #339;  
			    text-decoration: none;  
			}  
		a:hover {  
		    text-decoration: underline;  
		} 
	</style>
  </head>
  
  <body>
    <div class="all">
    		<div class="title">
    			<div class="t1"><h2>我的影院-放映厅</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>影厅列表</span></div>
	   			</div>
    		</div>
    		
    		<div class="table">
				<form action="">
					<table width="670" border="0" align="center">
						  <thead>
						   <tr>
								<td width="92" height="45"><div align="center">放映厅编号</div></td>
								<td width="84"><p align="center" >放映厅</p></td>
								<td width="106"><p align="center" >座位数</p></td>
								<td width="95"><p align="center" >排数</p></td>
								<td width="103"><p align="center" >列数</p></td>
								<td width="103"><p align="center" >操作</p></td>
								<td width="130"><p align="center" >查看座位</p></td>
						  </tr>
						  </thead>
						  <tbody>
						  <c:forEach items="${hallList }" var="hall">
						  	<%	/* private String str[] = {"一","二"}; */	 %>
						  	  <tr>
								<td width="92" height="45"><div align="center">${hall.id }</div></td>
								<td width="84"><p align="center" >${hall.no }号厅</p></td>
								<td><div align="center">${hall.seatCount }</div></td>
						    	<td><div align="center">${hall.row }</div></td>
						    	<td><div align="center">${hall.col }</div></td>
						    	<td><div  class="operate" align="center" ><a href="business/VideoHallServlet?op=delete&hallId=${hall.id }">删除</a></div></td>
							  	<td><div  class="operate1" align="center" ><a href="business/VideoHallServlet?op=showSeat&hallId=${hall.id }">查看座位</a></div></td>
							  </tr>
						  </c:forEach> 
						  
							
						  </tbody>
						</table>
				</form> 
				
				<div class="operate2">
					<a href="business/business_addVideoHall.jsp" class="button blue">AddScreening</a>
					
				</div>   		
    		</div>
   	</div>
  </body>
</html>
