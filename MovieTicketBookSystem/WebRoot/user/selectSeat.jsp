<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>选座订票</title>
	<link rel="stylesheet" type="text/css" href="css/user/selectSeat.css"/>
	
	<script type="text/javascript" src="js/user/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/business/json2.js"></script>
	
	<script type="text/javascript">
		$(function() {
			var $divNode = $("#seatList");
			var rows = parseInt('${screening.videoHall.row}'), cols = parseInt('${screening.videoHall.col}');
			for(var i=0; i<rows; i++) {											
				var $dlNode =$("<dl></dl>");
				$divNode.append($dlNode);
				for(var j=0; j<cols; j++) {
					var $ddNode = $("<dd><div class='noSeat'></div></dd>");			
					$dlNode.append($ddNode);			
				}				
				$divNode.append($dlNode);
			}
			
			/* 显示有座位的位置 */
			<c:forEach items="${seatList}" var="seat">
				var $div = $("#seatList dl:eq(${seat.row-1}) dd:eq(${seat.col-1}) > div");
				$div[0].className = 'on';
				$div.attr("title", '${seat.row}排${seat.col}座');
			</c:forEach>
			
			/* 显示已选座位的位置 */
			<c:forEach items="${ticketList}" var="ticket">
				$("#seatList dl:eq(${ticket.row-1}) dd:eq(${ticket.col-1}) > div")[0].className = 'un';
			</c:forEach>
			
			var $ddNode = $("#seatList dd");			
			$ddNode.click(function() {
				var $child= $(this).find("div");
				var row = $("#seatList dl").index($child.parent().parent());
				var col = $("#seatList dl:eq("+(row)+") > dd").index($(this));					
				if($child.hasClass("on")) {//之前为可选状态							
					$child.removeClass("on");
					$child.addClass("in");										
					
					$("#selectedSeat").show();
					$(".choosedSeat_1").hide();
					$("#selectedSeat").append("<div style='width:100px; display:inline-block; margin-left:3px; margin-top:6px;' id="+(row+"_"+col)+"  class='seatSpan_1'>"
						+(row+1)+"行"+(col+1)+"座"+'${fn:split(screening.price, ".")[0]}'+"元</div>");
					$("b.price").text($("#selectedSeat div").size()*'${screening.price}');
						
					return;							
				} else if($child.hasClass("in")) {//之前为未被选为状态
					$child.removeClass("in");
					$child.addClass("on");
										
					$("#"+row+"_"+col).remove();	//移除座位
					var seatSelected = $("#selectedSeat div").size();//判断是否还剩下选了的座位
					$("b.price").text($("#selectedSeat div").size()*'${screening.price}');
					if(seatSelected == 0) {//座位均已移除
						$("#selectedSeat").hide();
						$(".choosedSeat_1").show();
					}
				}
			});
			
			//点击完成选座时事件处理
			$("#completeSel").click(function(){		
				if($("#global_user_infobox").size() > 0) {//已经登录
					var noseat = [];
					var $dlList = $(".seatList dl");		
					$(".seatList dd > div").each(function() {
						var $dlParent = $(this).parent().parent();
						if($(this).hasClass('in')) {
							var row = $dlList.index($dlParent) + 1;
							var col = $dlParent.find("dd").index($(this).parent()) + 1;
							noseat.push({'row':row, 'col':col});
						}
					});	
					if(noseat.length > 0) {//已经完成选座
						$("input[name='seat']").val(JSON.stringify(noseat));
						$("form[name='seatForm']").submit();
					} else {
						alert("请先选座位!");
					}
				} else {//尚未登录
					$("#loginHref").trigger("click");//弹出登录悬浮框
				}															
			});
		});
		
	</script>
</head>

