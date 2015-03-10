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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		/* body css */
		body{
			 background-color: white; 
		}
	
		/* 右边css  */
		.right{
			width: 98%;
			height: 98%;
			float:right;
			/* border: 1px solid orange; */
		}
		/* 标题 css  */
		.title{
			width: 100%;
			height: 56px;
			/* border: 1px solid pink; */
		}
		.title .t1{
			height: 55px;
			float: left;
			margin-left: 180px;
			/* border: 1px solid red; */
		}
		.bigSubp {
		    display: inline-block;
		    height: 30px;
		    width:52px;
		    margin-top:10px;
		    font-weight:500;
		    line-height: 30px;
		    vertical-align: -4px;
		}
		sub {
		    color: #f60;
		    font-size: 22px;
		    font-style: italic;
		    line-height: 24px;
		    margin-left: 10px;
		    vertical-align: middle;
		}
		sup {
		    color: #f60;
		    font-size: 16px;
		    font-style: italic;
		    font-weight: normal;
		    line-height: 20px;
		    margin-left: 2px;
		    vertical-align: top;
		}
		.ratingsSide1 {
			width:200px;
			height:55px;
		    float: left;
		    /* border: 1px solid red; */
		}
		.ratings li, .ui_big_ratings li, .initRating span {
		    background: url("images/other/ico_star.gif?v=54654") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
		}
		.ui_big_ratings {
		    float: left;
		    height: 24px;
		    padding-left:15px;
		    padding-bottom:5px;
		    line-height: 24px;
		    overflow: hidden;
		    width: 130px;
		}
		.ui_big_ratings li {
		    background-position: -25px 0;
		    cursor: default;
		    float: left;
		    height: 24px;
		    overflow: hidden;
		    width: 13px;
		}
		.ui_big_ratings li.no {
		    background-position: -38px 0;
		}
		.ui_big_ratings li.on {
		    background-position: -12px 0;
		}
		.ui_big_ratings li.half {
		    background-position: 1px 0;
		}
		.description{
			margin-top:5px;
			width: 100%;
			height: 274px;
			border-top:1px solid #EEEEEE;
			/* border: 1px solid yellow; */
		}
		.description .img{
		background-color:#E7E7E7;
			width: 280px;
			height: 234px;
			float: left;
			margin-top:20px;
			/* border: 1px solid purple; */
		}
		.description .img img{
			padding-top: 13px;
		}
		.description .content{
			width: 600px;
			height: 234px;
			font-size:14px;
			float: left;
			margin-top:20px;
			margin-left:10px;
			/* border: 1px solid red; */
		}
		.description .content .address{
			color: #999999;	
		}
		.description .content .tel{
			color: #999999;
			margin-top: 10px;
		}
		.description .content .spe{
			color: #999999;
			margin-top: 10px;
		}
		
		
		/* 简介&公告 css  */
		.introduction{
			width:100%;
			
			/* border: 1px solid skyblue; */
		}
		.introduction .m10{
			width:100%;
			height: 42px;
			border-bottom:2px solid #EB6120;
			float: left;
			
		}
		.introduction .m10 .m11{
			width: 120px;
			height: 42px;
			float: left;
			background-color:#EB6120;
			color:white;
			font-family: "微软雅黑","宋体";
		    font-size: 16px;
		    font-weight: bold;
		}
		.introduction .m10 .m11 span{
			position: relative;
			top: 10px;
			left: 15px;
		}
		.m12{
			float:left;
			margin-top: 20px;
			/* border: 1px solid green;  */ 
		}
		.m12 .m13{
			
			 /* border: 1px solid yellow;  */ 
		}
		.m12 .m14{
			padding-top: 15px;
		}
		.m14 h3{
			font-weight: bold;
		}
		.m12 .m13 .m15{
			width: 96%;
			font-size:16px;
			padding-left: 10px;
		}
		.m12 .m16{
			 /* border: 1px solid red; */ 
		}
		.m12 .m13 .m15 p{
			 text-indent: 2em;
		}
		
		.m12 .m16 .m18{
			width: 96%;
			font-size:16px;
			padding-left: 10px;
			/* border: 1px solid red;  */
		}
		.m12 .m16 .m17 p{
			 text-indent: 2em;
		}
		.m18 span{
			font-size: 18px;
			font-weight: bold;
		}
		</style>

  </head>
  
  <body>
  		
    	<div class="right">
	   			<div class="title">
	   				<div class="t1"><h2>我的影院-重庆UME国际影城-江北店</h2></div>
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
	   			</div>
	   			
	   			<div class="description">
	   				<div class="img">
	   					<img width="280" height="210"  src="images/cinema/scene002.jpg">
	   				</div>
	   				<div class="content">
	   					<div class="address"><span><span style="color: black;">地址：</span>江北区北城天街8号北城天街购物广场B区5F(5楼老馆B区,4楼新馆A区)</span></div>
	   					<div class="tel"><span><span style="color: black;">电话：</span>023-67701166</span></div>
	   					<div class="spe"><pre><span style="color: black;">特色：</span>“山城首家经国家认证的五星级影城，占地面积7000多平米，共有十五个放映厅，是目前国内不
	可多见的多厅现代化影城。 ” </pre></div>
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
								<p> UME重庆国际影城是香港UME影院集团继北京、上海设立影城后，于2004年12月投资兴建的第三座影城，也是山城首家经国家认证的五星级影城，占地面积7000多平米，共有十五个放映厅，是目前国内不可多见的多厅现代化影城。 每天
								排映十余部新片，放映一百多场次获给市民最多选择的“电影超市”之美誉。其豪华贵宾厅、情侣影厅为国内首创。 重庆UME国际影城（江北）平面布局图，犹如一列豪华列车，十五个厅排列有序，从售票大厅购票后，顺着长达120米的长廊
								依次进入1—10放映厅，坐电梯下四楼通过天桥通道即可进入新馆11——15放映厅。 除了道具橱窗、电影玩具柜、主题咖啡厅，新馆还设有情侣套座影厅和电影书吧等全国首创的全新设施。</p>
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
 5.每周一为UME会员日：凡每周一会员持卡购票，可享受等同周二的折扣优惠--18:00前场次半价优惠，18:00后场次七折优惠。
 ------------------------------------------------------------------------------------------------------------- 
 <span>会员政策：</span> 
 ------------------------------------------------------------------------------------------------------------- 
 UME会员招集： 
    一次性充值500元 成为500卡会员 购票享受8.5折优惠； 
    一次性充值800元 成为800卡会员 购票享受8.折优惠 ；
    一次性充值3000元 成为贵宾金卡会员 购票享受7.5折优惠 ；
    会员可享电话订票、网上购票等优质服务，每月新购充值会员都有惊喜礼物送，当月生日会员可享生日赠票，周一会员日
    持卡购票更可享等同 。
								 </pre>
							</div>
						</div>
	   				</div>
	   		</div>
     	</div>
     </div>
  </body>
</html>
