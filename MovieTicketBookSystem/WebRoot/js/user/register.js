$(function() {
	/* 表单文本框样式 */
	$("input[type='text'], input[type='password']").focus(function () {
	    $(this)[0].style.border = "1px solid #FF9A66";	    
	});
	$("input[type='text'], input[type='password']").blur(function () {
	    $(this)[0].style.border = "1px solid #cccccc";
	});		
	
	/* 表单提交时进行输入验证 */
	var $username = $("input[name='username']");
	var $password = $("input[name='password']");
	var $pwdAgain = $("input[name='pwdAgain']");
	var $validateCode = $("input[name='validateCode']");
	/* 表单输入验证 */
	var isValidate = true;
	//验证用户名输入
	$username.blur(function() {
		if($(this).val() == '') {		
			var errorInfoCount = $(this).parent().find("p").size();
			if(errorInfoCount == 0) {//如果尚未添加错误提示信息
				$(this).parent().append("<p class='errorInfo'>用户名不能为空！</p>");
			}
			isValidate = false;
		} else {
			$(this).siblings().remove();
			isValidate = true;
		}	
	});
	//验证密码输入
	$password.blur(function() {
		var password = $(this).val(); 
		if(password == '') {		
			var errorInfoCount = $(this).parent().find("p").size();
			if(errorInfoCount == 0) {//如果尚未添加错误提示信息
				$(this).parent().append("<p class='errorInfo'>密码不能为空！</p>");
			}
			isValidate = false;
		} else {
			if(password.length < 6 || password.length > 16) {//密码长度不对
				var errorInfoCount = $(this).parent().find("p").size();
				if(errorInfoCount == 0) {//如果尚未添加错误提示信息
					$(this).parent().append("<p class='errorInfo'>密码必须为6-16位(含6,16)！</p>");
				} else {
					$(this).parent().find("p").text("密码必须为6-16位(含6,16)！");
				}
				isValidate = false;
			} else {
				$(this).siblings().remove();
				isValidate = true;
			}			
		}	
	});
	//验证重新输入的密码
	$pwdAgain.blur(function() {
		if($password.val() != $(this).val()) {
			var errorInfoCount = $password.parent().find("p").size();
			if(errorInfoCount == 0) {//如果尚未添加错误提示信息
				$(this).parent().append("<p class='errorInfo'>两次输入的密码不一致,请重新输入！</p>");
			}
			isValidate = false;
		} else {
			$(this).siblings().remove();
			isValidate = true;
		}
	});			
	$("input[type='button']").click(function () {	    	    
		//ajax验证用户名是否输入正确
		$.post("../user/UserRegisterServlet", 
    		{'username':$username.val(), 'password':$password.val(), 'validateCode':$validateCode.val()}, 
    		function(data) {
    			$("p.errorInfo").remove();	//清除之前的错误提示信息
				if(data == 'code') {//验证码输入有误
					$validateCode.parent().append("<p class='errorInfo'>验证码输入错误！</p>");
					$("#image").trigger("click");//更换验证码
					isValidate = false;
				} else if(data == 'exists') {//用户名已经被占用
					$username.parent().append("<p class='errorInfo'>用户名已经存在！</p>");
					$("#image").trigger("click");//更换验证码
					isValidate = false;
				} else if(data == 'success') {//注册成功,跳转至用户首页
					location.href = 'IndexServlet';
					isValidate = true;
				} else {//注册出现异常
				}		
			});		   
		});			
	});
	
	//更换验证码
	function refresh() {  
	    //IE存在缓存,需要new Date()实现更换路径的作用  
		document.getElementById("image").src="../user/CreateCodeServlet?"+new Date();  
	} 