<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>影片排期列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		
		/* 时间选择css  */
		.ticket_choose {
			float:left;
		    padding: 20px 10px;
		}
		.ticket_choose .ui_pic {
		    color: #999;
		    line-height: 35px;
		    margin-right: 0;
			display:inline;
			width:120px;
			position:relative;
			top:-50px;
			font-size:1em;
		}
		.ticket_choose .ui_text {
			display:inline-block;
			width:700px;
			margin-top:10px;
			margin-left:-5px;
		}
		.ticket_choose a {
		    border-radius: 3px;
		    color: #333;
		    display: inline-block;
		    float: left;
		    font-family: verdana,"微软雅黑","宋体" ;
		    font-size: 14px;
		    height: 35px;
		    line-height: 35px;
		    margin: 2px 10px 2px 0;
		    overflow: hidden;
		    padding: 0 10px;
		    text-decoration: none !important;
		    word-break: keep-all;
		}
		.ticket_choose a.select, .ticket_choose a:hover {
		    background-color: #f88630;
		    border-color: #f88630;
		    color: #fff;
		}
		.ticket_choose_day a {
		    border: 1px solid #ffd3bd;
		    height: 48px;
		    margin: 0 10px 10px 0;
		    min-width: 75px;
		    padding: 3px 10px;
		    text-align: center;
		}
		.ticket_choose_day a span {
		    display: block;
		    font-size: 16px;
		    height: 24px;
		    line-height: 24px;
		}
		.ticket_choose_day a em {
		    display: block;
		    font-size: 12px;
		    height: 20px;
		    line-height: 20px;
		}
		/* 分页css 之后导入标签  */
		.pageGroup{
			width: 85%;
			height: 45px;
			float: left;
			margin-left: 20px;
			/* border: 1px solid pink; */
		}
		.pageGroup .addListing .add{
			width: 90px;
			float: right;
			height: 32px;
			margin-top:-30px;
			text-align:center;
			background-color: #EB6120; 
			
			text-decoration:none !important;
			font: 18px/30px "微软雅黑","宋体";
			border-radius: 5px;
		}
		.pageGroup .addListing .add a{
			color:white;
			text-decoration: none;
			cursor: pointer;
		}
		
		.pageGroup .page table tr{
			height: 30px;
		}
		.pageGroup .page table tr td{
			background-color: #33CCFF;
			font-size:16px;
			color: white;
		}
		/* 列表css  */
		.list{
			height:100%;
			float:left;
			margin:10px 20px;
			border: 2px solid gray;
		}
		.chooseOpi_head {
		    background: none repeat scroll 0 0 #eee;
		    height: 40px;
		    font: 18px/30px "微软雅黑","宋体";
		    line-height: 30px;
		    overflow: hidden;
		    width: 100%;
		}
		.chooseOpi_head span, .chooseOpi_body{
		    display: inline-block;
		    float: left;
		    height: 30px;
		    overflow: hidden;
		    padding: 5px 10px;
		    position: relative;
		}
		.chooseOpi_head .k10{
			padding: 5px 0 20px 30px;
		    width: 110px;
		}
		.chooseOpi_head .k12{
			padding: 5px 0 20px 30px;
		    width: 85px;
		}
		.chooseOpi_head .k11{
			padding: 5px 0 5px 50px;
		    width: 120px;
		}

		.clear:after, .button:after, .ui_layout:after, .abeam:after, .list li:after, .tales60:after, .tales100:after, .selectOptions:after, .inner:after, .nav_tabs:after {
		    clear: both;
		    content: "content";
		    display: block;
		    font-size: 0;
		    height: 0;
		    visibility: hidden;
		    width: 0;
		}
		
		.movieList{
			width:100%;
			float:left;
			margin:0px 0px;
		}
		.movieList .lii{
			width: 104.5%;
			
			float:left;
			margin-left:-40px;
			padding-bottom:10px;
			padding-top:10px;
			border-bottom: 1px solid #ccc;
			
		}
		.movieList ul li{
			list-style-type: none;
		}
		.movieList .opitime{
			margin-left:15px;
			float:left;
		}
		.movieList .opitime .time1{
			font: 20px/30px "微软雅黑","宋体";
			/* font-weight: bold; */
		}
		.movieList .opitime .time2{
			color: #999;
			font-size: 16px;
		}
		.movieList .movieName{
			width:180px;
			float:left;
			margin-left:40px;
			font: 16px/30px "微软雅黑","宋体";
			/* font-weight: bold; */
			text-align:left;
		}
		.movieList .opiEdition{
			margin-top:5px;
			float:left;
			margin-left:-10px;
		}
		.movieList .opiRoom{
			float:left;
			margin-left:60px;
			margin-top:5px;
			color:#666;
		}
		.movieList .opiPrice{
			width:100px;
			float:left;
			margin-left:65px;
			margin-top:5px; 
		}
		.movieList .opiPrice .price{
			float: left;
			font-size:20px;
			/* font: 20px/30px "微软雅黑","宋体"; */
			font-weight: bold;
			color: #EB6120;
		}
		.movieList .opiPrice .oprice{
			float: left;
			font-size:14px;
			margin-left:8px;
			margin-top:5px;
			text-decoration:line-through;
			color: #999;
		}
		.movieList .operate{
			float:left;
			width:50px;
			color:white;
			background-color:#EB6120;
			border-radius: 5px;
			font: 16px/30px "微软雅黑","宋体";
			text-align:center;
			margin-left:20px;
			margin-top:5px; 
		}
		.movieList .operate a{
			text-decoration: none;
			color: white;
		}
		
	
	</style>
  
  	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script> 
 
 	<script type="text/javascript">
 		$(function() {
 			//初始选中日期
 			var daySelected = '${daySelected}';
 			$("#timeOutside a").each(function() {
 				var id = $(this).attr("id");
 				if(id == daySelected) {
 					$(this).addClass("select");
 				}
 			});
 		});
 	</script>
  </head>
  
  <body>
    	<div class="all">
    		<div class="title">
    			<div class="t1"><h2>我的影院-排期管理</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>影片排期</span></div>
	   			</div>
    		</div>
    		
    		<div class="ticket_choose">
                  <dl class="ui_media ticket_choose_day">
                      <dt class="ui_pic">选择时间：</dt>
                      <dd id="timeOutside" class="ui_text">
                      	<c:forEach items="${dateList}" var="date" varStatus="status">
                      		<c:choose>
                      			<c:when test="${status.index==0}">
                      				 <a id="${date}" href="business/ScreeningServlet?op=listing&day=${date}">
		                                 <span>${date.getMonth()+1}月${date.getDate()}日</span>
		                                 <em> 今天 </em>
	                                </a>
                      			</c:when>
                      			<c:otherwise>
                      				 <a id="${date}" href="business/ScreeningServlet?op=listing&day=${date}">
		                                 <span>${date.getMonth()+1}月${date.getDate()}日</span>
		                                 <em> ${weekdays.get(date.getDay())}</em>
	                                </a>
                      			</c:otherwise>
                      		</c:choose>
                      		
                      	</c:forEach>                                  
                      </dd>
                  </dl>
			</div>
			
			<div class="listing">
				<div class="pageGroup">
					<!-- <div class="page">
						<table >
							<tr>
								<td>首页</td>
								<td>上一页</td>
								<td>下一页</td>
								<td>尾页</td>
								<td>&nbsp;</td>
								<td>当前页码：1/30</td>
								<td>共有<span style="color: #EB6120;font-weight: bold;">128</span>部影片</td>
							</tr>
						</table>
					</div> -->
					<div class="addListing">
						<div class="add"><a href="business/ScreeningServlet?op=addListing">添加排期</a></div>
					</div>
				</div>
				
				<div class="list">
					<div class="chooseOpi_head " lang="chooseOpi_head">
						<span class="k10">放映时间</span>
						<span class="k11">影片</span>
						<span class="k12">语言版本</span>
						<span class="k12">放映厅</span>
						<span class="k12">现价(元)</span>
						<span class="k12">操作</span>
						<span class=""></span>
					</div>
					<div class="movieList">						
						<ul>
							<c:forEach items="${sList}" var="screening" varStatus="status">				
								<li>
									<div class='lii'>
										<div class='opitime'>
											<div class='time1'>${screening.startTime.toString().substring(11,16)}</div>
											<div class='time2'>预计<myTag:endTime startTime="${screening.startTime}"  duration="${screening.movie.duration}"></myTag:endTime> 散场</div>
										</div>
										<div class='movieName'>${screening.movie.name}</div><div class='opiEdition'>${screening.movie.language} ${screening.movie.is3D ? '3D' : '2D'}</div>
										<div class='opiRoom'>${screening.videoHall.no}号厅</div>
										<div class='opiPrice'><div class='price'>${screening.price}</div>
										<div class='oprice'>${screening.originalPrice}</div>
										</div><div class='operate'><a href='business/ScreeningServlet?op=delete&id=${screening.id }'>删除</a></div>
									</div>
								</li>					
							</c:forEach>
						</ul>
					</div>
			</div>	
		</div>		
    </div>
  </body>
</html>
