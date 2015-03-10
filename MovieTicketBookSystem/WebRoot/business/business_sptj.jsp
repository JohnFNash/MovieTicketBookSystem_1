<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>售票统计</title>
<style type="text/css">
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
		
		/* 选择条件css  */
		.option{
			float: left;
			width: 100%;
		}
		.option .op1{
			padding-left: 60px;
		}
		.option .op2{
			padding-left: 60px;
		}
		.option .op1 p{
			font-weight: bold;
		}
		.option .op2 p{
			font-weight: bold;
		}
		
		/* 分页css  */
		.pageGroup{
			float: left;
			width: 100%;
			margin-top: 15px;
		}
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
<div class="all">
    		<div class="title">
    			<div class="t1"><h2>我的影院-统计信息</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>售票统计</span></div>
	   			</div>
    		</div>
	<div  class="option">
		  <div class="op1">
		      <p>请选择：
		        <select name="select" style="width:190px; height:32px;">
		          <option class="b">按影片名查看</option> 
		          <option class="a">按放映厅查看</option>
		                  
		        </select>
		        <input name="text" type="text" style="width:120px; height:25px;" placeholder="请输入影片关键词" />
				<input type="submit"  value=" 搜索" style="height: 25px;"/>
		 	 </p>
		  </div>
		  <div class="op2">
		      <p><span class="movie_yp"><span>排序方式</span>
		          <select name="select3" style="width:190px; height:30px;">
		            <option class="c">近一周</option>
		            <option class="d">近一个月</option>
		            <option class="e">近三个月</option>
		            <option class="d">--全部--</option>
		          </select>
		      </span>
		        <!-- <input type="submit"  value="筛选"/> -->
		      </p>
		   </div>
	</div>
	<div class="pageGroup">
		<div>分页条</div>
	</div>
		<table width="780" border="0" align="center">
		  <thead>
			  <tr>
				<td width="120" height="50"><div align="center">影片/影厅</div></td>
				<td width="95"><p align="center" >总场次</p></td>
				<td width="95"><p align="center">上座率</p></td>
				<td width="95"><p align="center">均价</p></td>
				<td width="110"><p align="center">总售票数</p></td>
				<td width="110"><p align="center">总销售额</p></td>
			  </tr>
		  </thead>
		  <tbody>
		  <tr>
		    <td height="60"><div align="center">智取威虎山</div></td>
		    <td><div align="center">125</div></td>
		    <td><div align="center">19.8%</div></td>
		    <td><div align="center">￥39.8</div></td>
		    <td><div align="center">1100</div></td>
		    <td><div align="center">￥99999</div></td>
		  </tr>
		  <tr >
		    <td height="60"><div align="center">三号厅</div></td>
		    <td><div align="center">198</div></td>
		    <td><div align="center">17.6%</div></td>
		    <td><div align="center">￥40.5</div></td>
		    <td><div align="center">2100</div></td>
		    <td><div align="center">￥99998</div></td>
		  </tr>
		  <tr >
		    <td height="60"><div align="center">五号厅</div></td>
		    <td><div align="center">19:15</div></td>
		    <td><div align="center">十万个冷笑话</div></td>
		    <td><div align="center">￥32</div></td>
		    <td><div align="center">3100</div></td>
		    <td><div align="center">￥99997</div></td>
		  </tr>
		  <tr >
		    <td height="60"><div align="center">四号厅</div></td>
		    <td><div align="center">21:15</div></td>
		    <td><div align="center">一代宗师</div></td>
		    <td><div align="center">￥42</div></td>
		    <td><div align="center">4100</div></td>
		    <td><div align="center">￥99996</div></td>
		  </tr>
		  </tbody>
		</table>
	</div>
</body>
</html>
