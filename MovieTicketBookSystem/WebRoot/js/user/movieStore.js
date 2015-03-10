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
	var href = "user/MovieStoreServlet?op=filter";
	var area = $(".condition .areaDd a.selected").text();
	var typeName = $(".condition .typeNameDd a.selected").text();
	var timespan = $(".condition .timeDd a.selected").attr("lang");			
	var key = $("input[name='key']").val();
	if(area) {
		href += "&area=" + area;
	}
	if(typeName) {
		href += "&typeName=" + typeName;
	}
	if(key) {
		href += "&key=" + key;
	}
	if(timespan) {
		href += "&timespan=" + timespan;
	}
	location.href = href;
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
				$tiaojian_p.prepend("<span class='selections selections1'><em>" + $(this).text() + "</em><a class='filterHref' href='javascript:removeTimespanFilter();'>×</a></span>");
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