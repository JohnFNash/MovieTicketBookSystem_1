<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${cinema.cinemaName}</title>
			
		<link rel="stylesheet" type="text/css" href="css/user/cinema.css"/>	
			
		<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="js/business/json2.js"></script>
		<script type="text/javascript" src="js/user/cinema.js"></script>
		<script type="text/javascript" src="js/user/common.js"></script>					
		<script type="text/javascript" src="js/user/writeMovieRemark.js"></script>
		
		<link rel="stylesheet" type="text/css" href="js/user/artDialog-5.0.3/skins/default.css" />
        <script src="js/user/artDialog-5.0.3/artDialog.min.js"></script>
				
		<script type="text/javascript">
			$(function() {
				//初始显示影院得分
				var score = parseInt('${cinema.score}');
				$(".ui_big_ratingsGot li:lt(" + score + ")").each(function() {
					var index = $(".ui_big_ratingsGot li").index($(this));
					if(index % 2 == 0) {
						$(this)[0].className = "half";
					} else {
						$(this)[0].className = "on";
					}
				});
			
	 			//初始选中日期
	 			var daySelected = '${daySelected}';
	 			$("#timeOutside a").each(function() {
	 				var id = $(this).attr("id");
	 				if(id == daySelected) {
	 					$(this).addClass("select");
	 				}
	 			});
	 			
	 			//初始选中影片
	 			var movieIdSelected = '${movieIdSelected}';
	 			$("#menu_box_movie_inner li a").each(function() {
	 				var id = $(this).attr("id");
	 				if(id == movieIdSelected) {
	 					$(this).addClass("select");
	 				}
	 			});	 				 			
	 		});
		</script>
	</head>

	<body>
		<!-- 导航栏 -->
	  	<div style="margin-top:8px;">  
	  		<jsp:include page="nav.jsp"></jsp:include>
	  	</div>
	
		<div class="middle">
			<!-- 标题 -->		
			<div class="title">
				<span id="idSpan" style="display: none;">${cinema.cinemaId}</span>
				<div class="t1"><h2>${cinema.cinemaName}</h2></div>
				<div class="ratingsSide1">
					<ul class="ui_big_ratingsGot">
						<li class=""></li>
						<li class="no"></li>
						<li class=""></li>
						<li class="no"></li>
						<li class=""></li>
						<li class="no"></li>
						<li class=""></li>
						<li class="no"></li>
						<li class="">&nbsp;</li>
						<li class="no"></li>
					</ul>
					<span class="bigSubp">
						<sub>${fn:substring(cinema.score == null ? "7.0" : cinema.score+"", 0, 1)}</sub>
						<sup>.0</sup>
					</span>
				</div>
	  		</div>
	  			
			<div class="description">
				<div class="img">
					<img width="280" height="210"  src="${cinema.scene}">
				</div>
				<div class="content">
					<div class="address"><span><span style="color: black;">地址：</span>${cinema.address}</span></div>
					<div class="tel"><span><span style="color: black;">电话：</span>${cinema.tel}</span></div>
					<div class="spe"><pre><span style="color: black;">特色：</span>${cinema.special}</pre></div>
	   				</div>
	   			</div>		
	   		
	   		<!--  电影场次,影院介绍介绍等 -->
            <div class="mod_tabs">
            	<!-- 排片购票、剧情介绍、影片等选项卡 -->
            	<ul class="nav_tabs">
                	<li class="selected"><a href="javascript:void(0);">排片购票</a></li>
                    <li><a href="javascript:void(0);">影院介绍</a></li>
                    <li><a href="javascript:void(0);">评价<span></span></a></li>
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
		                      				 <a id="${date}" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}&day=${date}">
				                                 <span>${date.getMonth()+1}月${date.getDate()}日</span>
				                                 <em> 今天 </em>
			                                </a>
		                      			</c:when>
		                      			<c:otherwise>
		                      				 <a id="${date}" href="user/GetCinemaServlet?cinemaId=${cinema.cinemaId}&day=${date}">
				                                 <span>${date.getMonth()+1}月${date.getDate()}日</span>
				                                 <em> ${weekdays.get(date.getDay())}</em>
			                                </a>
		                      			</c:otherwise>
		                      		</c:choose>
		                      		
		                      	</c:forEach>
                              </dd>
                          </dl>
                          
						<dl class="ui_media movie_choose_box">
							<dt class="ui_pic">选择影片：</dt>
							<dd id="choice" class="listM">
								<div id="menu_box_movie" class="even_box">
									<div class="even_center">
										<ul id="menu_box_movie_inner" style="width: 1296px; left: 0px;">
											<c:forEach items="${movieList}" var="movie">
												<li>
													<a id="${movie.movieId}" href="javascript:void(0);"><img width="96" height="128" src="${movie.post}" alt="${movie.name}"></a>
													<span data-type="moreInfo" data-id="208315566" class="moreInfo">详情</span>
													<em>${movie.name}</em>
												</li>
											</c:forEach>											
										</ul>
									</div>
									<c:if test="${movieList.size() > 6}">
										<span style="display: block;" class="even_left"><b class="orientation disable"></b></span>
										<span style="display: block;" class="even_right"><b class="orientation"></b></span>
									</c:if>									
								</div>
							</dd>
						</dl>						
					</div>
                 
		      		<div id="cinemaCollection"><!-- 2015-01-06 22:13:10 -->
		                          <div class="chooseOpi opend">
		                              <div id="opiCinemaDetail" class="chooseOpi_caption clear">
		                                  <h4><a target="_blank" href="javascript:void(0)">${cinema.cinemaName}</a></h4>                                           
		                              </div>
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
                  
	            <!-- 影院介绍介绍 -->
				<div class="tab2" style="display:none;">
					<h3>影院介绍</h3>
	                <p style="margin-top:20px; margin-left:10px; line-height:25px;text-indent: 2em;">${cinema.description}</p>
	                <h3>优惠政策</h3>
	                <p style="margin-top:20px; margin-left:10px; line-height:25px;">
	                 		优惠信息： ------------------------------------------------------------------------------------------------------------- 1.每周一至周五：白天18:00之前的场次优惠15-20元； 2.每周六、周日及节假日：上午10:30前开设半价早场； 3.每周五、周六及节假日：晚上22:00后开设两片连映午夜场； 4.每周二：18:00前场次半价优惠；18:00后场次七折优惠； 5.每周一为UME会员日：凡每周一会员持卡购票，可享受等同周二的折扣优惠--18:00前场次半价优惠；18:00后场次七折优惠； ------------------------------------------------------------------------------------------------------------- 会员政策： ------------------------------------------------------------------------------------------------------------- UME会员招集： 一次性充值500元 成为500卡会员 购票享受8.5折优惠 一次性充值800元 成为800卡会员 购票享受8.折优惠 一次性充值3000元 成为贵宾金卡会员 购票享受7.5折优惠 会员可享电话订票、网上购票等优质服务，每月新购充值会员都有惊喜礼物送，当月生日会员可享生日赠票，周一会员日持卡购票更可享等同  
	                </p>
	            </div>                   
	                  
	            <!-- 用户对商家的评价 -->
	            <div class="tab3" style="display:none;">
	                <div class="modify_alt">
						<span class="left">打个分吧：</span>
						<ul class="ui_big_ratings">
							<li lang="不推荐，不考虑" class="" id="1"></li>
							<li lang="不推荐，不考虑" id="2" class="no"></li>
							<li lang="不推荐，不考虑" class="" id="3"></li>
							<li lang="凑合，别无选择可考虑" id="4" class="no"></li>
							<li lang="凑合，别无选择可考虑" class="" id="5"></li>
							<li lang="不错，值得考虑" id="6" class="no"></li>
							<li lang="不错，值得考虑" class="" id="7"></li>
							<li lang="很棒，推荐大家去" id="8" class="no"></li>
							<li lang="很棒，推荐大家去" class="" id="9"></li>
							<li lang="最佳，绝对首选" id="10" class="no"></li>
						</ul>
						<sub class="myPoint"></sub>
						<span class="brown left"></span>
					</div> 	
					<div class="writeRemark">
						<textarea rows="2" cols="95" maxlength="75" placeholder="请输入评价(不多于75个字)"></textarea>
						<a href="javascript:void(0);" class="redBt">发&nbsp;布</a>
					</div>
                    <div class="mod_bd">
                     	<div id="hotCin_content">
                         	<ul>                             	
                             </ul>
                        	<div class="ui_loading">加载更多评论数据！</div>
                         </div>
                     </div>
	            </div>                  
       		</div>
		</div>
		
		<!-- 底部 -->
  		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>