<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'business_account.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


	<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var error = false;
			
		/* 	$("#username").blur(function(){
				var username = $("#username").val();
				if(username == '') {
					showError('username', '用户名不能为空');
					error = true;
					return;
				}
				else {
					$.post('business/BusinessInformationServlet?op=usernameCheck', {'username':username}, function(data){
					if(data=='用户名已存在') {
						showError('username', '用户名已存在');
					} else {
						$("#username").css({"border-color":"green"});
						$("#usernameTip").css({"display":"none"});
						error = true;
					}
				});
				}
			
			}); */
	
		 	$("#oldpass").blur(function(){
				var username = $("#username").val();
			 	if(username=='') {
					showError('username', '用户名不能为空');
					error = true;
					return;
				} 
	
				var oldpass = $("#oldpass").val();
				if(oldpass =='') {
					showError('oldpass', '密码不能为空');
					error = true;
					return;
				}
				else {
					$.post("business/BusinessInformationServlet?op=oldpassCheck", { 'username':username, 'oldpass':oldpass}, function(data){
					if(data=='error') {
						showError('oldpass', '密码错误');
						error = true;
						return;
					} else {
						$("#oldpass").css({"border-color":"green"});
						$("#oldpassTip").css({"display":"none"});
						
					}
				});
				}
	
			
			}); 
	
			$("#newpass").blur(function(){
				var newpass = $("#newpass").val();
				if(newpass == '') {
					showError('newpass', '新密码不能为空');
					error = true;
					return;
				}
				else {
					$("#newpass").css({"border-color":"green"});
					$("#newpassTip").css({"display":"none"});
				}
			});
	
			$("#newpassAgain").blur(function(){
				var newpass = $("#newpass").val();
				 if(newpass == '') {
					showError('newpass', '新密码不能为空');
					error = true;
					return;
				} 
	
				var newpassAgain = $("#newpassAgain").val();
				if(newpassAgain != newpass) {
					showError('newpassAgain', '与输入的新密码不一致');
					error = true;
					return;
				}
				else {
					$("#newpassAgain").css({"border-color":"green"});
					$("#newpassAgainTip").css({"display":"none"});
				}
			});
			
			$("#submit").click(function(event){
				/* $("#username").blur(); */
				$("#oldpass").blur(); 
				$("#newpass").blur();
				$("#newpassAgain").blur();
	
				  if(!error) {
					var username = $("#username").val();			
					var newpass = $("#newpass").val();
					$.post('business/BusinessInformationServlet?op=pwdModify', {'newpass':newpass,'username':username}, function(data) {
					
					if(data=='success')
					{
							//$("input[type='text]',input[type='password']").attr("value","");
							$("#modifySuccess").css({'display':'inline'});
							
					}
						
					});
				} 
	
				event.preventDefault();
				return false;
			});
		});
	
		function showError(formSpan, errorText) {
			$("#" + formSpan).css({"border-color":"red"});
			$("#" + formSpan + "Tip").empty();
			$("#" + formSpan + "Tip").append(errorText);;
			$("#" + formSpan + "Tip").css({"display":"inline"});
		};
	</script>

	<style type="text/css">
		/* 整体框架css  */
		.all{
			width: 95%;
			height: 100%;
			/* border: 1px solid orange; */
		}
		/* 标题css  */
		.title{
			width: 100%;
			height: 100px;
			
			/* border: 1px solid pink; */
		}
		.title .t1{
			width:100%;
			height: 55px;
			float: left;
			text-align:center;
			/* border: 1px solid red; */
		}
		.title .m10{
			width:100%;
			height: 42px;
			margin-top:40px;
			border-bottom:2px solid #EB6120;
			float: left;
			
		}
		.title .m10 .m11{
			width: 120px;
			height: 42px;
			float: left;
			background-color:#EB6120;
			color:white;
			font-family: "微软雅黑","宋体";
		    font-size: 16px;
		    font-weight: bold;
		}
		.title .m10 .m11 span{
			position: relative;
			top: 10px;
			left: 27px;
		}
		
		/* 表单css  */
		 .container{
			/* border: 1px solid pink; */
			float: left;
			width: 100%;
		}
		.container form{
			margin-left: 350px;
		}
		.form-group label{
			font-size: 18px;
			font-weight: bold;
		}
		.form-group{
			margin-top: 15px;
		}
		.form-group .col-sm-10{
			margin-top: 10px;
		}
		.form-group .btn{
			margin-left: 80px;
			border-radius: 5px;
			background-color: skyblue;
			color: white;
			font-family: "微软雅黑","宋体";
		}
	</style>
  </head>
  
  <body>
    	<div class="title">
    			<div class="t1"><h2>我的影院-信息管理</h2></div>
    			<div class="m10">
	   					 <div class="m11"><span>账户修改</span></div>
	   			</div>
	   			
	   			<div class="container" style="margin-top:100px;">
					<form class="form-horizontal" role="form"  method="post">
						  <div class="form-group">
							    <label for="username" class="col-sm-2 control-label">用户名</label>
							    <div class="col-sm-10">
							      <input type="text" disabled="disabled" class="form-control" value="${seller.account }" style="width:250px;height: 28px;" id="username" placeholder="Username"><span id="usernameTip" style="display:none;color:red;"></span>
							    </div>
						  </div>
						  <div class="form-group">
							    <label for="oldpass" class="col-sm-2 control-label">旧密码</label>
							    <div class="col-sm-10">
							      <input type="password" class="form-control" style="width:250px;height: 28px;" id="oldpass" placeholder="Old Password"><span id="oldpassTip" style="display:none;color:red;"></span>
							    </div>
						  </div> 
						  <div class="form-group">
							    <label for="newpass" class="col-sm-2 control-label">新密码</label>
							    <div class="col-sm-10">
							      <input type="password" class="form-control" style="width:250px;height: 28px;" id="newpass" placeholder="New Password"><span id="newpassTip" style="display:none;color:red;"></span>
							    </div>
						  </div>
						  <div class="form-group">
							    <label for="newpassAgain" class="col-sm-2 control-label">再次确认新密码</label>
							    <div class="col-sm-10">
							      <input type="password" class="form-control" style="width:250px;height: 28px;" id="newpassAgain" placeholder="Again New Password"><span id="newpassAgainTip" style="display:none;color:red;"></span>
							    </div>
						  </div>
						  <div class="form-group">
						   	 <label class="col-sm-2 control-label">  </label>
						 	 <button type="submit" class="btn btn-primary" id="submit" style="text-align:center;height: 28px;">确认修改</button>
						  </div>
					</form>
				</div>
				
				<div id="modifySuccess" class="alert alert-success alert-dismissable" style="width:50%;margin-left:40%;display:none;">
				  <strong>Success!</strong> 你已成功修改密码！
				</div>
    	</div>
    	
  </body>
</html>
