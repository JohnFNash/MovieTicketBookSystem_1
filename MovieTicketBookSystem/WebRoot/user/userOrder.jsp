<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet" type="text/css" href="css/user/userOrder.css">

	<style type="text/css">
		table tbody tr {font-size:13px;}
	</style>
  </head>
  
  <body>
    <div class="top">
		<div class="title">
			<h1>我的订单</h1>
			
			<!-- <div class="select">
				<div class="date">
					<select name="queryDateYear" class="year">
						<option value="2010">2010年</option>
						<option value="2011">2011年</option>
						<option value="2012">2012年</option>
						<option value="2013">2013年</option>
						<option value="2014">2014年</option>
						<option value="2015" selected="selected">2015年</option>
					</select>	
					<select name="queryDateYear" class="year">
						<option value="1" selected="selected">1月</option>
						<option value="2">2月</option>
						<option value="3">3月</option>
						<option value="4">4月</option>
						<option value="5">5月</option>
						<option value="6">6月</option>
						<option value="7">7月</option>
						<option value="8">8月</option>
						<option value="9">9月</option>
						<option value="10">10月</option>
						<option value="11">11月</option>
						<option value="12">12月</option>
					</select>	
				</div>
				
				<a class="query" href="">
					<span>查询</span>
				</a>
			</div> -->
		</div>
		
		<div>
			<form name="orderForm" action="user/ShowUserOrderServlet" method="post">
				<%List<Integer> values = Arrays.asList(5,10,20); pageContext.setAttribute("values", values);%>
				<myTag:page form="orderForm" pageBean="${pageBean}" optionValues="${values}"></myTag:page>
			</form>
			<table style="width:880px; border:0;" class="orderTable">
				  <thead>
					  <tr height="50px" style="font-weight: bold;">
						<td width="130"><p align="center" >订单编号</p></td>
						<td width="164"><p align="center" >下单日期</p></td>
						<td width="180"><p align="center" >影片名称</p></td>
						<td width="206"><p align="center" >影厅</p></td>
						<td width="112"><p align="center" >票价</p></td>
						<td width="164"><p align="center" >查看座位</p></td>
						<td width="164"><p align="center" >订单状态</p></td>
					  </tr>
				  </thead>
				  <tbody>				  	
				  	<c:forEach items="${pageBean.dataList}" var="order">
					  	 <tr>
					    	<td height="60"><div align="center">${order.id }</div></td>
					    	<td><div align="center">${order.time.toLocaleString().substring(0,order.time.toLocaleString().length()-3) }</div></td>
						    <td><div align="center">${order.screenings.movie.name }</div></td>
						    <td><div align="center">${order.screenings.videoHall.cinema.cinemaName}${order.screenings.videoHall.no }号厅</div></td>
						    <td><div align="center">${order.screenings.price }</div></td>
						    <td width="164"><p align="center" ><a href="user/ShowOrderSeatServlet?&orderId=${order.id}" target="_blank">查看座位</a></p></td>
							<td><div align="center">${order.status==true ? '已完成':'待付款' }</div></td>
					 	 </tr>
				  	</c:forEach>
				  </tbody>
			</table>
		</div>		
	</div>
  </body>
</html>