<body>
	<!-- 导航栏 -->
    <jsp:include page="nav.jsp"></jsp:include>
	
	<div class="background">
	
		<div class="top">
			<ul class="movieOrderStep">
				<li class="step1">1.选择影片场次</li>
				<li class="step2">2.在线选座，填写手机号码</li>
				<li class="step3">3.确认订单并支付</li>
				<li class="step4">4.终端机取票</li>
			</ul>
		</div>
		
		
		<div class="main">
		
			<div class="left">
				<div class="chooseSeat">
					<div class="seatTable">
						<div class="inner">
							<div class="seatInfo">
								<span><span class="on">&nbsp;&nbsp;&nbsp;</span>可选座位</span>
								<span><span class="in">&nbsp;&nbsp;&nbsp;</span>已选座位</span>
								<span><span class="un">&nbsp;&nbsp;&nbsp;</span>不可选座位</span>
							</div>
						</div>
						
						<div class="inner">
							<div class="seat">
								<div class="room_screen">
									${cinema.cinemaName}${screening.videoHall.no}号厅银幕
									<span>&nbsp;</span>
								</div>
								
								<div class="showSeat">
									<div class="seatList" id="seatList">																	
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="explain">
					<h2>场次说明：</h2>
					<p>1、3D眼镜免押金</p>
					<p>2、1.4m以下儿童半价</p>	
				</div>
				<div class="useExplain">
					<h2>使用说明：</h2>
					<p>1、每笔订单最多可选购5张电影票，情侣座不单卖；</p>
					<p>2、选座时，请尽量选择相邻座位，不要留下单个座位；</p>
					<p>3、点击“完成选座”后，请在15分钟内完成支付，超时系统将释放你选定的座位。</p>
				</div>
				
			</div>
			
			<div class="right">
			
				<div class="box">
				
					<div class="pewSeat">
					
						<div class="showMovie">
						
							<div class="movie_pic">
								<a target="_blank">
									<img width="96" height="128" src="${screening.movie.post}">
								</a>
							</div>
							
							<div class="movie_text">
								<h4>${screening.movie.name}</h4>
								<p>语言版本：${screening.movie.language} ${screening.movie.is3D ? "3D" : "2D"}</p>
								<p>片长： ${fn:substring((screening.movie.duration/60+""), 0, 1)}小时${screening.movie.duration%60}分钟</p>
							</div>
							
						</div>
						
						<div class="changCi">
							<p>
								<em>影院：</em>
								${cinema.cinemaName}
							</p>
							<p>
								<em>影厅：</em>
								${screening.videoHall.no}号厅
							</p>
							<p>
								<em>场次：</em>
								<!-- <span>12-31(周三) 10:30</span> -->
								<span>${screening.startTime.toString().substring(5,16)}</span>
								<!-- <span>&nbsp;&nbsp;&nbsp;&nbsp;更换场次</span> -->
							</p>
						</div>
						
						<dl class="choosedSeat">
							<dt><em>座位：</em></dt>
							<dd>
								<div id="selectedSeat" class="clear" style="display: none;">
									<!-- <div style="top: 317px; left: 1442px; position: static;" class="pews">C排06座58元</div>
									<div style="top: 334px; left: 1442px; position: static;" class="pews">C排07座58元</div> -->
								</div>
								
								<div class="choosedSeat_1">
									<span class="seatSpan_1">还未选择座位</span>
								</div>
								<p>单击
									<span class="seatSpan_2">左侧座位图</span>
									选择座位，再次单击取消。
								</p>
							</dd>
						</dl>
					</div>
					
					<hr color="#f96">
					<div>
						<div class="sum">
							<b>总计：</b>
							<span>
								￥
								<b class="price">0</b>
								<span>元</span>
							</span>
						</div>
					</div>
					<form name="seatForm" action="user/SelectSeatServlet" method="post">
						<input type="hidden" name="screeningId" value="${screening.id}" />
						<input type="hidden" name="cinemaId" value="${screening.videoHall.cinema.cinemaId}" />
						<input type="hidden" name="seat" />
						<br/><input type="submit" id="completeSel" class="redBt" style="border:none;cursor:pointer;" value="完成选座">
					</form>					
				</div>
			</div>
		</div>	
	</div>
	
	<!-- 底部 -->
  	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
