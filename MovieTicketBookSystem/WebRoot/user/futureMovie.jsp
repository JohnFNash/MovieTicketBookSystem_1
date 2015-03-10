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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>即将上映影片</title>
	<link rel="stylesheet" href="css/user/futureMovie.css" type="text/css"/>

	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>    
	<script type="text/javascript">
		function removeTypeFilter() {
			location.href = "user/FutureMovieServlet?typeName=&key="+$("input[name='key']").val()+"&order="+$("select[name='order']").val(); 
		}
		
		$(function() {
			//选中侧边栏中当前页面对应的项并突出显示
			$(".ui_aside_nav li:eq(2)").addClass("select")
									   .siblings().each(function() {
									   		$(this).removeClass("select");
									   });
									   
			//点击影片类型进行筛选事件						   
			$(".movie_types .typeHref a, input[type='button']").click(function() {
				location.href = "user/FutureMovieServlet?typeName="+$(this).text()+"&key="+$("input[name='key']").val()+"&order="+$("select[name='order']").val(); 
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
				location.href = "user/FutureMovieServlet?typeName="+$(".movie_types .typeHref a.selected").text()
					+"&key="+$("input[name='key']").val()+"&order="+$(this).val();
			});
		});
	</script>
</head>

<body>
	<!-- 导航栏 -->
    <jsp:include page="nav.jsp"></jsp:include>
	
	<div class="bottom">
		<!-- 导航栏 -->
  		<jsp:include page="left.jsp"></jsp:include>
		
		<div class="right">
			<h2>即将上映</h2>
			   
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
					 <option>按关注(由高到低)</option>
				</select>
			</div>
		   
		   <div class="main-left-bottom">
		   		
		   		<div class="onShow">
				<c:forEach items="${pageBean.dataList}" var="movie">
					<span class="date">${movie.publish.getMonth()+1}月${movie.publish.getDate()}日</span>
	             	<div class="ui_media">
	                 	<div class="ui_pic">
	                  		<a target="_blank" title="${movie.name}" href="user/GetMovieServlet?movieId=${movie.movieId}"><img width="150" height="200" alt="${movie.name}" src="${movie.post}"></a><br/>                                    
	                  	</div>
	                  	<div class="ui_text">
	                     	<h2>
								<a target="_blank" href="user/GetMovieServlet?movieId=${movie.movieId}" class="c333" title="${movie.name}" class="movie_title">${movie.name}</a>
								<span>(<span>${movie.attentionCount}</span>人关注<span>&nbsp;${movie.likeCount}</span>人想看)</span>									
							</h2>
							<p class="mt5"><em>类型：<myTag:typeListToStr movieTypeList="${movie.movieTypes}"></myTag:typeListToStr> </em></p>
							<p><em>语言：&nbsp;</em>${movie.language}</p>
							<p><span>片长： </span><span>${fn:substring((movie.duration/60+""), 0, 1)}小时${movie.duration%60}分钟</span></p>
							<p><em>导演：&nbsp;</em>${movie.director.actorName}</p>
							<p><em>主演：</em><myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr> </p>																																													
	                  	</div>
	              	</div>                           	
	           	</c:forEach>
	            </div>
		   			   		
							
				<div class="pagePlugin">
					<%List<Integer> values = Arrays.asList(6,10,20); pageContext.setAttribute("values", values);%>
					<myTag:page form="hotMovieForm" pageBean="${pageBean}" optionValues="${values}"></myTag:page>
				</div>
								
		   </div>
			
		   <div class="main-right">
			<div class="movieTop10">
				<div>
					<font face="楷体" size="+2" color="#000000">最期待影片排行榜</font>
				</div>
				<div>
					<ul class="movieList-2">
						<c:forEach items="${attentionMovies}" var="movie" varStatus="status">
							<li>
								<span class="span-1">${status.index+1}</span>
								<span class="span-2"><a target="_blank" href="user/GetMovieServlet?movieId=${movie.movieId}">${movie.name}</a></span>
								<span class="span-3"><span>${movie.attentionCount}</span>人想看</span>
								<br><br>
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
