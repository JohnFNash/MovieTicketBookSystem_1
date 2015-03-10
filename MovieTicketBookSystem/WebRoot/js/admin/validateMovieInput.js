/**
 * 验证影片信息输入数据的合法性
 */
/*片名的非空验证*/
function checkname() {
	$("input[name='movieName']")[0].style.border = '1px solid #ccc';
		var $parent = $("input[name='movieName']").parent();//获取所在的td
		if($("input[name='movieName']").val() == '') {//input内容为空时，提示
			var $span = $parent.find("span");
			if($span.size() == 0) {//之前尚未添加错误提示
				$parent.append("<span class='span'>电影名称不能为空!</span>");
			}					
			return false;
		} else {//input内容不为空，如果之前添加过错误信息，则去掉
			var $span = $parent.find("span");
			if($span) {
				$span.remove();//去掉之前添加的错误信息
			}
			return true;
		}
}		

/*类型的非空验证*/
function checkType() {
	var type = ($("input[name='type']:checked").size());
	var $parent = $("input[name='type']:last").parent();
	if(type == 0) {
		var $span = $parent.find("span");
		if($span.size() == 0) {
			$parent.append("<span class='span'>请选择影片类别！</span>");
		}
		return false;
	} else {
		var $span = $parent.find("span");
		if($span) {
			$span.remove();
		}
		return true;
	}
}

/*时长的非空验证*/
function checkTime() {
	$("input[name='time']")[0].style.border = '1px solid #ccc';
	var $parent = $("input[name='time']").parent();
	if($("input[name='time']").val() == '') {
		var $span = $parent.find("span");
		if($span.size() == 0) {
			$parent.append("<span class='span'>时长不能为空！</span>");
		}
		return false;
	} else{
		var $span = $parent.find("span");
		if($span) {
			$span.remove();
		}
		return true;
	}
}

/*导演的非空验证*/
function checkDirector() {
	$("input[name='directors']")[0].style.border = '1px solid #ccc';
	var $parent = $("input[name='directors']").parent();
	if($("input[name='directors']").val() == '') {
		var $span = $parent.find("span");
		if($span.size() == 0) {
			$parent.append("<span class='span'>导演不能为空！</span>");
		}
		return false;
	} else{
		var $span = $parent.find("span");
		if($span) {
			$span.remove();
		}
		return true;
	}
}

/*主演的非空验证*/
function checkActor() {
	$("input[name='actor']")[0].style.border = '1px solid #ccc';
	var $parent = $("input[name='actor']").parent();
	if($("input[name='actor']").val() == '') {
		var $span = $parent.find("span");
		if($span.size() == 0) {
			$parent.append("<span class='span'>主演不能为空！</span>");
		}
		return false;
	} else{
		var $span = $parent.find("span");
		if($span) {
			$span.remove();
		}
		return true;
	}
}

/*上映日期验证*/
function checkPublish() {
	var $parent = $("input[name = 'publish']").parent();
	if ($("input[name = 'publish']")[0].value == '') {
		var $span = $parent.find("span");
		if($span.size() == 0) {
			$parent.append("<span class='span'>请选择上映日期！</span>");
		}
		return false;
	} else {
		var $span = $parent.find("span");
		if($span) {
			$span.remove();
		}
		return true;
	}
}

/*上传图片验证*/
function checkFile() {
	var $parent = $("input[name = 'file']").parent();
	if ($("input[name = 'file']")[0].value == '') {
		var $span = $parent.find("span");
		if($span.size() == 0) {
			$parent.append("<span class='span'>请上传图片！</span>");
		}
		return false;
	} else {
		var $span = $parent.find("span");
		if($span) {
			$span.remove();
		}
		return true;
	}
}

/*添加影片页面的非空验证*/
$(function() {
	/*片名*/
	$("input[name='movieName']").blur(checkname).focus(function() {//获得焦点
		$(this)[0].style.border = '1px solid red';
	});//失去焦点
	
	/*标题*/
	$("input[name='movieTitle']").focus(function() {
		$(this)[0].style.border = '1px solid red';
	});
	
	/*时长*/
	$("input[name='time']").focus(function() {
		$(this)[0].style.border = '1px solid red';
	});
	$("input[name='time']").blur(checkTime);
	
	/*导演*/
	$("input[name='directors']").focus(function() {
		$(this)[0].style.border = '1px solid red';
	});
	$("input[name='directors']").blur(checkDirector);
	
	/*主演*/
	$("input[name='actor']").focus(function() {
		$(this)[0].style.border = '1px solid red';
	});
	$("input[name='actor']").blur(checkActor);
		
	/*核对信息完整，判断是否提交的验证*/	
	$("input[type='submit']").click(function() {
		return checkname() 
		&& checkType()
		&& checkTime()
		&& checkDirector()
		&& checkActor()
		&& checkPublish()
		&& checkFile();
	});
	
});