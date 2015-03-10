<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>影片详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin/movieDetails.css">
	
  </head>
  
  <body>
<div class="ui_left">
			<div class="detail_head_name clear">
				<div class="clear">
					<h1 class="left">${movie.name }</h1>
					 <!-- <div class="n10"> 
					 	<div class="n101"><a href="#">已上架</a></div>
					 	<div class="n102"><a href="#">下架</a></div>
					 </div>	 -->
				</div>
				<div class="movieCountList">
					<ul class="ui_inline">
						<li class="first">
							<b id="collectedTimes">
								<span data-keynum="159639640_collectedtimes">${movie.likeCount }</span>
							</b>
							<span>喜欢</span>
						</li>
						<li>
							<b data-keynum="159639640_clickedtimes">${movie.attentionCount }</b>
							<span>关注</span>
						</li>
						<li>
							<b data-keynum="159639640_boughtcount">${movie.buyCount }</b>
							<span>购票</span>
						</li>
					</ul>
				</div>
				<!-- <h2 class="t2" style="width:100%">Tracks in The Snowy Forest</h2> -->
			</div>
			<div class="detail_head_info">
				<div class="ui_media">
					<div class="ui_pic">
						<img width="210" height="280" src="${movie.post }" alt="${movie.name }" title="${movie.name }">
						<div class="ui_movieType_line mt10">
							<a target="_blank" href="/chongqing/cinema/imax.xhtml?mId=159639640&ctype=REALD">
								<span class="ui_type3d"></span>
							</a>
						</div>
					</div>
					<div class="ui_text">
						<p class="ui_summary_big">
							<span class="first"></span>
							“${movie.title }”
							<span></span>
						</p>
						<div class="detail_head_text mt10">
							<p>
								<em>导演：</em>
								<myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr>
							</p>
							<div class="ui_media" style="margin-top:0;">
								<div class="ui_pic" style="margin-right:0;">主演：</div>
								<div class="ui_text" style="min-height:0; color:#333;"><myTag:actorListToStr actorList="${movie.actors }"></myTag:actorListToStr></div>
							</div>
							<p>
								<em>类型：</em>
								<myTag:typeListToStr movieTypeList="${movie.movieTypes }"></myTag:typeListToStr>
							</p>
							<p>
								<em>地区：</em>
								${movie.area }
							</p>
							<p>
								<em>片长：</em>
								${movie.duration }分钟
							</p>
							<p>
								<em>语言：</em>
								${movie.language }
							</p>
							<p>
								<em>首映：</em>
								${movie.publish.toLocaleString().split(" ")[0]}
							</p>
							
						</div>
						
					</div>
				</div>
			</div>
			
	</div>
	
	<div class="introduction">
	   				<div class="m10">
	   					 <div class="m11"><span>剧情介绍</span></div>
	   				</div>
	   				
	   				<div class="m12">
	   					<div class="m121">
	   					<p>
	   						${movie.description }
	   					</p>	
	   					</div>
	   					
	   				</div>
	</div>
	
	<div class="operate">
		
			<!-- <a href="business/ScreeningServlet?op=addListing" class="button blue">AddListing</a> -->
			<a href="admin/LoadAllMoviesServlet?op=mList" class="button blue">Return</a>
	</div>
	
  </body>
</html>
