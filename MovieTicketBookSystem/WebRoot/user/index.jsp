<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>格瓦拉电影网</title>
        
        <link rel="stylesheet" type="text/css" href="css/user/index_content.css"/>        
        <script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>        
        <script type="text/javascript" src="js/user/index.js"></script>
	</head>
  
  <body>
    <br><!-- 导航栏 -->
    <div style="position:relative; top:-20px;">
		<jsp:include page="nav.jsp"></jsp:include>
	</div>
	
	<!-- 内容 -->
	<div class="ui_layout">
		<!-- 侧边栏 -->
		<div style="position:relative; top:-48px;">
			<jsp:include page="left.jsp"></jsp:include>
		</div>
		
		<!-- 具体内容 -->
		<div class="index_right">
			<!-- 左 -->
			<div class="ui_left">
               	<!-- 正在热映 -->
               	<div class="onShow">
					<div class="mod_hd">
                     	<h3>正在热映</h3>
                        <!--  <span>今天共有<span>23</span>部影片，<span>60</span>家影院支持选座订票</span> -->
                     </div>
                     <div class="mod_bd">
                     	<c:if test="${hotMovieList.size() > 0}"><!-- 如果存在排期 -->
                       		<div class="topM">
	                           	<div class="ui_left">                           		
	                           		<c:set var="firstMovie" value="${hotMovieList.get(0)}"></c:set>
	                               	<div class="ui_media">
	                                   	<div class="ui_pic">
	                                       	<a href="user/GetMovieServlet?movieId=${firstMovie.movieId}" title="${firstMovie.name}" target="_blank"><img width="150" height="200" src="${firstMovie.post}" alt="${firstMovie.name}"></a>                                       	                                          
	                                    </div>
	                                    <div class="ui_text">
	                                       	<h2>
											<a title="${firstMovie.name}" class="c333" href="user/GetMovieServlet?movieId=${firstMovie.movieId}" target="_blank">${firstMovie.name}</a>	
											<span><sub>${fn:substring(firstMovie.score+"", 0, 1)}</sub><sup>.${fn:substring(firstMovie.score+"", 2, 3)}</sup></span>																																																
											</h2>												
											<c:if test="${firstMovie.title != null}">
												<div class="ui_summary">
													<span class="first"></span>${firstMovie.title}<span></span>
												</div>
											</c:if>																				
											<p class="mt5"><em>类型：</em><myTag:typeListToStr movieTypeList="${firstMovie.movieTypes}"></myTag:typeListToStr></p>										
											<p><em>导演：</em><myTag:directorListToStr directorList="${firstMovie.directors}"></myTag:directorListToStr> </p>
											<p><em>主演：</em><myTag:actorListToStr actorList="${firstMovie.actors}"></myTag:actorListToStr> </p>																																	
											<p class="c999">${firstMovie.buyCount}人购票</p>																																															
	                                    </div>
	                                   </div>
	                               </div>
	                            <div class="ui_right">                               	
									<div class="tags">
										<ul>
											<c:forEach items="${hotCinemaList}" var="cinema" end="1" varStatus="status">
												<li>
													<a title="${cinema.cinemaName}" id="tags${status.index+1}" class="${status.index==0 ?  'select' : ''}" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}" target="_blank">${cinema.cinemaName}</a>
												</li>
											</c:forEach>
										</ul> 
										<a class="more c999" href="user/GetMovieServlet?movieId=${firstMovie.movieId}" target="_blank">全部</a>
									</div>
									<div class="list mt10">
										<div class="" id="tags1_content">
											<div class="listBox">									
												<c:forEach items="${screenings_1}" var="screening">
													<a target="_blank" href="user/GetMovieServlet?movieId=${firstMovie.movieId}" title="选座购票">${screening.startTime.toString().substring(11,16)}</a>
												</c:forEach>																																																										
											</div>																						
											<p class="tar"><a target="_blank" href="user/GetMovieServlet?movieId=${firstMovie.movieId}" class="button bigBt redBt"><span>选座购票</span></a></p>																					
										</div>																		
										<div class="none" id="tags2_content">											
											<div class="listBox">				                    																			
												<c:forEach items="${screenings_2}" var="screening">
													<a target="_blank" href="user/GetMovieServlet?movieId=${firstMovie.movieId}" title="选座购票">${screening.startTime.toString().substring(11,16)}</a>
												</c:forEach>																							
											</div>																						
											<p class="tar"><a target="_blank" href="user/GetMovieServlet?movieId=${firstMovie.movieId}" class="button bigBt redBt"><span>选座购票</span></a></p>																					
										</div>																		</div>								
	                               </div>
	                        </div>    		
                        </c:if>
                     
                       
                     	<!-- 电影列表 -->
                       	<div class="listM">                    	
                               <div class="even_move" id="menu_box_movie">
                                   <div class="even_left"><b class="orientation disable"></b></div>
                                   <div class="even_right"><b class="orientation"></b></div>
                                   <div class="even_center">
                                       <ul style="" id="menu_box_movie_inner">
                                         <c:forEach items="${hotMovieList}" var="movie" begin="1">
                                         	<li>
	                                           <div class="roomTypeBox" style="width:120px; height:160px; overflow:hidden; margin:0 auto;">
	                                             <a target="_blank" title="${movie.name}" href="user/GetMovieServlet?movieId=${movie.movieId}">
	                                                 <img alt="${movie.name}" src="${movie.post}" height="160" width="120">
	                                             </a>
	                                                                                                                                                                                                                                                                                                                 </div>
		                                         <p class="mt10">
		                                             <a title="${movie.name}" class="c333 fs14" href="user/GetMovieServlet?movieId=${movie.movieId}" style="height:30px; line-height:30px; display:inline-block;" target="_blank">${movie.name}</a>
		                                             <span><sub class="ml5" data-keynum="211613117_mark1">${fn:substring(movie.score+"", 0, 1)}</sub><sup data-keynum="211613117_mark2">.${fn:substring(movie.score+"", 2, 3)}</sup></span>
		                                         </p>
		                                         <p class="c999" data-keynum="211613117_boughtcount">${movie.buyCount}人购票</p>
		                                         <p class="mt5"><a href="user/GetMovieServlet?movieId=${movie.movieId}" class="button bigBt redBt" target="_blank"><span>选座购票</span></a></p>
	                                        </li>
                                         </c:forEach>                                                                                                                                          										
										</ul>
                                   </div>
                               </div>							
                           </div>
               		</div>
                 </div>                    
               	
                 <!-- 精彩影评 -->
                 <div class="movieRemark">
                   		<h3>精彩影评</h3>
                       <!-- 影评列表 -->
                       <ul id="commentListDiv">
                       <c:forEach items="${movieRemarkList}" var="movieRemark">
                       		<li>
	                           	<div class="ui_media">
	                                <!-- 头像 -->
	                               	<div class="ui_pic">                                    	
                                       <a style="width:50px; margin:0 auto; display:block;" href="#" title="${movieRemark.user.account}" target="_blank">
                                           <img src="${movieRemark.user.headPath}" alt="${movieRemark.user.account}" height="50" width="50">
                                       </a>
                                       <span class="mt5"><a href="#" title="${movieRemark.user.account}" target="_blank">${movieRemark.user.account}</a></span>
                                    </div>
                                    <!-- 评价内容 -->
                                    <div class="ui_text">                                    	
                                      	<h4><a href="user/GetMovieRemarkServlet?movieRemarkId=${movieRemark.id}" target="_blank">${movieRemark.title}</a></h4>
                                       <p class="mt5">${movieRemark.content}</p>
                                       <a class="ui_readMore c333" target="_blank" href="user/GetMovieRemarkServlet?movieRemarkId=${movieRemark.id}">全文</a>
                                       <div class="box_movieInfo">                                                                             
										<p><a href="user/GetMovieServlet?movieId=${movieRemark.movie.movieId}" target="_blank">${movieRemark.movie.name}</a></p>
										<!-- <p>还有<span class="ui_readMore c333">6篇影评</span></p>	 -->	
                                       </div>
                                   </div>
                               </div>
                           </li>
                       </c:forEach>                       	                        
                       </ul>
                   </div>
               </div>					                    
			
			<!-- 右 -->				
			<div class="ui_right_2">
                <!-- 购票流程 -->
               	<div class="buyTicket_procedure">
                   	<img class="procedure_img" src="images/other/procedure.png" />
                   </div>
                   
                   <!-- 热门影院 -->
                   <div class="hotCinema">
                   	<div class="mod_hd">                        	
						<h3>热门影院</h3>
						<a href="user/LoadCinemasServlet" target="_blank" class="more">更多</a>						
                       </div>
                       <div class="mod_bd">                        	
						<ul>
							<c:forEach items="${hotCinemaList}" var="cinema">
								 <li>                          
	                                <a class="button minBt redBt right" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}" target="_blank"><span>选座购票</span></a>
	                                <a href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}" target="_blank" class="name left">${cinema.cinemaName}</a>
	                                <span class="point"><sub class="min">${fn:substring(cinema.score+"", 0, 1)}</sub><sup class="min">.${fn:substring(cinema.score+"", 2, 3)}</sup></span>
								</li>
							</c:forEach>                          							
						</ul>						
                       </div>                        
                   </div>
               </div>
		</div>
	</div>
	
	<!-- 底部 -->
	<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
