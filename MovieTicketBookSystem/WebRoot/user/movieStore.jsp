<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	 <base href="<%=basePath%>"/>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
    <title>电影库</title>
        
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    	

	<link rel="stylesheet" type="text/css" href="css/user/movieStore.css">
	
	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>    	
	
	<script type="text/javascript">
		//移除区域筛选条件
		function removeAreaFilter() {
			$(".condition .areaDd a.selected").removeClass("selected");
			filterMovie();
		}
		
		//移除影片类型筛选条件
		function removeTypeNameFilter() {
			$(".condition .typeNameDd a.selected").removeClass("selected");
			filterMovie();
		}
		
		//移除搜索关键字筛选条件
		function removeKeyFilter() {
			$("input[name='key']").val("");
			filterMovie();
		}
		
		//移除时间段筛选条件
		function removeTimespanFilter() {
			$(".condition .timeDd a.selected").removeClass("selected");
			filterMovie();
		}
		
		//对影片进行过滤
		function filterMovie() {
			var area = $(".condition .areaDd a.selected").text();
			var typeName = $(".condition .typeNameDd a.selected").text();
			var timespan = $(".condition .timeDd a.selected").attr("lang");
			
			$("input[name='area']").val(area);
			$("input[name='typeName']").val(typeName);
			$("input[name='timespan']").val(timespan);
						
			$("form[name='filterForm']")[0].submit();	//提交表单						
		}
		
		$(function() {
			//选中侧边栏中当前页面对应的项并突出显示
			$(".ui_aside_nav li:eq(4)").addClass("select")
									   .siblings().each(function() {
									   		$(this).removeClass("select");
									   });
			
			var $tiaojian_p = $("div.tiaojian_p");
			//根据过滤时选择的国家/地区进行相应设置
			var area = '${area}';
			if(area) {
				$(".condition .areaDd a").each(function() {
					if($(this).text() == area) {
						$(this).addClass("selected");
					}
				});
				$tiaojian_p.prepend("<span class='selections selections1'><em>" + area + "</em><a class='filterHref' href='javascript:removeAreaFilter();'>×</a></span>");
			}
			//根据过滤时选择的类型名称进行相应设置
			var typeName = '${typeName}';
			if(typeName) {
				$(".condition .typeNameDd a").each(function() {
					if($(this).text() == typeName) {
						$(this).addClass("selected");
					}
				});
				$tiaojian_p.prepend("<span class='selections selections1'><em>" + typeName + "</em><a class='filterHref' href='javascript:removeTypeNameFilter();'>×</a></span>");
			}
			//根据过滤时输入的搜索关键字进行相应设置
			var key = '${key}';
			if(key) {
				$("input[name='key']").val(key);
				$tiaojian_p.prepend("<span class='selections selections1'><em>" + key + "</em><a class='filterHref' href='javascript:removeKeyFilter();'>×</a></span>");
			}
			//根据过滤时输入的时间段进行相应设置
			var timespan = '${timespan}';
			if(timespan) {
				$(".condition .timeDd a").each(function() {
					if($(this).attr("lang") == timespan) {
						$(this).addClass("selected");
						$tiaojian_p.prepend("<span class='selections selections1'><em>" + $(this).text() + "</em></span>");
					}
				});				
			}
												   
			//点击国家/地区对影片进行过滤			
			$(".condition .areaDd a").click(function() {
				$(this).addClass("selected")
					   .siblings().removeClass("selected");				
				filterMovie();
			});
			
			//点击影片类型对影片进行过滤			
			$(".condition .typeNameDd a").click(function() {
				$(this).addClass("selected")
					   .siblings().removeClass("selected");
				filterMovie();
			});	
			
			//点击时间段对影片进行过滤			
			$(".condition .timeDd a").click(function() {
				$(this).addClass("selected")
					   .siblings().removeClass("selected");
				filterMovie();
			});
			
			//根据搜索关键字进行对影片进行筛选
			$("div.search input[type='button']").click(function() {
				var key = $("input[name='key']").val();
				if(key != '') {
					filterMovie();
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
  
    <div class="mid" >
    	<div style="margin-left:-70px;">
   			<jsp:include page="left.jsp"></jsp:include>
   		</div>
   		
    	<div class="ui_right">
    	  <form action="user/MovieStoreServlet" method="post" name="filterForm">
			<div class="left">			
	 			<div class="mod_kong" style="margin-bottom:0px">
	 				<div class="mod_hd addT">
						<h2>电影库</h2>				
					</div>
			
					<div class="mod_bd">					  					  						  
					  	<input type="hidden" name="area" />
					  	<input type="hidden" name="typeName" />
					  	<input type="hidden" name="timespan" />
					  
						<ul class="ulist">
							<li>
								<div class="condition">
									<dl class="clear dlTy">
										<dt>
											<span class="typeTitle mr10">选择地区 </span>
										</dt>
										<dd class="areaDd">
											<a href="javascript:void(0);">大陆</a>
											<a href="javascript:void(0);">港台</a>
											<a href="javascript:void(0);">美国</a>
											<a href="javascript:void(0);">日本</a>
											<a href="javascript:void(0);">英国</a>
											<a href="javascript:void(0);">法国</a>
											<a href="javascript:void(0);">香港</a>
											<a href="javascript:void(0);">台湾</a>
											<a href="javascript:void(0);">印度</a>
											<a href="javascript:void(0);">意大利</a>
											<a href="javascript:void(0);">韩国</a>
											<a href="javascript:void(0);">澳大利亚</a>
										</dd>
									</dl>
								</div>
							</li>
							
							<li>
								<div class="condition">
									<dl class="clear dlTy">
										<dt>
											<span class="typeTitle mr10">影片类型</span>
										</dt>
										<dd class="typeNameDd">
											<a href="javascript:void(0);">动作</a>
											<a href="javascript:void(0);">喜剧</a>
											<a href="javascript:void(0);">爱情</a>
											<a href="javascript:void(0);">科幻</a>
											<a href="javascript:void(0);">奇幻</a>
											<a href="javascript:void(0);">灾难</a>
											<a href="javascript:void(0);">恐怖</a>
											<a href="javascript:void(0);">纪录</a>
											<a href="javascript:void(0);">犯罪</a>
											<a href="javascript:void(0);">战争</a>
											<a href="javascript:void(0);">冒险</a>
											<a href="javascript:void(0);">动画</a>
											<a href="javascript:void(0);">剧情</a>
											<a href="javascript:void(0);">其他</a>
										</dd>
									</dl>
								</div>
							</li>
							
							<li>
								<div class="condition">
									<dl class="clear dlTy">
										<dt>
											<span class="typeTitle mr10">选择时间</span>
										</dt>
										<dd class="timeDd">
											<a href="javascript:void(0);" lang="2016-01-01_2016-12-31">2016</a>
											<a href="javascript:void(0);" lang="2015-01-01_2015-12-31">2015</a>
											<a href="javascript:void(0);" lang="2014-01-01_2014-12-31">2014</a>
											<a href="javascript:void(0);" lang="2013-01-01_2013-12-31">2013</a>
											<a href="javascript:void(0);" lang="2012-01-01_2012-12-31">2012</a>
											<a href="javascript:void(0);" lang="2011-01-01_2011-12-31">2011</a>
											<a href="javascript:void(0);" lang="2010-01-01_2010-12-31">2010</a>
											<a href="javascript:void(0);" lang="2009-01-01_2009-12-31">2009</a>
											<a href="javascript:void(0);" lang="2008-01-01_2008-12-31">2008</a>
											<a href="javascript:void(0);" lang="2007-01-01_2007-12-31">2007</a>
											<a href="javascript:void(0);" lang="2006-01-01_2006-12-31">2006</a>
											<a href="javascript:void(0);" lang="2000-01-01_2005-12-31">2005~2000</a>
											<a href="javascript:void(0);" lang="1990-01-01_2000-01-01">90年代</a>
											<a href="javascript:void(0);" lang="1980-01-01_1900-01-01">80年代</a>
											<a href="javascript:void(0);" lang="1950-01-01_1980-01-01">更早</a>
											<a href="javascript:void(0);" lang="all">全部</a>
										</dd>
									</dl>
								</div>
							</li>					
							<li>
								<div class="condition mt10">
									<dl class="clear dlTy">
										<dt>
											<span class="typeTitle search">影片查询</span>
										</dt>
										<dd>
											<div class="search">
												<input type="text" style="width:240px; font-size:13px; color:#999999; " name="key" maxlength="25" placeholder="请输入搜索关键字(不多于25个字)"/>
												<input class="redBt" type="button"  value="搜索"/>
											</div>
										</dd>
									</dl>
								</div>
							</li>
						</ul>					  
					</div>
				</div>
		
		<div class="selectedMenus clear" style="margin-top:10px; margin-bottom:10px">
			<!-- <div class="right1">
				<div class="ui_simulate">				
					<select name="order">
						<option>默认(按日期)</option>
						<option>按关注(由高到低)</option>
						<option>按购票(由高到低)</option>
					</select>					
				</div>
			</div> -->
			<div class="left1 tiaojian_p">
				<!-- <span class="fs15">已选条件：</span> -->
				<!-- <span class="selections selections1">2015</span>
				<span class="selections selections1">即将上映</span> -->
				<span>共<b class="colorRed sNum">${pageBean.totalRecords}</b>部影片</span>
			</div>
		</div>
		
		<div class="movieList">
			<ul>			
				<c:forEach items="${pageBean.dataList}" var="movie">					
				<li class="effectLi" style="display:block;">
					<div class="ui_media">
						<div class="ui_pic">
							<a title="${movie.name}" target="_blank" href="user/GetMovieServlet?movieId=${movie.movieId}">
								<img width="120" height="160" alt="${movie.name}" src="${movie.post}">
							</a>
						</div>
						<div class="ui_text">
							<div class="title">
								<div class="tl">
									<h2>
									<a class="color3" target="_blank" title="${movie.name}" href="user/GetMovieServlet?movieId=${movie.movieId}">${movie.name}</a>
									</h2>
								</div>
								<div class="tr">
									<span class="ml5">
										(<span>${movie.attentionCount}</span>人关注 )
									</span>									
									<span class="grade"><sub>${fn:substring(movie.score == null ? "7.0" : movie.score+"", 0, 1)}</sub>
									<sup>.${fn:substring(movie.score == null ? "7.0" :movie.score+"", 2, 3)}</sup></span>
								</div>
							</div>
							<p class="mt10">上映时间：${movie.publish.toLocaleString().split(" ")[0]}</p>
							<p>电影类型：<myTag:typeListToStr movieTypeList="${movie.movieTypes}"></myTag:typeListToStr></p>
							<p>国家：&nbsp;${movie.area}</p>
							<p>语言：&nbsp;${movie.language}</p>
							<p>片长： ${fn:substring((movie.duration/60+""), 0, 1)}小时${movie.duration%60}分钟</p>
							<p>导演： <myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr></p>
							<p>主演：<myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr> </p>
						</div>
					</div>
				</li>
			</c:forEach>				
			</ul>
		</div>
		<myTag:page form="filterForm" pageBean="${pageBean}"></myTag:page>
	</div>		
	</form>
				
			<div class="right">
			<div class="m14">
				<div class="t"><h2>正在热映</h2></div>
				<div class="m"><a target="_blank" href="user/HotMovieServlet">更多</a></div>
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
								<em>导演：</em><myTag:directorListToStr directorList="${movie.directors}"></myTag:directorListToStr>
							</p>
							<p class="c999" title="主演：<myTag:actorListToStr actorList='${movie.actors}'></myTag:actorListToStr>" style="line-height:22px; word-wrap:break-word; height:44px; overflow:hidden;">
								<em>主演：</em><myTag:actorListToStr actorList="${movie.actors}"></myTag:actorListToStr>								
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
