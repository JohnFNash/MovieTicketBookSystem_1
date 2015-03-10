<%@ page language="java" import="java.util.*,com.team3.mbts.util.*,com.team3.mbts.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag" %>
<!DOCTYPE HTML>
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>用户列表</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<style type="text/css">
		.del{ cursor:pointer;}
		.yf{ width:724px; margin:auto;}
		a:link {
			color: #000000;
			text-decoration: none;
			font-family:Arial, Helvetica, sans-serif;
		}
		a:hover {
			color: #FF6633;
			text-decoration: underline;
		}
		a:visited {
			text-decoration: none;
		}
		a:active {
			text-decoration: none;
		
		}
		.even{ background-color:#CCCCCC;}
		.odd{ background-color:#DBEAF9;}
		table{border-collapse:collapse;}
		thead{background-color:#66a9bc;}
		.STYLE7 {font-family: "Times New Roman", Times, serif; font-size: 20px;}
		    .STYLE8 {color: #00000}
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
  	<form action="admin/ShwoUserServlet" name="showuser" method="post">
	  <div class="yf">
	    <h1 align="left">用户列表</h1>
        <p align="left">
          <select name="select" style="width:150px; height:27px;">
            <option style="height:27px; ">按编号查询</option>
             <option style="height:27px; ">按用户名查询</option>
          </select>
          &nbsp;&nbsp;
          
          <input type="text" name="key" placeholder="请输入搜索关键词" style="width:140px; height:27px;">
          <input name="submit" type="submit" style=" height:26px;"  value=" 确定"/>
        </p>
        <br/>
	  </div>	
	
  	  <div style="margin:auto; width:800px;">
		<myTag:page form="showuser" pageBean="${pageBean}">
		</myTag:page>
	  </div>
   </form>
	  
	  <table align="center"  style="border-collapse:collapse; border:0; width:724px;">
	   	<thead>
	    <tr>
	      <td width="127" height="57"><div align="center" class="STYLE7">用户编号</div></td>
	      <td width="156"><div align="center" class="STYLE7">用户头像</div></td>
	      <td width="96"><div align="center" class="STYLE7">用户名</div></td>
	      <td width="317"><div align="center" class="STYLE7">操作</div></td>
	    </tr>
		</thead>
		
	  	 <c:forEach items="${pageBean.dataList}" var="userInfo">
		     <tr>
		      <td height="67"><div align="center">${userInfo.userId}</div></td>
		      <td><div align="center"><img src="${userInfo.headPath }" width="50" height="50"></div></td>
		      <td><div align="center">${userInfo.account}</div></td>
		      <td>
			    <div align="center" class="del"><a href="admin/DeletUserServlet?userId=${userInfo.userId }" onclick="return confirm('确认删除?');" class="STYLE8">删除</a></div></td>
		    </tr>
	 	</c:forEach> 
	  </table>  	  
  
  </body>
</html>
