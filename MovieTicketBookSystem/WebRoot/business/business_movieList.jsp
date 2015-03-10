<%@page import="com.team3.mbts.entity.Movie"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

	<link rel="stylesheet" type="text/css" href="css/business/movieList.css">

	<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>
	
	<script type="text/javascript">
	/* .condition a.selected */
		$(function(){
			var num = '${num}';
			//alert(num);
			if('${num}'!='')
			{
				$(".condition a:eq('${num}')").addClass("selected");
			}	
			
		});
	</script>
	
  </head>
  
  <body>
    	<div class="all">
    		<div class="title">
    			<div class="t1"><h2>&nbsp; 我的影院-影片管理</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>影片列表</span></div>
	   			</div>
    		</div>
    			
    		<div class="mod_bd">
					<ul class="ulist">
						<li>
							<div class="condition">
							<dl class="clear dlTy">
								<dt>
									<span class="typeTitle mr10">影片类型</span>
								</dt>
								<dd>
									<a href="business/MovieServlet?op=typeSearch&typeNum=0">动作</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=1">喜剧</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=2">爱情</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=3">科幻</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=4">奇幻</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=5">灾难</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=6">恐怖</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=7">纪录</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=8">犯罪</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=9">战争</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=10">冒险</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=11">剧情</a>
									<a href="business/MovieServlet?op=typeSearch&typeNum=12">其他</a>
								</dd>
							</dl>
							</div>
						</li>
						<li>
						<div class="condition mt5">
							<dl class="clear dlTy">
							<dt>
								<span class="typeTitle left">影片查询</span>
							</dt>
							<dd>
								<div class="search_kuang left ">
									<form id="searchForm" action="business/MovieServlet?op=search" method="post">
										<input type="text" placeholder="请输入搜索关键字" name="key" value="${key }" /><input type="submit" value="搜索"/>
									</form>
								</div>
							</dd>
							</dl>
						</div>
						</li>
					</ul>
				</div>
				
				<div class="pageGroup">
					<form action="business/MovieServlet?op=mList" method="post" name="moviePage">
					<div class="page">
						<%List<Integer> values = Arrays.asList(5,10); pageContext.setAttribute("values", values);%>
						<myTag:page form="moviePage" pageBean="${pageBean }" optionValues="${values}">
						</myTag:page>
					</div>
					</form>
				</div>
    		
    		
    		<div class="movieList">		
    				<ul class="mlist">
    						<c:forEach items="${pageBean.dataList }" var="movie">
	    							<li>
			    						<table width="670"   cellspacing="0px;">
											  <tr>
											    <td width="138" rowspan="7" height="160"><a href="business/MovieServlet?op=details&movieId=${movie.movieId }"><img width="120px;" height="160px;" src="${movie.post }"/></a></td>
											    <td width="550" style="font-size: 20px;"><a href="business/MovieServlet?op=details&movieId=${movie.movieId }">${movie.name }</a></td>
										      </tr>
											  <tr>
											    <td style="color: #999999;font-size: 14px;"><span style="color: black;">类型：</span><myTag:typeListToStr movieTypeList="${movie.movieTypes }"></myTag:typeListToStr> </td>
										      </tr>
											  <tr>
											    <td style="color: #999999;font-size: 14px;"><span style="color: black;">语言：</span> ${movie.language }</td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">时长：</span> ${movie.duration }分钟</td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">导演：</span><myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr></td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">主演：</span><myTag:actorListToStr actorList="${movie.actors }"></myTag:actorListToStr> </td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">上映时间：</span> ${movie.publish.toLocaleString().split(" ")[0]} </td>
										      </tr>
											<%--   <tr>
											    <td colspan="2"><p><a href="business/MovieServlet?op=details&movieId=${movie.movieId }" style="text-decoration: none;">查看详情</a>	
											    	<!-- <a href="business/ScreeningServlet?op=allListing">上架 </a>  
											    	<a href="#">下架</a>	</p> -->
											    </td>
										      </tr> --%>
										</table>
	    							</li>
	    						</c:forEach>
    						    						    						
 								<c:forEach items="${movieList }" var="movie">
	    							<li>
			    						<table width="670"   cellspacing="0px;">
											  <tr>
											    <td width="138" rowspan="7" hight="160"><a href="business/MovieServlet?op=details&movieId=${movie.movieId }"><img width="120px;" height="160px;" src="${movie.post }"/></a></td>
											    <td width="550" style="font-size: 20px;">${movie.name }</td>
										      </tr>
											  <tr>
											    <td style="color: #999999;font-size: 14px;"><span style="color: black;">类型：</span><myTag:typeListToStr movieTypeList="${movie.movieTypes }"></myTag:typeListToStr> </td>
										      </tr>
											  <tr>
											    <td style="color: #999999;font-size: 14px;"><span style="color: black;">语言：</span> ${movie.language }</td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">时长：</span> ${movie.duration }分钟</td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">导演：</span> ${movie.director.actorName }</td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">主演：</span><myTag:actorListToStr actorList="${movie.actors }"></myTag:actorListToStr> </td>
										      </tr>
											  <tr>
											     <td style="color: #999999;font-size: 14px;"><span style="color: black;">上映时间：</span> ${movie.publish.toLocaleString().split(" ")[0]} </td>
										      </tr>
											  <tr>
											    <td colspan="2"><p><a href="business/MovieServlet?op=details&movieId=${movie.movieId }" style="text-decoration: none;">查看详情</a>	
											    	<!-- <a href="business/ScreeningServlet?op=allListing">上架 </a>  
											    	<a href="#">下架</a>	</p> -->
											    </td>
										      </tr>
										</table>
	    							</li>
	    						</c:forEach>
    					    					
    				</ul>    			
    		</div>
    		
    </div>
    
  </body>
</html>
