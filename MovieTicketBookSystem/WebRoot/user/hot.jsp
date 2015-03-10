<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>热映电影</title>
	<link href="css/user/hot.css" rel="stylesheet" type="text/css" />
	<meta name="Description" />
	
	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>    
	<script type="text/javascript">
		function removeTypeFilter() {
			$("input[name='typeName']").val("");	//清空所选影片类型 
			$("form[name='hotMovieForm']")[0].submit();
		}
	
		$(function() {
			//选中侧边栏中当前页面对应的项并突出显示
			$(".ui_aside_nav li:eq(1)").addClass("select")
									   .siblings().each(function() {
									   		$(this).removeClass("select");
									   });	
			//点击影片类型进行筛选事件						   
			$(".movie_types .typeHref a, input[type='button']").click(function() {
				$("input[name='typeName']").val($(this).text());	//设置所选影片类型 
				$("form[name='hotMovieForm']")[0].submit();
			});
			
			//初始选中类型事件
			var typeSelected = '${typeName}';
			if(typeSelected != '') {
				$(".movie_types .typeHref a").each(function(){
					if(typeSelected == $(this).text()) {//找到当前选中类型，高亮显示
						$(this).addClass("selected");
						$(this).css("color", "#ffffff");
						
						var str = "<span>已选条件：</span><span class='selections'><em>" + typeSelected + "</em><a class='filterHref' href='javascript:removeTypeFilter();'>×</a></span>";
						$(".movie_yp h4").prepend($(str));						
					}
				});
			}
			
			//初始选中排序规则
			var orderSelected = '${order}';
			if(orderSelected != '') {
				$(".movie_yp option").each(function(){
					if(orderSelected == $(this).text()) {//找到当前排序规则，高亮显示
						$(this).attr("selected", "selected");						
					}
				});
			}
			
			//改变排序规则，重新加载影片信息
			$(".movie_yp select").change(function() {
				$("form[name='hotMovieForm']")[0].submit();
			});
		});
	</script>
</head>

<body>
  <!-- 导航栏 -->
  <jsp:include page="nav.jsp"></jsp:include>
  
  <div id="mainContent">
    <!-- 导航栏 -->
    <div style="float:left">
  		<jsp:include page="left.jsp"></jsp:include>
	</div>
	
    <div id="content">
    	<form action="user/HotMovieServlet" method="post" name="hotMovieForm">
			<div class="left">
				<h3>热映电影</h3>
				<div class="movie_types">
					  <div class="movie_dz">
					  	<span class="movie_feilei">影片类型&nbsp;&nbsp;&nbsp;&nbsp;</span>
					    <div class="typeHref">
						    <a href="javascript:void(0);">动作</a>
						    <a href="javascript:void(0);">喜剧</a>
						    <a href="javascript:void(0);">爱情</a>
						    <a href="javascript:void(0);">科幻</a>
						    <a href="javascript:void(0);">魔幻</a>
						    <a href="javascript:void(0);">灾难</a>	
						    <a href="javascript:void(0);">恐怖</a>
						    <a href="javascript:void(0);">纪录</a>
						    <a href="javascript:void(0);">犯罪</a>
						    <a href="javascript:void(0);">战争</a>
						    <a href="javascript:void(0);">冒险</a>
						    <a href="javascript:void(0);">动画</a>
						    <a href="javascript:void(0);">剧情</a>
						    <a href="javascript:void(0);">其它</a>
					    </div>
					    <input type="hidden" name="typeName" value="${typeName}" />
				      </div>
						<div class="queryDiv">					  
						  <span class="movie_chaxun">影片查询&nbsp;&nbsp;&nbsp;&nbsp;</span>
						  <input type="text" name="key" placeholder="请输入搜索关键词(不多于25个字)" style="width:250px;" maxlength="25" value="${key}" />					  
						  <input class="redBt" type="button" value="搜索" />	
				        </div>
			  	</div>
			  	
			  	<div class="movie_yp"> 
					<h4>共<span class="movieCount">${pageBean.totalRecords}</span>部影片</h4>
					<select name="order"> 
						 <option>按日期(由近到远)</option>
						 <option>按评分(由高到低)</option>
						 <option>按购票(由高到低)</option>
						 <option>按关注(由高到低)</option>
					</select>
				</div>
				
				<!-- 热映电影列表 -->
				<div class="onShow">
					<c:forEach items="${pageBean.dataList}" var="movie">
		             	<div class="ui_media">
		                 	<div class="ui_pic">
		                  		<a target="_blank" title="${movie.name}" href="user/GetMovieServlet?movieId=${movie.movieId}"><img width="150" height="200" alt="${movie.name}" src="${movie.post}"></a><br/>
		                  		<a target="_blank" href="user/GetMovieServlet?movieId=${movie.movieId}" class="redBt selectSeat"><span>选座购票</span></a>                                           
		                  	</div>
		                  	<div class="ui_text">
		                     	<h2>
									<a target="_blank" href="user/GetMovieServlet?movieId=${movie.movieId}" class="c333" title="${movie.name}" class="movie_title">${movie.name}</a>
									<span>(${movie.attentionCount}人关注 | ${movie.buyCount}人购票)</span>
									<span><sub>${fn:substring(firstMovie.score == null ? "7.0" : firstMovie.score+"", 0, 1)}</sub>
									<sup>.${fn:substring(firstMovie.score == null ? "7.0" :firstMovie.score+"", 2, 3)}</sup></span>
								</h2>
								<c:if test="${movie.title!=null}">
									<div class="ui_summary"><span class="first"></span>${movie.title}<span></span></div>
								</c:if>									
									
								<p class="mt5"><em>类型：<myTag:typeListToStr movieTypeList="${movie.movieTypes}"></myTag:typeListToStr> </em></p>
								<p><em>语言：</em>${movie.language}</p>
								<p><span class="left">片长： </span><span>${fn:substring((movie.duration/60+""), 0, 1)}小时${movie.duration%60}分钟</span></p>
								<p><em>导演：</em><myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr> </p>
								<p><em>主演：</em><myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr> </p>
								<br/><p>84家影院上映1286场</p>																																															
		                  	</div>
		              	</div>                           	
		           	</c:forEach>
		            </div>
		              			
				<div class="movie_sort"></div>
				<div class="movie_list"></div>
			</div>
			
			<div class="right">
			<div class="ticketsRank">
				<div>
					<font face="楷体" size="+2" color="#000000">一周购票排行</font>
				</div>
				<div>
					<ul class="movieList-2">
						<li>
							<span class="span-1" style="color:#FF0000">01</span>
							<span class="span-2"><a href="user/movie.jsp" target="_blank">一代宗师</a></span>
							<span class="span-3"><sub>9</sub><sup>.1</sup></span>
							<br><br>
						</li>					
					</ul>
				</div>
			</div>
						
		</div>
				
		<div class="pagePlugin">
			<%List<Integer> values = Arrays.asList(6,10); pageContext.setAttribute("values", values);%>
			<myTag:page form="hotMovieForm" pageBean="${pageBean}" optionValues="${values}"></myTag:page>
		</div>						
		</form>
	</div>
	 
  </div> 

    <!-- 底部 -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

