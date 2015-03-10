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
    
    <title>我的影院评价</title>
    
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
			<h1>我的影院评价</h1>					
		</div>
		
		<div>
			<form name="orderForm" action="user/ShowUserCinemaRemarkServlet" method="post">
				<%List<Integer> values = Arrays.asList(5,10,20); pageContext.setAttribute("values", values);%>
				<myTag:page form="orderForm" pageBean="${pageBean}" optionValues="${values}"></myTag:page>
			</form>
			<table style="width:880px; border:0;" class="orderTable">
				  <thead>
					  <tr height="50px" style="font-weight: bold;">
						<td width="80"><p align="center" >评论编号</p></td>
						<td width="180"><p align="center" >影院名称</p></td>						
						<td width="302"><p align="center" >评论内容</p></td>
						<td width="164"><p align="center" >评论时间</p></td>
						<td width="84"><p align="center" >评论状态</p></td>
					  </tr>
				  </thead>
				  <tbody>				  	
				  	<c:forEach items="${pageBean.dataList}" var="remark">
					  	 <tr>
					    	<td height="60"><div align="center">${remark.id }</div></td>
					    	<td><div align="center">${remark.cinema.cinemaName}</div></td>
						    <td><div align="left">${remark.content}</div></td>
						    <td><div align="center">${remark.time.toString().substring(0,19)}</div></td>
							<td>
								<div align="center">
									<c:choose>
										<c:when test="${remark.status == 1 }">尚未审核</c:when>
										<c:when test="${remark.status == 2 }">审核通过</c:when>
										<c:otherwise>审核未通过</c:otherwise>
									</c:choose>
								</div>
							</td>
					 	 </tr>
				  	</c:forEach> 
				  </tbody>
			</table>
		</div>		
	</div>
  </body>
</html>
