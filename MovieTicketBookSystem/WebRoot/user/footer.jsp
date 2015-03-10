<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet" type="text/css" href="css/user/footer.css"/>

  </head>
  
  <body>
    <div class="footer">
       	<div class="ui_copyright">       		
       		
           	<p>    
           		<span><a href="business/business_login.html" style="text-decoration: none;font-size: 16px;color: #EB6120;"  >商家入驻</a></span>
           		<span><a href="#" style="text-decoration: none;font-size: 16px;color: #EB6120;"  >友情链接</a></span>
           		<br/>
                <span>Copyright © 2007–2014 Gewara.</span>
                <span>All Rights Reserved</span>
                <span><a href="http://www.miibeian.gov.cn/" target="_blank">沪ICP备09050772号</a></span>
                <span>ICP证：沪B2-20120044</span>			
            </p>
        </div>
    </div>
  </body>
</html>
