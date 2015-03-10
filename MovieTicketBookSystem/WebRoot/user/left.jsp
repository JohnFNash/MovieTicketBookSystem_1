<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
     
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	 <link rel="stylesheet" type="text/css" href="css/user/left.css"/>

  </head>
  
  <body>
    <div class="index_left">
    	<div id="ui_left" class="leftNavBox" style="position:absolute; bottom:0px; top:80px;">
        	<a class="ui_quickBuy" title="快速购票" target="_blank" href="#" style="display:none;">快速购票</a>
            <ul class="ui_aside_nav">
            	<li class="select"><a href="user/IndexServlet">电影首页</a></li>
                <li><a href="user/HotMovieServlet">热映电影<span id="keyNum_curMovieListCount"></span></a></li>
                <li><a href="user/FutureMovieServlet">即将上映<span id="keyNum_featureMovieCount"></span></a></li>
                <li><a href="user/LoadCinemasServlet">电影院<span id="keyNum_cinemaCount"></span></a></li>
                <li><a href="user/MovieStoreServlet">电影库</a></li>
            </ul>
        </div>
    </div>
  </body>
</html>
