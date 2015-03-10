<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
			
	<meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<title>Custom Drop-Down List Styling</title>
	
	<link rel="stylesheet" type="text/css" href="js/user/artDialog-5.0.3/skins/default.css" />
    <script src="js/user/artDialog-5.0.3/artDialog.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="js/business/five/css/style.css" />
	<link rel="stylesheet" type="text/css" href="js/business/five/css/noJS.css" />
	<link rel="stylesheet" type="text/css" href="css/business/addListing.css" />
		
    <script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>    
    
	<script type="text/javascript">
			
			function DropDown(el) {
				this.dd = el;
				this.placeholder = this.dd.children('span');
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = '';
				this.index = -1;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						return false;
					});

					obj.opts.on('click',function(){
						var opt = $(this);						
						obj.val = opt.text();
						obj.index = opt.index();
						obj.placeholder.text(obj.val);
						if(opt.parent().attr("id") == 'ul_1') {
							$("input[name='movieId']").val(opt.attr("lang"));
							$("input[name='movieId']").trigger("change");
						} else if(opt.parent().attr("id") == 'ul_2') {
							$("input[name='hallId']").val(opt.attr("lang"));
							$("input[name='hallId']").trigger("change");
						} else if(opt.parent().attr("id") == 'ul_3') {
							$("input[name='date']").val(opt.attr("lang"));
							$("input[name='date']").trigger("change");
						} else if(opt.parent().attr("id") == 'ul_4') {
							$("input[name='hour']").val(opt.attr("lang"));
							$("input[name='hour']").trigger("change");
						} else if(opt.parent().attr("id") == 'ul_5') {
							$("input[name='minute']").val(opt.attr("lang"));
							$("input[name='minute']").trigger("change");
						}						
										
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			};

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});
			
				$(function() {

				var dd = new DropDown( $('#dd1') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});
			
			$(function() {

				var dd = new DropDown( $('#dd2') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});
			
				$(function() {

				var dd = new DropDown( $('#dd3') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});
			
				$(function() {

				var dd = new DropDown( $('#dd4') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});
		</script>
		
	
	<script type="text/javascript">
		$(function(){		
			var invalidate = false;//表示排期是否合法,true为不合法
			//点击提交按钮验证排期信息输入是否完整
			$("#addBtn").click(function() {
				var hasEmpty = false;
				$("input[type='text'], input[type='hidden']").each(function() {
					if($(this).val() == '') {
						hasEmpty = true;
					}
				});
				var infoValida = !hasEmpty && !invalidate;
				if(infoValida) {//如果填写的信息合法
    				var movieIdV = $("input[name='movieId']").val();
    				var hallIdV = $("input[name='hallId']").val();
    				var dateV = $("input[name='date']").val();
    				var hourV = $("input[name='hour']").val();
    				var minuteV = $("input[name='minute']").val();
    				var opriceV = $("input[name='oprice']").val();
    				var cpriceV = $("input[name='cprice']").val();
					$.post('business/ScreeningServlet', {op:'screening', movieId:movieIdV, hallId:hallIdV, date:dateV, hour:hourV, 
						minute:minuteV, oprice:opriceV, cprice:cpriceV}, 
						function(data) {							
							if(data == 'input') {//输入数据不合法
								var dialog = artDialog({
								    width: 260,
								    height:100,
								    title: '消息',
								    content: "添加失败，请重新输入!",
								    lock:true,
								    time:2000
								});
							} else if(data == 'success') {//输入数据合法
								var dialog = artDialog({
								    width: 260,
								    height:100,
								    title: '消息',
								    content: "添加成功!",
								    lock:true,
								    time:2000
								});
								window.location.reload();
								$("input[name='oprice']").val('');
								$("input[name='cprice']").val('');
							} 
						});
				}
			});
			
			$("input[type='hidden']").change(function() {
				var hasEmpty = false;
				$("input[type='hidden']").each(function() {
					if($(this).val() == '') {
						hasEmpty = true;
					}
				});
				if(!hasEmpty) {//时间，影厅填充完整
					$.post('business/ScreeningServlet?op=validate',{'movieId':$("input[name='movieId']").val(),'hallId':$("input[name='hallId']").val(),'date':$("input[name='date']").val(),
					'hour':$("input[name='hour']").val(),'minute':$("input[name='minute']").val()},function(data){
						if(data!='')
						{ 
							var dialog = artDialog({
							    width: 260,
							    height:100,
							    title: '消息',
							    content: data,
							    lock:true,
							    time:2000
							});
							invalidate = true;
						}
						else
						{							
							invalidate = false;
						}
					});
				}
			});
		});
		
	</script>
	
  </head>
  
  <body>
    <div class="content" style="background-color: #fff;">
    		<div class="title">
    			<div class="t1"><h2>我的影院-排期管理</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>添加排期</span></div>
	   			</div>
    		</div>
    		
    	<div style="background-color: #fff;">
    		<input type="hidden" name="movieId" />
    		<input type="hidden" name="hallId" />
    		<input type="hidden" name="date" />
    		<input type="hidden" name="hour" />
    		<input type="hidden" name="minute" />
    	   <div class="container" style="z-index:100;">
			<section class="main" >
				<div class="wrapper-demo">
					<span>选择影片：</span>
					<div id="dd" class="wrapper-dropdown-3" tabindex="1">
						<span>选择影片</span>
						<ul id="ul_1" class="dropdown">
							<c:forEach items="${movieList }" var="movie">
								<li lang="${movie.movieId}"><a href="#">${movie.name }</a></li>
							</c:forEach>
						</ul>
					</div>
					
                </div>
			</section>	
			</div>
			
			<div class="container">
			<section class="main" style="z-index:100;">
				<div class="wrapper-demo">
					<span>选择影厅：</span>
					<div id="dd1" class="wrapper-dropdown-3" tabindex="1">
						<span>选择影厅</span>
						<ul id="ul_2" class="dropdown">
							<c:forEach items="${hallList }" var="hall">
								<li lang="${hall.id }"><a href="#">${hall.no }号厅</a></li>
							</c:forEach>
						</ul>
					</div>
					
                </div>
			</section>	
			</div>
			
			<div class="container" style="z-index:100;">
			<section class="main">
				<div class="wrapper-demo">
					<span>选择日期：</span>
					<div id="dd2" class="wrapper-dropdown-3" tabindex="1">
						<span>选择日期</span>
						<ul id="ul_3" class="dropdown">
							<c:forEach items="${dateList }" var="date">
								<li lang="${date }"><a href="#">${date}</a></li>
							</c:forEach>
						</ul>
					</div>
					
                </div>
			</section>	
			</div>
			
			<div class="container" style="height: 120px; z-index:90;border: 1px solid white;">
			<section class="main">
				<div class="wrapper-demo" style="margin-top: 20px;">
					<span>选择时间：</span>
					<div id="dd3" class="wrapper-dropdown-3" tabindex="1" style="width: 120px;">
						<span>选择小时</span>
						<ul id="ul_4" class="dropdown">
							<li lang="10"><a href="#">10点</a></li>
							<li lang="11"><a href="#">11点</a></li>
							<li lang="12"><a href="#">12点</a></li>
							<li lang="13"><a href="#">13点</a></li>
							<li lang="14"><a href="#">14点</a></li>
							<li lang="15"><a href="#">15点</a></li>
							<li lang="16"><a href="#">16点</a></li>
							<li lang="17"><a href="#">17点</a></li>
							<li lang="18"><a href="#">18点</a></li>
							<li lang="19"><a href="#">19点</a></li>
							<li lang="20"><a href="#">20点</a></li>
							<li lang="21"><a href="#">21点</a></li>
							<li lang="22"><a href="#">22点</a></li>
						</ul>
					</div>
					
                </div>
			</section>	
			</div>
			
			<div class="container" style="height: 120px;z-index:90;border: 1px solid white;">
			<section class="main">
				<div class="wrapper-demo" style="margin-top: 20px;">
					<span>选择时间：</span>
					<div id="dd4" class="wrapper-dropdown-3" tabindex="1" style="width: 120px;">
						<span>选择分钟</span>
						<ul id="ul_5" class="dropdown">
							<li lang="00"><a href="#">00分</a></li>
							<li lang="10"><a href="#">10分</a></li>
							<li lang="20"><a href="#">20分</a></li>
							<li lang="30"><a href="#">30分</a></li>
							<li lang="40"><a href="#">40分</a></li>
							<li lang="50"><a href="#">50分</a></li>
							
						</ul>
					</div>
					
                </div>
			</section>	
			</div>
			
			<div class="price">
					<div class="ori"><span>请输入影片原价：</span><input type="text" name="oprice" style="height: 25px;"></div>
					<div class="cur"><span>请输入影片现价：</span><input type="text" name="cprice" style="height: 25px;"></div>
			</div>
			<div class="bu">
				<!-- <a href="" class="button blue">Confirm</a> -->
				<input id="addBtn" type="button" class="button blue" value="添加" style="font-family: '微软雅黑' ,'宋体' ;font-size: 18px;"/>
				<input type="button" class="button blue" value="返回" style="font-family: '微软雅黑' ,'宋体' ;font-size: 18px; " onclick="location.href='business/ScreeningServlet?op=listing' ">
				<!-- <a href="business/business_listing.jsp" class="button blue">Cancel</a> -->  
			</div>
		</div>
    		
    </div>
  </body>
</html>
