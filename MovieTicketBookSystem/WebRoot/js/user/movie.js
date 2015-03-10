/**
 * 电影详情页相关的js
 */
$(function() {	
	//点击影院，随之切换场次信息
	$("#cinemaPanel a").click(function() {
		var $a = $(this);
		$.post('user/GetMovieServlet', {op:'switchCinema', day:$("#timeOutside a.select").attr("id"), cinemaId:$(this).attr("id"), movieId: $("#idSpan").text()}, function(data) {
			$a.addClass("select")	//当前选中的影院高亮显示
				   .siblings().removeClass("select");//其他影院去掉高亮
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
				  + "<td><a target='_blank' class='redBt' href='user/BeforeSelectSeatServlet?screeningId="+jsonObj.screeningId+"'><span>选座购票</span></a></td>"
                  + "</tr>";				 						
           $(".chooseScreenings tbody").append($(str)); 						
			}			
		});
	});
	
	//点击评论选项卡加载评论
	$(".nav_tabs li:eq(2)").click(function() {
		var size = $("#hotCin_content li").size();
		if(size == 0) {//首次点击，尚未加载评论
			$.post('user/GetMovieServlet', {op:'loadRemarks', movieId: $("#idSpan").text(), prevRecords:size}, function(data) {					
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
	                         +"<h4 class='yTitle'><a href='#' target='_blank' title='"+jsonObj.title+"'>"+jsonObj.title+"</a></h4>"
	                            +"<div class='remarkContent'>"
	                            	+"<div>"+jsonObj.content+"</div>"
	                                +"<a href='user/GetMovieRemarkServlet?movieRemarkId="+jsonObj.remarkId+"' target='_blank' class='detailed'>详情...</a>"
	                            +"</div>"
	                            +"<div class='footer'>"
	                            	+"<span class='funcTime'>"+jsonObj.time+"</span><a title='赞成' href='javascript:void(0);'>"+jsonObj.likeCount+"</a>"								
	                            +"</div></div></div></li>";
	               $("#hotCin_content ul").append($(str));
				}
				if($("#hotCin_content li").size() == 0) {//暂无影评
					$("#hotCin_content .ui_loading").text("暂无影评!");
				}
			});
		}
	});
	
	//点击加载更多评论
	$(".ui_loading").click(function() {
		var size = $("#hotCin_content li").size();	 			
		$.post('user/GetMovieServlet', {op:'loadRemarks', movieId: $("#idSpan").text(), prevRecords:size}, function(data) {	 			
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
                         +"<h4 class='yTitle'><a href='#' target='_blank' title='"+jsonObj.title+"'>"+jsonObj.title+"</a></h4>"
                            +"<div class='remarkContent'>"
                            	+"<p>"+jsonObj.content+"</p>"
                                +"<a href='user/movieRemark.jsp' target='_blank' class='detailed'>详情...</a>"
                            +"</div>"
                            +"<div class='footer'>"
                            	+"<span class='funcTime'>"+jsonObj.time+"</span><a title='赞成' href='javascript:void(0);'>"+jsonObj.likeCount+"</a>"								
                            +"</div></div></div></li>";
				$("#hotCin_content ul").append($(str)); 						
			}			
		});	 				
	});
	
	
	
});