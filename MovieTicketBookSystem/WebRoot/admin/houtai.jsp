<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath %>"></base>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>网站后台管理</title>
	<link  href="css/admin/houtai_01.css"  rel="stylesheet" type="text/css" />	
	<link rel="StyleSheet" href="js/business/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="js/business/dtree/dtree.js"></script>
	<script charset="utf-8" src="js/business/jquery-1.11.1.min.js"></script>
	
	<script type="text/javascript">
		window.onload = function() {
			//iframe高度自适应
		    var oFrm = document.getElementById('contentFrame');
		    oFrm.onload = oFrm.onreadystatechange = function() {
		         if (this.readyState && this.readyState != 'complete') return;
		         else {
		         	var $topDiv = $("#container");//获取最外层div
		         	var $headerDiv = $("#header");//获取头部div
		         	var $frameDiv = $("#content");//获取iframe外的div
		
					//设置iframe的高度
		            $("#contentFrame").height($("#contentFrame").contents().find("body").height() + 30);
		            
		            //获取整个页面的高度
		            var height =  $headerDiv.height() +  $("#contentFrame").height();
		            //设置iframe外的div以及最外层div的高度		           
		            $frameDiv.height($("#contentFrame").height());
		             $("#container").height(height);
		         }
		    };
		};
	</script>
	  
	<style type="text/css">
		<!--
		body {
			width:1200px;
			margin:0 auto;
		}
		#content {
			width:1000px;
		}
		a:visited {
			color: #FFFFFF;
			text-decoration: none;
		}
		a:hover {
			color: #FF6600;
			text-decoration: underline;
		}
		a:link {
			text-decoration: none;
		}
		a:active {
			text-decoration: none;
		}
		-->
	</style>
</head>

<body>
<div id="container">
  <div id="header">&nbsp; 网站后台管理<span class="STYLE2">{管理中心}</span>	
    <table width="240" style="height:41px" border="0" align="right" >
      <tr>
        <td width="79" ><span class="STYLE4">时间</span></td>
        <td width="112"><span class="STYLE4">网站首页</span></td>
        <td width="110"><span class="STYLE4"><a href="admin/AdminLogoutServlet">安全退出</a></span></td>
      </tr>
    </table>
    <h3 class="STYLE4">当前管理员：${admin}</h3>
  </div>
  <div id="mainContent">
    <div id="sidebar">
		<script type="text/javascript">				
					d = new dTree('d');
			
					d.add(0,-1,'后台管理');
					d.add(1,0,'首页','admin/index.html',null,'contentFrame');
					d.add(2,0,'用户管理');
					d.add(3,2,'用户列表','admin/ShwoUserServlet',null,'contentFrame');
					d.add(4,0,'商家管理');
					d.add(5,4,'商家列表','admin/ShwoSellerServlet',null,'contentFrame');
					d.add(6,0,'权限管理');
					d.add(7,6,'修改密码','admin/admin_mmxg.html',null,'contentFrame');
					d.add(8,0,'电影管理');
					d.add(9,8,'添加电影','admin/addMovie.jsp',null,'contentFrame');
					d.add(10,8,'电影列表','admin/LoadAllMoviesServlet?op=mList',null,'contentFrame');
					d.add(11,0,'评论管理');
					d.add(12,11,'影院评论列表','admin/ShwoCinemaServlet',null,'contentFrame');
					d.add(13,11,'影片评论列表','admin/pingJia_yp.jsp',null,'contentFrame');		
					d.add(14,0,'查看统计信息');
					d.add(15,14,'查看售票情况','admin/spqk.jsp',null,'contentFrame');
					document.write(d);
					
				</script>
	</div>

    <div id="content">
      <iframe name="contentFrame" id="contentFrame" src="admin/index.html" style="width: 1000px; min-height: 800px; border:none;" scrolling="auto">
		
	  </iframe>
    </div>
  </div>
</div>
</body>
</html>

