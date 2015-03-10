<%@ page language="java" import="java.util.*,com.team3.mbts.util.*,com.team3.mbts.entity.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
.del {cursor:pointer;}
form { width:724px; margin:0 auto;}
a:link {
	color: #000000;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #FFFF33;
}
a:hover {
	text-decoration: underline;
	color: #33FF99;
}
a:active {
	text-decoration: none;
	color: #000000;
}
.even{ background-color:#CCCCCC;}
.odd{ background-color:#DBEAF9;}
table{border-collapse:collapse;}
thead{background-color:#66a9bc;}
.STYLE4 {font-size: 20px; font-family: "Times New Roman", Times, serif; }
.STYLE5 {color: #000000}
-->
</style>
<script type="text/javascript" src="js/user/jquery-1.11.1.js">
</script>
<script type="text/javascript">
	$(function(){
		$("tbody>tr:odd").addClass("odd");
		$("tbody>tr:even").addClass("even");
			
	});
</script>
</head>

<body>
 <form action="admin/ShwoSellerServlet" method="post" name="showseller">
<div class="zb">
 <h1 align="left">商家列表</h1>
      
        <p align="left">
		 <select name="consultSelect" style="width:150px; height:27px;"> 
						 <option style="height:27px;">按商家编号查询</option>
						 <option style="height:27px;">按商家名查询</option>
						</select>&nbsp;&nbsp;
						<input name="consultPut"  type="text" style="width:120px; height:23px;" placeholder="请输入搜索关键词" />
                        <input name="consultBtn"  type="submit" style=" height:26px;"  value=" 确定"/>
        </p>
        <br/>
</div>
   <div style="margin-left:-40px;">
		<myTag:page form="showseller" pageBean="${pageBean }">
		</myTag:page>
   </div>
   
  <table style="width:724px; margin:0 auto; border:0; align:center; border-collapse:collapse">
 	<thead> 
    <tr>
      <td width="127" height="59"><div align="center" class="STYLE4">商家编号</div></td>
      <td width="155"><div align="center" class="STYLE4">商家名称</div></td>
      <td width="171"><div align="center" class="STYLE4">商家影院编号</div></td>
      <td width="218"><div align="center" class="STYLE4">操作</div></td>
    </tr>
	</thead>
	<tbody>
    <c:forEach items="${pageBean.dataList}" var="seller">
	     <tr>
	      <td height="67"><div align="center">${seller.sellerId}</div></td>
	      <td><div align="center">${seller.account }</div></td>
	      <td><div align="center">${cinema.cinemaId}</div></td>
	      <td>
		    <div align="center" class="del">
		      <div align="center"><a href="admin/DeletSellerServlet?sellerId=${seller.sellerId}" onclick="return confirm('确认删除?');" class="STYLE8">删除</a>&nbsp;</div>
	       </div></td>
	    </tr>
 	</c:forEach>
 	</tbody> 
</table> 

</form> 
</body>
</html>
