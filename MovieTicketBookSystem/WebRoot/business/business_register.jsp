<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	
 	<title>注册</title>
	
	<link rel="stylesheet" type="text/css" href="../css/user/register.css"/>
	
	
	<script type="text/javascript" src="../js/business/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="../js/business/jquery.validate.js"></script>
	<script type="text/javascript" src="../js/business/register.js"></script>
	<script type="text/javascript">
	        // 重载验证码  
	      function reloadVerifyCode(){  
	       var timenow = new Date().getTime() ;                         
	        document.getElementById("safeCodeImg").src="CreateCodeServlet?d="+timenow ;  
	      }  

	</script>
	
	<link rel="stylesheet" href="../css/business/errorPlacement.css" type="text/css"></link>
	
	</head>
<body>
	<div class="nav">
		<img src="../images/other/e.PNG" width="1026" height="65" />
	</div>
	
	<form id="regForm" method="post" action="loginServlet?op=reg">
		<table>	
			<tr>
				<td width="153" height="54"><span class="STYLE2">请输入用户名:</span></td>
				<td><input type="text" name="username" maxlength="15"  style="height:24px; width:190px;" /></td>
			</tr>	
			<tr>
				<td height="48"><span class="STYLE2">请输入密码:</span></td>
				<td width="180"><input type="password" name="password" maxlength="16" style="height:24px; width:190px;" /></td>
			</tr>
			<tr>
				<td height="47"><span class="STYLE2">确认输入密码:</span></td>
				<td width="180"><input type="password" name="pwdAgain" maxlength="16" style="height:24px; width:190px;" /></td>
			</tr>	
			<tr>
				<td height="47"><span class="STYLE2">请输入验证码:</span></td>
				<td><input type="text" name="code" size="5px;" maxlength="4" style="height: 24px;" ><img alt="验证码" id="safeCodeImg" src="CreateCodeServlet" width="60" height="20" onclick="javascript:reloadVerifyCode();" />
    			<a href="javascript:reloadVerifyCode();" style="text-decoration: none;color: black;">看不清</a></td>
    					
			</tr>
			<!-- <tr>
				<td height="47" colspan="2">
				  <span class="STYLE2">
				  <input type="checkbox" checked="checked">	
				  <span style="font-size:14px; color:#aaa">我接受服务协议表明您已经阅读并同意接受格瓦拉的服务协议</span></span>
				</td>				
			</tr> -->
			<tr>
				<td height="69" colspan="2" ><input type="submit" class="button" value="下一步"/><input type="button"  onclick="location.href='business_login.html'" value="返&nbsp;回"/></td>
			</tr>			
		  </table>
			
	</form>
	
	<div class="footer">
		<img src="../images/other/g.PNG" width="1027" height="60" />
	</div>
</body>
</html>
