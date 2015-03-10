/**
 * 
 */
$(function() {
	/* 上/下一部影片 */
	var curPage = 1;	//当前处于第几页，默认为第1页
	var maxPage = Math.ceil($("div.even_center li").size()/6);	//最大页数
	var li_width = $("div.even_center").width();
	/* 为下一页添加事件 */
	$("span.even_right b").click(function() {
		if(!$("#menu_box_movie_inner").is(":animated")) {						
			if(curPage + 1 <= maxPage) {	//还未处于最后一页，则转向下一页						
				curPage = curPage + 1;
				$("#menu_box_movie_inner").animate({ marginLeft : '-='+li_width}, 600);
				if(curPage == maxPage) {	//当前处于最后一页，无法再向右翻页
					$("span.even_right b").addClass("disable");
				}
				if($("span.even_left b").hasClass("disable")) {//当前处于第2页后之后的页码，可以向左翻页
					$("span.even_left b").removeClass("disable");
				}
			}					
		}					
	});
	/* 为上一页添加事件 */
	$("span.even_left b").click(function() {
		if(!$("#menu_box_movie_inner").is(":animated")) {
			if(curPage - 1 >= 1) {	//还未到达第一页,则向左翻页
				curPage = curPage - 1;							
				$("#menu_box_movie_inner").animate({ marginLeft : '+='+li_width}, 600);
				if(curPage == 1) {	//已经到达第1页，无法继续向前翻页
					$("span.even_left b").addClass("disable");
				}
				if($("span.even_right b").hasClass("disable")) {//可以向右翻页
					$("span.even_right b").removeClass("disable");
				}
			}	
		}					
	});
	
	//点击影片，随之切换场次信息
	$("#menu_box_movie_inner li a").click(function() {
		var $a = $(this);
		$.post('user/GetCinemaServlet', {op:'switchMovie', day:$("#timeOutside a.select").attr("id"), movieId:$(this).attr("id"), cinemaId: $("#idSpan").text()}, function(data) {
			$a.addClass("select")	//当前选中的影片高亮显示
				   .parent().siblings().find("a").removeClass("select");//其他影片去掉高亮
			//清除之前的场次信息
			$(".chooseScreenings tbody > tr").remove();
			var jsonArr = JSON.parse(data); 	
			for(var i in jsonArr) {
				var jsonObj = jsonArr[i];
				var str = "<tr>"
					  + "<td class='opiTime'><b>"+jsonObj.startTime+"</b><em>预计 "+jsonObj.endTime+" 散场</em></td>"
				  + "<td class='opiEdition'><span>"+jsonObj.language+"</span>&nbsp;<span>"+jsonObj.is3D+"</span></td>"
				  + "<td class='opiRoom'><span>" + jsonObj.videoHallNo + "</span><span>号厅</span></td>"
				  + "<td class='opiPrice'><span class='nowPrice'>" + jsonObj.price + "</span>&nbsp;<span class='originalPrice'>"+jsonObj.originalPrice+"</span></td>"
				  + "<td><a target='_blank' class='redBt' href='user/selectSeat.jsp'><span>选座购票</span></a></td>"
                  + "</tr>";				 						
           $(".chooseScreenings tbody").append($(str)); 						
			}			
		});
	});
		
	//点击评论选项卡加载评论
	$(".nav_tabs li:eq(2)").click(function() {
		var size = $("#hotCin_content li").size();
		if(size == 0) {//首次点击，尚未加载评论
			$.post('user/GetCinemaServlet', {op:'loadRemarks', cinemaId: $("#idSpan").text(), prevRecords:size}, function(data) {	 					
				var jsonArr = JSON.parse(data); 	
				for(var i in jsonArr) {
					var jsonObj = jsonArr[i];	 							 						
	                 var str = "<li class='movieText'>"
	                	+"<div class='ui_media'>"
	                        +"<div class='ui_pic'>"
	                        	+"<a href='#' target='_blank' title='"+jsonObj.userName+"'>"
									+"<img src='images/other/blank.gif' style='background: url("+jsonObj.headPath+") center center no-repeat;' height='50' width='50'>"
								+"</a>"
								+"<a target='_blank' title='"+jsonObj.userName+"' href='#'>"+jsonObj.userName+"</a>"						
	                         +"</div>"
	                         +"<div class='ui_text'>"                                	
	                            +"<div class='remarkContent'>"
	                            	+"<p>"+jsonObj.content+"</p>"
	                            +"</div>"
	                            +"<div class='footer'>"
	                            	+"<span class='funcTime'>"+jsonObj.time+"</span><a title='赞成' href='javascript:void(0);'>"+jsonObj.likeCount+"</a>"								
	                            +"</div></div></div></li>";
	               $("#hotCin_content ul").append($(str));
				}
				if($("#hotCin_content li").size() == 0) {//暂无评价
					$("#hotCin_content .ui_loading").text("暂无评价!");
				}
			});
		}
	});

	//点击加载更多评论
	$(".ui_loading").click(function() {
		var size = $("#hotCin_content li").size();	 			
		$.post('user/GetCinemaServlet', {op:'loadRemarks', cinemaId: $("#idSpan").text(), prevRecords:size}, function(data) {	 					
			var jsonArr = JSON.parse(data); 	
			for(var i in jsonArr) {
				var jsonObj = jsonArr[i];	 							 						
             var str = "<li class='movieText'>"
            	+"<div class='ui_media'>"
                    +"<div class='ui_pic'>"
                    	+"<a href='#' target='_blank' title='"+jsonObj.userName+"'>"
							+"<img src='images/other/blank.gif' style='background: url("+jsonObj.headPath+") center center no-repeat;' height='50' width='50'>"
						+"</a>"
						+"<a target='_blank' title='"+jsonObj.userName+"' href='#'>"+jsonObj.userName+"</a>"						
                     +"</div>"
                     +"<div class='ui_text'>"                                	
                        +"<div class='remarkContent'>"
                        	+"<p>"+jsonObj.content+"</p>"
                        +"</div>"
                        +"<div class='footer'>"
                        	+"<span class='funcTime'>"+jsonObj.time+"</span><a title='赞成' href='javascript:void(0);'>"+jsonObj.likeCount+"</a>"								
                        +"</div></div></div></li>";
             $("#hotCin_content ul").append($(str)); 						
			}
			if(jsonArr.length == 0 &&  ( $("#hotCin_content .ui_loading").text() != '暂无评价!') ) {//没有更多评论
				$(".ui_loading").text("没有更多数据!");
			}
		});	 				
	});

	//点击发布评论来发布评论
	$("div.writeRemark a").click(function() {			
		if($("#global_user_infobox").size() > 0) {//已经登录
			var content = $(".writeRemark textarea").val();//评论
			if(content == '') {//未输入评论，弹出提示框
				var dialog = artDialog({
				    width: 160,
				    height:40,
				    title: '提示',
				    content: "评论不能为空!",
				    lock:true,
				    time:2000	
				});
				return;
			}
			$.post('user/WriteCinemaRemarkServlet', {cinemaId:$("#idSpan").text(), 
			score:$("sub.myPoint").text(), remarkContent:content}, function(data) {
				if(data != 'fail') {//评论发表成功
					var dialog = artDialog({
					    width: 260,
					    height:100,
					    title: '消息',
					    content: "评论发表成功!您的评论可能要经过一段时间才会显示在页面上。",
					    lock:true,
					    time:2000	
					});
					$(".writeRemark textarea").val("");//清空评论框
					//重置分数为未打分
					$(".ui_big_ratings li:even").removeClass("half");
					$(".ui_big_ratings li:odd").removeClass("on");
					$(".ui_big_ratings li:odd").addClass("no");
					//清空分数文本
					$("sub.myPoint").text("");
					$("sub.myPoint").next().text("");
				}
			});
		} else {//尚未登录
			$("#loginHref").trigger("click");//弹出登录悬浮框
		}
	});	
});