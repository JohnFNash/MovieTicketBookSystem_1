<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
    	<base href="<%=basePath%>"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>${movie.name}电影票购买_影评</title>
        
        <link rel="stylesheet" type="text/css" href="css/user/movie.css"/>
        
        <script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>   
		<script type="text/javascript" src="js/user/movie.js"></script>
		<script type="text/javascript" src="js/user/common.js"></script>
		
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
	 			
	 			//初始选中影院
	 			var cinemaSelected = '${cinemaSelected}';
	 			$("#cinemaPanel a").each(function() {
	 				var id = $(this).attr("id");
	 				if(id == cinemaSelected) {
	 					$(this).addClass("select");
	 				}
	 			});

			});
		</script>
    </head>
    
    <body>
    	<!-- 导航栏 -->
    	<jsp:include page="nav.jsp"></jsp:include>
        
        <!-- 具体内容 -->
        <div class="content">
        	<!-- 左 -->
        	<div class="ui_left">
            	<!-- 电影详细信息 -->
                <div class="detail_info">
					<div class="detail_head_name">                    	
                        <div class="clear">
                            <h1 class="left">${movie.name}</h1>
                             <span id="idSpan" style="display: none;">${movie.movieId}</span>
                            <span><a id="nCollect" class="ad" href="javascript:addCollection();">喜欢</a></span>
                            <span><a id="hCollect" class="ad se none" href="javascript:cancelTreasure();">已喜欢</a></span>
                            <div class="ratingsCount left">
                               
                            </div>
                        </div>                        
                        <!-- <h2 class="clear" style="width:100%">Night at the Museum: Secret of the Tomb</h2> -->
                    </div>
                    <div class="detail_head_info">
                    	<div class="ui_pic">
                        	<img width="210px" height="280px;" src="${movie.post}" title="${movie.name}"/>
                        </div>
                        <div class="ui_text">
                        	<p><em>导演：</em><myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr> </p>
                            <p><span class="left">主演：</span><span><myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr> </span></p>
                            <p><span class="left">类型：</span><span><myTag:typeListToStr movieTypeList="${movie.movieTypes}"></myTag:typeListToStr> </span></p>
                            <p><span class="left">地区： </span><span>${movie.area}</span></p>
                            <p><span class="left">片长： </span><span>${fn:substring((movie.duration/60+""), 0, 1)}小时${movie.duration%60}分钟</span></p>
                            <p><span class="left">语言： </span><span>${movie.language}</span></p>
                            <p><span class="left">首映： </span><span>${movie.publish.toLocaleString().split(" ")[0]}</span></p>
                        </div>
                    </div>
                </div>
                
                <!--  电影场次、剧情介绍等 -->
                <div class="mod_tabs">
                	<!-- 排片购票、剧情介绍、影片等选项卡 -->
                	<ul class="nav_tabs">
                    	<li class="selected"><a href="javascript:void(0);">排片购票</a></li>
                        <li><a href="javascript:void(0);">剧情介绍</a></li>
                        <li><a href="javascript:void(0);">影评<span>&nbsp;</span></a></li>
                    </ul>
                </div>
                
                <div class="tab_content">
                	<!-- 排片购票 -->                    
                	<div class="tab1">
                    	<div class="ticket_choose">
                            <dl class="ui_media ticket_choose_day">
                                <dt class="ui_pic">选择时间：</dt>
                                <dd id="timeOutside" class="ui_text">
                                   <c:forEach items="${dateList}" var="date" varStatus="status">
		                      		<c:choose>
		                      			<c:when test="${status.index==0}">
		                      				 <a id="${date}" href="user/GetMovieServlet?movieId=${movie.movieId}&day=${date}">
				                                 <span>${date.getMonth()+1}月${date.getDate()}日</span>
				                                 <em> 今天 </em>
			                                </a>
		                      			</c:when>
		                      			<c:otherwise>
		                      				 <a id="${date}" href="user/GetMovieServlet?movieId=${movie.movieId}&day=${date}">
				                                 <span>${date.getMonth()+1}月${date.getDate()}日</span>
				                                 <em> ${weekdays.get(date.getDay())}</em>
			                                </a>
		                      			</c:otherwise>
			                      	 </c:choose>		                      		
			                      	</c:forEach>
                                </dd>
                            </dl>                                     
							<dl class="ui_media mt10">
								<dt class="ui_pic">选择影院：</dt>
								<dd id="cinemaPanel" class="ui_text">
									<c:forEach items="${cinemaList}" var="cinema">
										<a id="${cinema.cinemaId}" href="javascript:void(0);" style="display: block;">${cinema.cinemaName}</a>
									</c:forEach>																
								</dd>
							</dl>
					</div>
                   
                   		<div id="cinemaCollection"><!-- 2015-01-06 22:13:10 -->
                            <div class="chooseOpi opend">
                            	<%-- <c:if test="${cinemaList.size()>0}">
                            		 <div id="opiCinemaDetail" class="chooseOpi_caption clear">
	                                    <h4><a target="_blank" href="user/GetCinemaServlet?cinemaId=${cinemaList.get(0).cinemaId}">${cinemaList.get(0).cinemaName}</a></h4>                                           
	                                </div>                            	
                            	</c:if>       --%>                         
                                <!-- 选择场次购票 -->
                                <div class="chooseScreenings">
                               		<table>
                               			<thead>
	                                    	<tr style="background-color:#ccc;">
	                                        	<td>放映时间</td><td>语言版本</td><td>放映厅</td><td>现价(元)</td><td>选座购票</td>
	                                        </tr>
                                        </thead>
                                        <tbody>
	                                        <c:forEach items="${sList}" var="screening">
		                                       <tr>
		                                       	<td class="opiTime">
													<b>${screening.startTime.toString().substring(10,16)}</b><em>预计<myTag:endTime startTime="${screening.startTime}" duration="${screening.movie.duration}"></myTag:endTime> 散场</em>
												</td>
		                                           <td class="opiEdition">
													<span>${screening.movie.language}</span>&nbsp;<span>${screening.movie.is3D ? "3D" : "2D"}</span>
												</td>
		                                           <td class="opiRoom">
													<span>${screening.videoHall.no}</span><span>号厅</span>
												</td>
		                                           <td class="opiPrice">
													<span class="nowPrice">${screening.price}</span>&nbsp;<span class="originalPrice">${screening.originalPrice}</span>
												</td>
		                                           <td>
		                                           	<a target="_blank" class="redBt" href="user/BeforeSelectSeatServlet?screeningId=${screening.id}"><span>选座购票</span></a>
		                                           </td>
		                                       </tr>
		                                    </c:forEach>	                                        
	                                    </tbody>    
                                    </table>
                                	<!-- 滚动条 -->
                                	<div class="scroller">
                                		<div class="handlerC">
                                			
                                		</div>
                                	</div>
                                </div>
                            </div> 
                  		</div>
                    </div>
                    
                    <!-- 剧情介绍 -->
					<div class="tab2" style="display:none;">
                    	<p style="margin-top:20px; margin-left:10px; line-height:25px;text-indent: 2em;">${movie.description}</p>
                    </div>                   
                    
                    <!-- 影评 -->
                    <div class="tab3" style="display:none;">
                    	<div class="mod_hd">
                      		  <a class="publishRemrkBtn" target="_blank" href="user/writeMovieRemark.jsp?movieId=${movie.movieId}&movieName=${movie.name}">发表影评</a>
                        </div>
                        <div class="mod_bd">
                        	<div id="hotCin_content">
                            	<ul style="border-top:1px solid #ccc;">                      
                                </ul>
                                <div class="ui_loading">加载更多评论数据！</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 右 -->
            <div class="ui_right">
            	
            </div>
        </div>
        
        <!-- 底部 -->
  		<jsp:include page="footer.jsp"></jsp:include> 
    </body>
</html>

