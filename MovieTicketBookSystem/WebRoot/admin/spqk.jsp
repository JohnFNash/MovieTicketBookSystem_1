<%@ page language="java" import="java.util.*,com.team3.mbts.util.*,com.team3.mbts.entity.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
	.even{ background-color:#CCCCCC;}
	.odd{ background-color:#DBEAF9;}
	table{border-collapse:collapse;}
	thead{background-color:#66a9bc;}
	.top{ width:709px; margin:auto;}
	.movie_yp{ float:right;}
.STYLE7 {font-size: 20px; font-family: "Times New Roman", Times, serif;}
</style>
<script type="text/javascript" src="js/user/jquery-1.11.1.js">
</script>
<script type="text/javascript">
	$(function(){
		$("tbody>tr:odd").addClass("odd");
		$("tbody>tr:even").addClass("even");
			
	});
</script>
</head>

<body>
<form action="" method="post" name="show">
<div class="top">
  <h1 align="left">售票统计</h1>
      <div align="left">
        <p><span>请选择：
            <span>
            <select name="spqkselect" style="width:100px; height:25px;">
                <option style=" height:25px;">电影院名</option>
                <option style=" height:25px;">电影名</option>
            </select>
            </span>
        <input name="spqktext" type="text" style="width:90px; height:21px;" placeholder="请输入搜索关键词" />
          <input type="submit"  value=" 筛选" style=" height:26px;"/>
        <span class="movie_yp">
         &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
        <select name="spqkpx" style="width:150px; height:25px;">
          <option style=" height:25px;">按日期(由近到远)</option>
          <option style=" height:25px;">按销售额(由高到低)</option>
        </select>
		</span>
        </p><br>
      </div>
</div>
<table width="717" border="0" align="center">
  <thead>
	  <tr>
		<td width="122" height="60"><div align="center" class="STYLE7">电影院编号</div></td>
		<td width="102"><p align="center" class="STYLE7" >电影院名</p></td>
		<td width="102"><p align="center" class="STYLE7" >电影名</p></td>
		<td width="91"><p align="center" class="STYLE7" >电影编号</p></td>
		<td width="79"><p align="center" class="STYLE7" >售价</p></td>
		<td width="85"><p align="center" class="STYLE7" >售票数</p></td>
		<td width="106"><p align="center" class="STYLE7" >销售额</p></td>
	  </tr>
  </thead>
  <tbody>
  <tr>
    <td height="53"><div align="center">001</div></td>
    <td><div align="center">很电影院</div></td>
    <td><div align="center">智取威虎山</div></td>
    <td><div align="center">900</div></td>
    <td><div align="center">￥12</div></td>
    <td><div align="center">1100</div></td>
    <td><div align="center">￥99999</div></td>
  </tr>
  <tr >
    <td height="65"><div align="center">002</div></td>
    <td><div align="center">横电影院</div></td>
    <td><div align="center">重返20岁</div></td>
    <td><div align="center">990</div></td>
    <td><div align="center">￥22</div></td>
    <td><div align="center">2100</div></td>
    <td><div align="center">￥99998</div></td>
  </tr>
  <tr >
    <td height="68"><div align="center">003</div></td>
    <td><div align="center">UTF影院</div></td>
    <td><div align="center">十万个冷笑话</div></td>
    <td><div align="center">991</div></td>
    <td><div align="center">￥32</div></td>
    <td><div align="center">3100</div></td>
    <td><div align="center">￥99997</div></td>
  </tr>
  <tr >
    <td height="60"><div align="center">004</div></td>
    <td><div align="center">小风影院</div></td>
    <td><div align="center">一代宗师</div></td>
    <td><div align="center">992</div></td>
    <td><div align="center">￥42</div></td>
    <td><div align="center">4100</div></td>
    <td><div align="center">￥99996</div></td>
  </tr>
  </tbody>
</table>
</form>
</body>
</html>
