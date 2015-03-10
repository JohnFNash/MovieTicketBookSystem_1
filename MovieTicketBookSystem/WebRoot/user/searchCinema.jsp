<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath %>=%>"/>
    <title>电影院列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/user/searchCinema.css"/>
	
	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>    
	<script type="text/javascript">
		$(function() {
			//选中侧边栏中当前页面对应的项并突出显示
			$(".ui_aside_nav li:eq(3)").addClass("select")
									   .siblings().each(function() {
									   		$(this).removeClass("select");
									   });
		});
	</script>
  </head>
  
  <body> 
  	<!-- 导航栏 -->
  	<div style="margin-top:-8px;">  
  		<jsp:include page="nav.jsp"></jsp:include>
  	</div>
  	
   	<div id="mid">
   		<div style="margin-left:-10px;">
   			<jsp:include page="left.jsp"></jsp:include>
   		</div>
   		
   		<div class="ui_right">
   			<div id="left">
   					<div id="left_top">
   						<h2>电影院<span class="m110">&nbsp;共<em id="fontcolor">${pageBean.totalRecords}</em>家<!-- <em id="fontcolor">60</em>家选座购票</span> --></h2>
   					</div>
   					
   				<!-- 	<div id="left_top2">
   						<span class="cate">区    域</span>	<a class="redBt">行政区</a>	<a class="redBt ml20">热门影院</a>	
   					</div> -->
   					
   					<div id="left_top3">
   					<form action="">
						<input type="text" style="font-size:13px; color:#999999; margin-left:-65px;" name="query" maxlength="25" placeholder="请输入影院名(不多于25个字)"/>
						<input class="redBt" type="button" value="搜索"/>
					</form>
   					</div>
   					
   					<div id="left_top4">
   						<!-- <div style="float: left;">排序方式：</div>   						
						<ul class="simulate_list">
						<li>
							<a href="">
							<span>评分</span>
							<img width="9" height="13" src="images/other/up_white.gif">
						</a>
						</li>
						<li>
							<a href="">
							<span>关注度</span>
							<img width="9" height="13"  src="images/other/up_white.gif">
							</a>
						</li>
						</ul> -->
						<div class="m11" ><em id="fontcolor">${pageBean.totalRecords}</em><span id="fontstyle">家影院</span></div>
   					</div>
   					
   					<div class="cinemalist">
   						<ul>
   							<c:forEach items="${pageBean.dataList}" var="cinema">
   								<li class="effectLi " style="width:100%">
								<div class="ui_media">
									<div class="ui_pic cinema">
										<a title="${cinema.cinemaName}" target="_blank" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}">
											<img width="122" height="92" style="border:1px solid #f4f4f4;background: url(${cinema.logo}) center center no-repeat #fff;vertical-align:middle;" alt="${cinema.cinemaName}" src="images/other/blank.gif">
										</a>
									</div>
									<div class="ui_text">
										<div class="clear">
											<div class="left">
												<div class="title">
													<h2>
													<a class="color3" target="_blank" title="${cinema.cinemaName}" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}">${cinema.cinemaName}</a>
													</h2>
													<span class="ml5">(${cinema.visitCount}关注)</span>
												</div>
												<p class="mt10">
													<a class="c999" target="_blank" href="javascript:void(0);">[${cinema.area.areaName}]</a>
													${cinema.address}					
												</p>
											<div class="mt5 conditionIco"> </div>
											<div class="favorable mt20">
											<%-- <p>
												今天放映
												<span>12部 余 82场</span>
												<!-- <em>　10:20~23:10　</em> -->
												<a target="_blank" title="全部排片" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}">
													全部
													<i></i>
												</a>
											</p> --%>
											</div>
										</div>
										<div class="right mr10">
											<p>
												<span class="grade"><sub>${fn:substring(cinema.score == null ? "7.0" : cinema.score+"", 0, 1)}</sub>
													<sup>.${fn:substring(cinema.score == null ? "7.0" :cinema.score+"", 2, 3)}</sup></span>
											</p>
											<!-- <p style="margin-top:12px;">评分来自74位瓦友</p> -->
											<p style="margin-top:9px;">
												<a class="button redBt minBt" target="_blank" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}">
													<span>选座购票</span>
												</a>
											</p>
											<!-- <p style="margin-top:13px;">最新购票1小时前</p> -->
										</div>
									</div>								
								</div>
								</div>
   								</li>
   							</c:forEach>   													   											
   						</ul>   						
						<div class="pagePlugin" style="margin-bottom:20px; margin-left:10px;">
							<%List<Integer> values = Arrays.asList(5,10,20); pageContext.setAttribute("values", values);%>
							<form name="cinemaForm" action="user/LoadCinemasServlet" method="post">
								<myTag:page form="cinemaForm" pageBean="${pageBean}" optionValues="${values}"></myTag:page>
							</form>
						</div>
   					</div>
   			</div>
   			    		
   			<div id="right">
    				<div class="m12"><h2>最受关注的影院</h2></div>
    				<div class="m13">
    					<ul>
	    					<c:forEach items="${mostAttentionCinemas}" var="cinema" varStatus="status">	
	    						<li class="topThree" >
										<span class="num">${status.index+1}</span>
										<span class="mName" style="width: 202px;">
										<a target="_blank" title="${cinema.cinemaName}" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}">${cinema.cinemaName}</a>
										</span>
										<span class="point">
											<sub>${fn:substring(cinema.score == null ? "7.0" : cinema.score+"", 0, 1)}</sub>
											<sup>.${fn:substring(cinema.score == null ? "7.0" :cinema.score+"", 2, 3)}</sup>										
										</span>						
								 </li>	
							</c:forEach>	 				
    					</ul>
    				</div>
    				
    				<div class="m14">
						<div class="t"><h2>正在热映</h2></div>
						<div class="m"><a target="_blank" style="color:#999; position: relative; top:14px;" href="user/HotMovieServlet">更多</a></div>
					</div>
    				
    				<div class="nowm">
    					<c:forEach items="${hotMovieList}" var="movie">
							<dl class="ui_media">
								<dt class="ui_pic">
									<a target="_blank" title="${movie.name}" href="user/GetMovieServlet?movieId=${movie.movieId}">
									<img width="96" height="128" alt="${movie.name}" src="${movie.post}">
									</a>
								</dt>
								<dd class="ui_text">
									<p>
										<a class="fs14" target="_blank" title="${movie.name}" href="user/GetMovieServlet?movieId=${movie.movieId}">${movie.name}</a>
										<span class="grade">
											<sub>${fn:substring(movie.score == null ? "7.0" : movie.score+"", 0, 1)}</sub>
											<sup>.${fn:substring(movie.score == null ? "7.0" :movie.score+"", 2, 3)}</sup>
										</span>										
									</p>
									<c:if test="${movie.title != null}">
										<p class="mt5">“${movie.title}”</p>
									</c:if>									
									<p class="c999">
										<em>导演：</em>
										<span><myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr></span>
									</p>
									<p class="c999" title="主演：<myTag:actorListToStr actorList='${movie.actors}'></myTag:actorListToStr>">
										<em>主演：</em>
										<span><myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr></span>
									</p>
								</dd>
							</dl>
						</c:forEach>	
    				</div>    				    			
    			</div>
    	</div>
    			
    </div>
  
  	<!-- 底部 -->
  	<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>

