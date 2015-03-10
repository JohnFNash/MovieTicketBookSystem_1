<%@ page language="java" import="java.util.*,com.team3.mbts.util.*,com.team3.mbts.entity.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
  <base href="<%=basePath%>" >
    <title>查看顾客评价</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
    <link rel="stylesheet" type="text/css" href="css/admin/CinemaRemark.css">

    <style type="text/css">
		<!--
		a:link {
			color: #000000;
			text-decoration: none;
		}
		a:visited {
			text-decoration: none;
			color: #000000;
		}
		a:hover {
			text-decoration: underline;
			color: #FF9900;
		}
		a:active {
			text-decoration: none;
		}
		-->
	</style>
	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(function(){
			function selectChange(){
				//得到影院编号
				var id= $("#selectyy").val();
				$("#cinemaId").val(id);
				$("#showremark").submit();
			}
			
			$("#selectyy").change(selectChange);
			$("option").each(function(){
				if($(this).val() == '${cinemaId}'){
					$(this).attr("selected", "selected");
				}
			});
			
			//点击删除评论
			$(".user_text a").click(function(){
				var confirmDelete = confirm("确认删除?");
				if(!confirmDelete) {//不删除
					return;
				}
			
				var index = $(".user_text a").index($(this));
				$.post('admin/DeletCinemaRemarServlet', {id: $(this).attr("lang")}, function(data) {
					if(data == 'success') {
						$("div.user:eq(" + index + ")").remove();
						alert("影院评论删除成功!");
					}
				});
			});
			
			//点击审核评论
			$("span.critic > span").click(function() {
				//获取当前评论在评论列表中的下标
				var index = $("span.critic").index($(this).parent());
				//获取当前评论对应的span
				var $span = $("span.critic:eq('"+index+"')");
				
				//审核评论
				var typeV = $(this).attr("lang");
				var idV = $span.attr("lang");
				$.post('admin/CinemaRemarkCirticServlet', {type:typeV, id: idV}, function(data){
					if(data == 'success') {//审核完成
						$span.find("span").remove();	//移除审核按钮
						if(typeV == 2) {//审核结果为审核通过							
							$span.text("审核通过");
						} else if(typeV == 3) {//审核结果为审核未通过
							$span.text("审核未通过");
						}
					}
				});
			});
		});
	</script>	
  </head>
  <body>
  <form action="admin/ShwoCinemaServlet" method="post" name="showremark" id="showremark">
  	<input type="hidden" name="cinemaId" id="cinemaId"/>
 	 	<div class="box">
  	
    	<div class="title">影院评价列表</div>
		<hr>
		<div class="index">
			<div class="index_left">
				<span>选择影院：</span>
				<select name="selectyy" id="selectyy">
				<c:forEach items="${cas}" var="showremark" >
				<option style="height:25px;" value="${showremark.cinemaId}">${showremark.cinemaName}</option>
				</c:forEach>
				</select>
			</div>
			<!-- <div class="index_right">
				<span>搜索影院：</span>
				<input type="text" placeholder="请输入关键字">
				<input type="submit" value=" 搜索" style="border:none;width: 70px;height: 28px;margin-top: 4px;"><br/>
			</div> -->
		</div>	
				<div class="main">
			<div class="cinema">
				<div class="cinema_pic">
				 	<img src="${cinema.scene }" width="300" height="200">
				</div>	
				<div class="cinema_text">
					<b>${cinema.cinemaName}</b>
					<!-- <span>评分：</span>
					<em><sub>7</sub><sup> .5</sup></em> -->
				</div>
			</div>
			<c:forEach items="${pry}" var="showpre">
			<div class="user">
				<div class="user_pic">
					<img src="${showpre.user.headPath}" width="50px">
				</div>
				<div class="user_text">
					<h2>${showpre.user.account}</h2>
					
					<p>${showpre.content}</p>
					<p>
						<span class="pulish">发表于
							<span>${showpre.time.toString().substring(0,19)}</span>
						</span>
						<c:choose>
							<c:when test="${showpre.status==1}"><!-- 尚未审核 -->
								<span class="critic" lang="${showpre.id}"><span id="criticPass" lang="2">审核通过</span>&nbsp;&nbsp;<span id="criticNotPass" lang="3">审核不通过</span></span>	
							</c:when>
							<c:when test="${showpre.status==2}"><!-- 审核已通过 -->
								<span class="critic">审核已通过</span>
							</c:when>
							<c:when test="${showpre.status==3}"><!-- 审核未通过-->
								<span class="critic">审核未通过</span>
							</c:when>
						</c:choose>																
						<a lang="${showpre.id }" href="javascript:void(0)">删除</a>
					</p>
		
				</div>
			</div>
			</c:forEach>
		</div> 
		
		<div class="more" style="text-align: center; cursor: pointer; visibility: visible; opacity: 1;">加载更多评论数据！</div>
		
	</div>
	</form>
  </body>
  
</html>
