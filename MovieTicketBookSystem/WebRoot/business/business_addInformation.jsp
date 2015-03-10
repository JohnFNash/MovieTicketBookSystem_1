<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>"/>
    <title>添加影院</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="css/business/business_information.css">
	<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/business/json2.js"></script>
	<script type="text/javascript" src="js/business/loadDistricts.js"></script>
	
	<script type="text/javascript">
		$(function(){						
			 $("#submit").click(function(){
			 	var empty = false;
				$("input[type='text']").each(function(){
					if($(this).val() =='')
					{
				
						empty = true;
					}
				});
				
				$(".tarea").each(function(){
					if($(this).val()=='')
					{
			
						empty = true;
					}
				});
				
				var logo = '${cinema.logo }';
				var logofile = $("input[type='file']:eq(0)").val();
				
				if(logo==''&&logofile=='')
				{
				
					empty = true;
				}
				var scene = '${cinema.scene}';
				var scenefile = $("input[type='file']:eq(1)").val();
				
				if(scene==''&&scenefile=='')
				{
					
					empty = true;
				}			
				return !empty;
			}); 
			
		});
	</script>
  </head>
  
  <body>
  	<div class="box">
  		
  		<form action="business/BusinessInformationServlet?op=addCinema"  method="post" enctype="multipart/form-data">
  		<div class="head">
	  		<div class="logo">
	  			<div class="img"><img src="" width="120px" height="50px"/></div>
	  			<div class="c1">上传logo:</div>
				<div class="c2"><input class="file" type="file" name="logoPhoto"/></div>
	  		</div>
	  		<div class="title"><h2>添加影院</h2></div>
  		</div>
		
    	<div class="top">
			<table class="table" cellspacing="10px" >
				<tr>
					<td width="240px;" height="180px;" rowspan="4" >
						<img class="pic" width="260" height="160" src=""/>
					</td>
					<td width="65px">
						名称：
					</td>
					<td>
						<input class="input" name="cinemaName" type="text"/>
					</td>
				</tr>
				<tr>
					<td >
						行政区：
					</td>
					<td>
						<!-- <input class="input" name="district" type="text" /> -->
						<select name="firstLevelArea">
							<c:forEach items="${firstLevelAreaList}" var="area">
								<option value="${area.areaId}">${area.areaName}</option>
							</c:forEach>
						</select>
						<select name="secondLevelArea">
							<c:forEach items="${secondLevelAreaList}" var="area">
								<option value="${area.areaId}">${area.areaName}</option>
							</c:forEach>
						</select>
						<select name="thirdLevelArea">
							<c:forEach items="${thirdLevelAreaList}" var="area">
								<option value="${area.areaId}">${area.areaName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td >
						地址：
					</td>
					<td>
						<input class="input" name="address" type="text" />
					</td>
				</tr>
				<tr>
					<td>
						电话：
					</td>
					<td>
						<input class="input" type="text" name="tel" />
					</td>
				</tr>
				<tr>
					<td>
						<div class="m1">上传图片:</div>
						<div class="m2"><input class="file" type="file" name="cinemaPhoto"/></div>
					</td>
					<td>
						特色：
					</td>
					<td>
						<textarea class="textarea tarea" name="special" style="resize:none;"></textarea>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="bottom">
			<div class="bottom_title">
				<div class="jieshao">
					<span>影院介绍</span>
				</div>
			</div>
			
			<textarea class="textarea_2 tarea" name="description" > </textarea>
			
			<div class="btn">
				<input id="submit" type="submit" value="确认添加" width="80px" height="40px">
			</div>
		</div>
	</form>		
	</div>
  </body>
</html>

