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
    
    <title>影片列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin/movieList.css">
	<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	
		$(function(){
			var num = '${num}';
			
			if('${num}'!='')
			{
				$(".movie_dz a:eq('${num}')").addClass("selected");
			}	
			
		});
	</script>
	<style type="text/css">
	.movie_dz a.selected{
		color: #FFffff; background-color:#EC6120;
	}
	
	</style>
  </head>
  
  <body>
    	<div class="all">
    		<div class="title">
    			<div class="t1"><h2 align="left">我的影院-影片管理</h2>
    			</div>
		  </div>
		 		
    		<div class="mod_bd">
    			
				<div class="movie_types">
					  <div class="movie_dz">
					  	<span class="movie_feilei">影片类型&nbsp;&nbsp;&nbsp;&nbsp;</span>
					    <div class="typeHref">
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=0">动作</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=1">喜剧</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=2">爱情</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=3">科幻</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=4">奇幻</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=5">灾难</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=6">恐怖</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=7">纪录</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=8">犯罪</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=9">战争</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=10">冒险</a>
							<a href="admin/LoadAllMoviesServlet?op=typeSearch&typeNum=11">剧情</a>
					    </div>
				      </div>
				      <form  action="admin/LoadAllMoviesServlet?op=search" method="post"> 
						<div class="queryDiv">		
						  <span class="movie_chaxun">影片查询&nbsp;&nbsp;&nbsp;&nbsp;</span>
						  <input type="text" name="key" placeholder="请输入搜索关键词" value="${key}" />					  
						  <input class="redBt" type="submit" value="搜索" />	
				        </div>
				       </form> 
			  	</div>
			</div>

					
				<div class="pageGroup">
				<form action="admin/LoadAllMoviesServlet?op=mList" method="post" name="movieForm">
					<myTag:page form="movieForm"  pageBean="${pageBean}"></myTag:page>
				</form>
				</div>			
						    		    		
	    		<div class="movieList">    			
	   				<ul class="mlist"> 
	   					<c:forEach items="${pageBean.dataList}" var="movie">							   					  		
						<li>
	   						<table style="width:670px">
								  <tr>
								    <td width="138" rowspan="7" height="160"><a href="admin/AdminGetMovieServlet?movieId=${movie.movieId}"><img width="120px;" height="160px;" src="${movie.post}"/></a></td>
								    <td width="550" style="font-size: 24px;font-weight: bold;"><a href="" style="color: #000;">${movie.name}</a></td>
							      </tr>
								  <tr>
								    <td style="color: #999999;font-size: 14px;"><span style="color: black;">类型：</span><myTag:typeListToStr movieTypeList="${movie.movieTypes}"></myTag:typeListToStr> </td>
							      </tr>
								  <tr>
								    <td style="color: #999999;font-size: 14px;"><span style="color: black;">语言：</span>${movie.language}</td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">时长：</span>90分钟</td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">导演：</span><myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr></td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">主演：</span><myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr> </td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">上映时间：</span>${movie.publish.toLocaleString().split(" ")[0]}</td>
							      </tr>
								  <tr>
								    <td colspan="2"><a class="redBt">删除</a>&nbsp;<a href="admin/MovieUpdateServlet?op=modify&movieId=${movie.movieId }" class="redBt">修改</a></td>
							      </tr>
							</table>
						</li>
						</c:forEach> 		
						
						
						
						<c:forEach items="${movieList}" var="movie">							   					  		
						<li>
	   						<table style="width:670px">
								  <tr>
								    <td width="138" rowspan="7" height="160"><a href="business/MovieServlet?op=details&movieId=${movie.movieId }"><img width="120px;" height="160px;" src="${movie.post}"/></a></td>
								    <td width="550" style="font-size: 24px;font-weight: bold;"><a href="" style="color: #000;">${movie.name}</a></td>
							      </tr>
								  <tr>
								    <td style="color: #999999;font-size: 14px;"><span style="color: black;">类型：</span><myTag:typeListToStr movieTypeList="${movie.movieTypes}"></myTag:typeListToStr> </td>
							      </tr>
								  <tr>
								    <td style="color: #999999;font-size: 14px;"><span style="color: black;">语言：</span>${movie.language}</td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">时长：</span>90分钟</td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">导演：</span>${movie.director.actorName}</td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">主演：</span><myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr> </td>
							      </tr>
								  <tr>
								     <td style="color: #999999;font-size: 14px;"><span style="color: black;">上映时间：</span>${movie.publish.toLocaleString().split(" ")[0]}</td>
							      </tr>
								  <tr>
								    <td colspan="2"><a class="redBt">删除</a>&nbsp;<a href="admin/MovieUpdateServlet?op=modify&movieId=${movie.movieId }" class="redBt">修改</a></td>
							      </tr>
							</table>
						</li>
						</c:forEach> 		
	   				</ul>
	   			
	    		</div>
    		
    </div>
    
  </body>
</html>
