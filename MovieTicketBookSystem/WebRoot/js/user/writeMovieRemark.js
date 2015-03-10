/**
 * 写评论中实现打分效果的js
 */
$(function() {
	/* 实现打分效果 */
	var $brown = $(".modify_alt .brown");
	var $myPoint = $(".modify_alt .myPoint");
	var $list = $(".ui_big_ratings li");
	var isMarked = false;	//用于表示是否已经打分的标识，如果尚未打分，失去焦点时默认打分为8分
	//监听hover事件
	$(".ui_big_ratings li").hover(function() {
		isMarked = false;	//重置标识为尚未打分
		//清空之前的打分情况
		$(".ui_big_ratings li").each(function(i) {
			if(i % 2 == 0) {
				$(this)[0].className = "";
			} else {
				$(this)[0].className = "no";
			}		
		});	
		
		//本次打分
		var index = $list.index($(this));										
		$(".ui_big_ratings li:lt(" + (index+1) + ")").each(function(i) {
			if(i % 2 == 0) {
				$(this)[0].className = "half";
			} else {
				$(this)[0].className = "on";
			}
		});
		//设置分数以及提示文本的内容
		$myPoint.text((index+1)+".0");
		$("input[name='point']").val((index+1)+".0");
		$brown.text($(this).attr("lang"));					
	}, function() {	
		
	}).click(function() {						
		isMarked = true;	//将标识置为已经打分
	});
	
	//监听ui_big_ratings的hover事件
	$(".ui_big_ratings").hover(function() {
		
	}, function() {
		if(isMarked) {//如果已经打过分数，则hover移出之后不做任何事
			return;
		}
		
		//清空之前的打分情况
		$(".ui_big_ratings li").each(function(i) {
			if(i % 2 == 0) {
				$(this)[0].className = "";
			} else {
				$(this)[0].className = "no";
			}		
		});	
		//尚未打分，则将分数置为默认的8.0分
		$(".ui_big_ratings li:lt(" + (8) + ")").each(function(i) {
			if(i % 2 == 0) {
				$(this)[0].className = "half";
			} else {
				$(this)[0].className = "on";
			}		
		});	
		
		$myPoint.text(8 + ".0");
		$("input[name='point']").val(8+".0");
		$brown.text($(".ui_big_ratings li:eq(7)").attr("lang"));
	});

});