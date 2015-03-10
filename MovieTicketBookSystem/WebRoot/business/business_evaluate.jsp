<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags"  prefix="myTag"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>影院评论列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>
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
		/* 评价列表 css  */
		.evaluateList{
			/* border: 1px solid green; */
			float: left;
			width: 100%;
		}
		
		.evaluateList ul li {
			list-style-type: none;
		}
		.evaluateList .select{
			background-color: #ddd;
			height: 40px;
			padding-top:12px; 
		}
		.evaluateList .select a{
			text-decoration: none;
		}
		.evaluate  {
			float:left;
			width:100%;
			margin-top:10px;
			border-bottom:1px solid #ddd;
		}
		.evaluateList .pic{
			float: left;
			 /* border: 1px solid green;  */
		}
		.evaluateList .text{
			float: left;
			/*  border: 1px solid green; * */
		}
		.evaluateList .text p{
			color: #999;
		}
		
		.evaluateList .text .con{
			color: #444;
		}
		/* 分页 */
		.pageBean {
			float:left;
			margin-top: 15px;
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
			
			$("#select").change(function(){
					$(this).attr("id");		
					var value = $("#select").val();
								
					location.href="business/StatisticsServlet?op=evaluate&value="+value;
			});
			
			
			
		});
		
	</script>
  </head>
  
  <body>
  		<div class="all">
    		<div class="title">
    			<div class="t1"><h2>我的影院-统计信息</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>评价列表</span></div>
	   			</div>
    		</div>
    		
    		<div class="evaluateList">
    		<form action="">
    			<div class="select">
    				<table>
    					<tr>
	    				<td align="center" width="160px">
	    					查询：
		    				<select style="height: 22px;" id="select">	
		   							<option value="1">近三个月内</option>
		   							<option value="2">今年内</option>
		   							<option value="3">2014内</option>
		   							<option value="4">2013内</option>
		   					</select>
		   				</td>
	   					</tr>
   					</table>
    			</div>
    			<ul>
    			
    				<c:forEach items="${pageBean.dataList }" var="evaluate">
    						
    				<li>
    					<div class="evaluate">
    						<div class="pic">
								<img src="${evaluate.user.headPath }" width="50" height="50">
							</div>
							<h4>${evaluate.user.account }</h4>
							<div class="text">
								
								<p class="con">${evaluate.content }</p>
								<p>
									发表于:
									<span>${evaluate.time.toLocaleString() }</span>
								</p>
							</div>
    					</div>
    				</li>
    				</c:forEach>
    				
    			</ul>
    		</form>
    		
    		<div class="pageBean">
    		<form action="business/StatisticsServlet?op=evaluate" method="post" name="remarkPage">
					<div class="page">
						<myTag:page form="remarkPage" pageBean="${pageBean }">
						</myTag:page>
					</div>
			</form>
    		</div>
    	</div>
    	
  </body>
</html>
