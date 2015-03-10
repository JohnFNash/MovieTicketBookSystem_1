$(function() {	
/* 选项卡切换效果实现 */
	$("ul.nav_tabs > li").click(function() {
		$(this).addClass("selected")	//当前li高亮显示
			   .siblings().removeClass("selected"); //其它li失去高亮
		$(this).find("a")[0].style.color = "#fff";	//当前li中的a标签颜色置为#fff
		$(this).siblings().each(function() {			//其他li中的a标签颜色置为#000
			$(this).find("a")[0].style.color = "#000";
		});
			   
		var index = $("ul.nav_tabs > li").index($(this))+1;//获取当前li在父标签中的下标
		$(".tab"+index).show()	//显示当前选项卡下的内容
					  .siblings().hide();//隐藏其它选项卡下的内容					
	});
	/* 选项卡hover效果实现 */
	$("ul.nav_tabs > li").hover(function() {
		if(!$(this).hasClass("selected")) {
			$(this).find("a")[0].style.color = "#f66f00";
		}
	},
	function() {
		if(!$(this).hasClass("selected")) {
			$(this).find("a")[0].style.color = "#000";
		}
	});
	
	/* 放映场次滚动效果 */
	var _y = 0;	//鼠标离控件左上角的相对位置
	var _move = false;	//移动标记
	/* 监听mousedown事件，记录鼠标的初始位置 */
	$(".handlerC").mousedown(function(e) {
		_move = true;
		_y = e.pageY;
	});
	/* 判断tbody(即场次列表区域)的高度是否小于滚动条滚动区域的高度，如果是，则不显示滚动条 */
	if($(".scroller").height() > $(".chooseScreenings tbody").height()) {
		$(".scroller").hide();
	}
	/* 计算滚动条最大滚动高度以及滚动条与场次列表滚动的比例 */
	var maxScorllHeight = $(".scroller").height() -  $(".handlerC").height();				
	var maxTbodyScrollHeight = $(".chooseScreenings tbody").height() - ($(".chooseScreenings table").height()
		- $(".chooseScreenings tr:first").height());				
	var scrollMod = maxTbodyScrollHeight/maxScorllHeight;
	/* 监听鼠标拖拽事件 ，并滚动tbody以及滚动条*/
	$(".handlerC").mousemove(function(e) {
		if(_move) {
			var y = e.pageY - _y;
			_y = e.pageY;
			var nextTop = parseInt($(this).css("top").substring(0,$(this).css("top").length-2)) + y;						
			if(nextTop < 0 || nextTop > maxScorllHeight) {
				return;
			}
			$(this).css("top",nextTop);
			$(".chooseScreenings tbody").css("top","-=" + (scrollMod*y));						
		}
	}).mouseup(function(){
		_move = false;	
	});
});