$(function() {
	/* 点击显示浮动登录框 */
	var dialog;
	$("#loginHref").bind("click",function(){
		//清空文本框的内容及错误提示
		//$("#boxy input[type='text'], #boxy input[type='password']").val("");
		$("#boxy p.errorInfo").each(function() {
			$(this)[0].style.visibility = "hidden";
		});
		
		 dialog = artDialog();
		 dialog.title("格瓦拉登录");
		 dialog.lock(true);
		 dialog.content(document.getElementById("boxy"));
		 $("#cancer").click(function (){
			dialog.close();
		  });
	});
	
	/* 导航栏高亮切换 */
	$("ul.ui_inline > li").hover(
	  function () {
	    $(this).addClass("isHover")
	    	   .siblings().removeClass("isHover");
	  },
	  function () {
	    $(this).removeClass("isHover");
	  }
	);
	
	/* 表单文本框样式 */
	$("input[name='username'], input[name='validateCode'], input[name='password']").focus(function () {
	    $(this)[0].style.border = "1px solid #FF9A66";
	});
	$("input[name='username'], input[name='validateCode'], input[name='password']").blur(function () {
	    $(this)[0].style.border = "1px solid #cccccc";
	});		
	
	/* 表单提交时进行输入验证 */
	$("input[type='submit']").click(function () {
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
		var validateCode = $("input[name='validateCode']").val();
	    if(username.length == 0) {	//如果用户名为空
	    	$("input[name='username']").trigger("focus");
	    	return false;
	    } else if(password.length == 0) {	//如果密码为空
	    	$("input[name='password']").trigger("focus");
	    	return false;
	    } else if(validateCode.length == 0) {	//如果验证码为空
	    	$("input[name='validateCode']").trigger("focus");
	    	return false;
	    } else {	//ajax进行验证
	    	$.post("user/UserLoginServlet", {'username':username, 'password': password, 'validateCode':validateCode}, function(data) {	    		
    			if(data == 'code') {
    				$("#boxy p.errorInfo:eq(2)")[0].style.visibility = "visible";
    				$("#image").trigger("click");//更换验证码
    			} else if(data == 'other') {
    				$("#boxy p.errorInfo:eq(0)")[0].style.visibility = "visible";
    				//验证码正确，隐藏验证码错误提示
    				$("#boxy p.errorInfo:eq(2)")[0].style.visibility = "hidden";
    			} else if(data == 'success'){
	    			dialog.close();	    			
	    			location.reload();//刷新页面
	    			$("#boxy input[type='text'], #boxy input[type='password']").val("");
	    		}    			
	    	});
	    	return false;
	    }
	});				
	
	
});