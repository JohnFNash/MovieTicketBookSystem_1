<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单列表</title>
    
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
		/* 表格css   */
		.even{ background-color:#FFF38F;}
		.odd{ background-color:#DBEAF9;}
		table{border-collapse:collapse;}
		thead{background-color:#ddd;}
		.a{ height:30px;}
		.b{ height:30px;}.c{ height:30px;}.d{ height:30px;}
			
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
		
		/* 订单css  */
		.indent{
			/* border: 1px solid pink; */
			float: left;
			width: 100%;
			margin-top: 20px;
		}
		
		.indent .select{
			float:left;
			width:100%;
			height:40px;
		/* 	border: 1px solid pink; */
			/* background-color: #ddd; */
			
		}
		.indent .select form{
			text-align: right;
			padding-right: 53px;
		}
		
		.table{
			float: left;
			margin-left: 55px;
		}
		
		.pageGroup{
			float: left;
			margin-top:25px;
			margin-left: 15px;
		}
	</style>
	
		<script type="text/javascript">
		$(function(){
			
			$("#select option").each(function(){
				if($(this).val()=='${value}')
				{
					$(this)[0].selected=true;
				}
				
			});
			
			
			$("input[type='text']").val('${key}');
			
			$("#select").change(function(){
					$(this).attr("id");		
					var value = $("#select").val();
						
				
						location.href="business/StatisticsServlet?op=order&value="+value;
					
			});
			
			
			
		});
		
	</script>
	
  </head>
  
  <body>
    	<div class="all">
    		<div class="title">
    			<div class="t1"><h2>我的影院-统计信息</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>订单列表</span></div>
	   			</div>
    		</div>
    		
    		<div class="indent">
    			<div class="select">
    				<form action="business/StatisticsServlet?op=orderSearch" method="post">
   						<select style="height: 28px;" id="select">	
   							<option value="1">近一周</option>
   							<option value="2">近一个月</option>
   							<option value="3">近三个月</option>
   							<option value="4">近一年</option>
   						</select>
    					<input type="text" placeholder="按订单号、片名查询" name="key" style="height: 22px;"/><input type="submit"  value="查询" style="height: 25px;"/>
    				</form>
    			</div>
    			<div class="table">
    				<table width="780px;" border="0" align="center">
						  <thead>
							  <tr height="50px" style="font-weight: bold;">
								<td width="130"><p align="center" >订单编号</p></td>
								<td width="164"><p align="center" >下单日期</p></td>
								<td width="180"><p align="center" >影片名称</p></td>
								<td width="106"><p align="center" >影厅</p></td>
								<td width="112"><p align="center" >票价</p></td>
								<td width="164"><p align="center" >用户名</p></td>
								<td width="164"><p align="center" >订单状态</p></td>
							  </tr>
						  </thead>
						  <tbody>
						  	<c:forEach items="${pageBean.dataList }" var="order">
							  	 <tr>
							    	<td height="60"><div align="center">${order.id }</div></td>
							    	<td><div align="center">${order.time.toLocaleString().substring(0,order.time.toLocaleString().length()-3) }</div></td>
								    <td><div align="center">${order.screenings.movie.name }</div></td>
								    <td><div align="center">${order.screenings.videoHall.no }号厅</div></td>
								    <td><div align="center">${order.screenings.price }</div></td>
								    <td><div align="center">${order.user.account }</div></td>
									<td><div align="center">${order.status==true ? '已完成':'待付款' }</div></td>
							 	 </tr>
						  	</c:forEach>
						  	
						  	<!-- 查询结果 -->
						  	<c:forEach items="${orderList }" var="order">
							  	 <tr>
							    	<td height="60"><div align="center">${order.id }</div></td>
							    	<td><div align="center">${order.time.toLocaleString().substring(0,order.time.toLocaleString().length()-3) }</div></td>
								    <td><div align="center">${order.screenings.movie.name }</div></td>
								    <td><div align="center">${order.screenings.videoHall.no }号厅</div></td>
								    <td><div align="center">${order.screenings.price }</div></td>
								    <td><div align="center">${order.user.account }</div></td>
									<td><div align="center">${order.status==true ? '已完成':'待付款' }</div></td>
							 	 </tr>
						  	</c:forEach>
			
						  </tbody>
					</table>
				</div>
				
				<div class="pageGroup"> 
					
		    		<form action="business/StatisticsServlet?op=order" method="post" name="remarkPage">
							<div class="page">
								<myTag:page form="remarkPage" pageBean="${pageBean }">
								</myTag:page>
							</div>
					</form>
    			</div>
				</div>
    		</div>
    	
  </body>
</html>
