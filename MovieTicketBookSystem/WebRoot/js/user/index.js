/**
 * 首页js
 */
$(function() {
	/* 影院选择高亮切换 */
	$("div.tags li").hover(
	  function () {
	    $(this).find("a").addClass("select").end()				//当前经过的选项高亮显示
	    	   .siblings().find("a").removeClass("select");		//其它的选项失去高亮
	   	/* 获取对应的div的id */
	   	var liId = $(this).find("a").attr("id");	
	   	var idStr = '#tags' + liId.charAt(liId.length-1) + '_content';
	   	$(idStr).removeClass("none")		//显示对应的div
	   			.siblings().addClass("none");	//隐藏其它div
	  },
	  function () {
	  }
	);
	
	/* 上/下一部影片 */
	var curPage = 1;	//当前处于第几页，默认为第1页
	var maxPage = Math.ceil($("div.even_center li").size()/4);	//最大页数
	var li_width = $("div.listM").width();
	/* 为下一页添加事件 */
	$("div.even_right b").click(function() {
		if(!$("#menu_box_movie_inner").is(":animated")) {						
			if(curPage + 1 <= maxPage) {	//还未处于最后一页，则转向下一页						
				curPage = curPage + 1;
				$("#menu_box_movie_inner").animate({ marginLeft : '-='+li_width}, 600);
				if(curPage == maxPage) {	//当前处于最后一页，无法再向右翻页
					$("div.even_right b").addClass("disable");
				}
				if($("div.even_left b").hasClass("disable")) {//当前处于第2页后之后的页码，可以向左翻页
					$("div.even_left b").removeClass("disable");
				}
			}					
		}					
	});
	/* 为上一页添加事件 */
	$("div.even_left b").click(function() {
		if(!$("#menu_box_movie_inner").is(":animated")) {
			if(curPage - 1 >= 1) {	//还未到达第一页,则向左翻页
				curPage = curPage - 1;							
				$("#menu_box_movie_inner").animate({ marginLeft : '+='+li_width}, 600);
				if(curPage == 1) {	//已经到达第1页，无法继续向前翻页
					$("div.even_left b").addClass("disable");
				}
				if($("div.even_right b").hasClass("disable")) {//可以向右翻页
					$("div.even_right b").removeClass("disable");
				}
			}	
		}					
	});
});