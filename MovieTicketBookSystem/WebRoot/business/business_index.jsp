<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'business_index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/business/index.css">
	
	<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/business/json2.js"></script>
	
	<style type="text/css">
		#cinemabox {border:1px solid #ddd; width:180px; height:40px; position: absolute; visibility:hidden;
			right:80px; top:60px; font-size:13px; overflow:visible; background-color: #ddd;}
		#cinemabox span {padding: 6px 6px; background-color: #aaa; color: #fff; 
			height:20px; font-size:13px; display: block; margin-top: 2px; cursor: pointer;}
		#cinemabox span:hover {background-color: #ccc;}
	</style>
	
	<script type="text/javascript">
		$(function() {
			//点击切换影院按钮切换影院
			$("#switchCinema").click(function() {
				var $span = $("#cinemabox span");
				if($span.size() > 0) {//如果已经加载所有影院，则不再加载					
					return;
				}
				$.post('business/BusinessInformationServlet', {op: 'loadAllCinemas'}, function(data) {
					$div = $("#cinemabox");										
					var jsonArr = JSON.parse(data);
					for(var i in jsonArr) {
						var jsonObj = jsonArr[i];
						var str = "<span lang='" + jsonObj.cinemaId + "'>"+ jsonObj.cinemaName + "</span>";
						var $span = $(str);	
		           		$div.append($span);
		           		
		           		//如果不是当前影院，则设置切换影院的点击事件
		           		if(jsonObj.cinemaName != '${cinema.cinemaName}') {
		           			$span.click(function() {
								window.location.href = "business/BusinessInformationServlet?op=switchCinema&cinemaId="+$(this).attr("lang");							
							});
		           		} else {
		           			$span.attr("title", "当前影院");
		           		}
		            }
		            $div.css("visibility", "visible");
				});				
			});				
		});
	</script>
  </head>
  
  <body>
  		
    	<div class="right">
	   			<div class="title">
	   				<div class="t1"><h2>我的影院-${cinema.cinemaName} </h2></div>
	   				<div class="ratingsSide1">
						<ul class="ui_big_ratings">
							<li class="half"></li>
							<li class="on"></li>
							<li class="half"></li>
							<li class="on"></li>
							<li class="half"></li>
							<li class="on"></li>
							<li class="half"></li>
							<li class="no"></li>
							<li class=""></li>
							<li class="no"></li>
						</ul>
						<span class="bigSubp">
							<sub id="mark_integer">7</sub>
							<sup id="mark_decimal">.0</sup>
						</span>
					</div>
	   				<div style="position:absolute; top: 25px; right:130px; font-size:14px;">
	   					<a href="business/BusinessInformationServlet?op=beforeAddCinema">添加影院</a>&nbsp;&nbsp;
	   					<span style="cursor:pointer; " id="switchCinema">切换影院</span>
	   				</div>
	   				
	   				<div id="cinemabox">
	   					
	   				</div>
	   			</div>
	   			
	   			<div class="description">
	   				<div class="img">
	   					<img width="280" height="210"  src="${cinema.scene }">
	   				</div>
	   				<div class="content">
	   					<div class="address"><span><span style="color: black;">地址：</span>${cinema.address }</span></div>
	   					<div class="tel"><span><span style="color: black;">电话：</span>${cinema.tel }</span></div>
	   					<div class="spe"><p><span style="color: black;">特色：</span>${cinema.special }</p></div>
	   				</div>
	   			</div>
	   			
	   			<div class="introduction">
	   				<div class="m10">
	   					 <div class="m11"><span>简介&公告</span></div>
	   				</div>
	   				
	   				<div class="m12">
						<div class="m13">
							<div class="m14">
								<h2>影院介绍</h2>
							</div>
							<div class="m15">
								<p> ${cinema.description }</p>
							</div>
						</div>
						<div class="m16">
							<div class="m17">
									<h2>优惠政策</h2>
							</div>
							<div class="m18">
							<pre> <span>优惠信息：</span>
 ------------------------------------------------------------------------------------------------------------- 
 1.每周一至周五：白天18:00之前的场次优惠15-20元； 
 2.每周六、周日及节假日：上午10:30前开设半价早场； 
 3.每周五、周六及节假日：晚上22:00后开设两片连映午夜场；
 4.每周二：18:00前场次半价优惠；18:00后场次七折优惠； 
 5.每周一会员日：凡每周一会员持卡购票，可享受等同周二的折扣优惠--18:00前场次半价优惠，18:00后场次七折优惠。
 ------------------------------------------------------------------------------------------------------------- 
 <span>会员政策：</span> 
 ------------------------------------------------------------------------------------------------------------- 
 会员招集： 
    一次性充值500元 成为500卡会员 购票享受8.5折优惠； 
    一次性充值800元 成为800卡会员 购票享受8.折优惠 ；
    一次性充值3000元 成为贵宾金卡会员 购票享受7.5折优惠 ；
    会员可享电话订票、网上购票等优质服务，每月新购充值会员都有惊喜礼物送，当月生日会员可享生日赠票，周一会员日
    持卡购票更可享等同 。
 -------------------------------------------------------------------------------------------------------------
								 </pre>
							</div>
						</div>
	   				</div>
	   		</div>
     	</div>
     
  </body>
</html>
