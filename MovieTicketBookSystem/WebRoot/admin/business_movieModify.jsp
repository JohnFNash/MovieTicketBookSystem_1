<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'business_movieModify.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 编辑器导入文件 -->
	<script charset="utf-8" src="js/business/kindeditor/kindeditor.js">

	</script>
	
	<script charset="utf-8" src="js/business/kindeditor/lang/zh_CN.js">
	
	</script>
	
	<script charset="utf-8" src="js/business/kindeditor/plugins/code/prettify.js">
	
	</script>
	
	<script>
	
	KindEditor.ready(function(K) {
	
	var editor1 = K.create('textarea[name="article.content1"]', {
	
	cssPath : 'js/business/kindeditor/plugins/code/prettify.css',
	
	uploadJson : 'js/business/kindeditor/jsp/upload_json.jsp',
	
	fileManagerJson : 'js/business/kindeditor/jsp/file_manager_json.jsp',
	
	allowFileManager : true,
	
	afterCreate : function() {
	
	var self = this;
	
	K.ctrl(document, 13, function() {
	
	self.sync();
	
	document.forms['example'].submit();
	
	});
	
	K.ctrl(self.edit.doc, 13, function() {
	
	self.sync();
	
	document.forms['example'].submit();
	
	});
	
	}
	
	});
	
	prettyPrint();
	
	});
	
	</script>
	
	
		<style type="text/css">
		/* 整体框架css  */
		.all{
			width: 100%;
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
		.title .t2{
			width:90%;
			height: 32px;
			float: left;
			margin-left:10px;
			font-size:18px;
			font-weight:bold;
			/* border: 1px solid red; */
		}
		/* content css  */
		.content{
			width:90%;
			float: left;
			margin-left: 30px;
			/* border: 1px solid green; */
		}
		.content table {
			margin: 0px auto;
		}
		.content table tr{
			height: 20px;
		}
	</style>

  </head>
  
  <body>
    <div class="all">
    		<div class="title">
    			<div class="t1"><h2 align="left">我的影院-影片管理</h2>
    			</div>
    			<div class="t2">修改影片</div>
    		</div>
    		
    		<div class="content">
    			<form action="" method="post" enctype="multipart/form-data">
    				<table>
    					<tr>
    						<td rowspan="9" width="210px;"><img src="images/movie/mbig001.jpg" width="210" height="280"/></td>
    						<td>片名：</td> 
    						<td><input type="text" name="movieName" value="智取威虎山" size="30px" style="height: 28px;"/></td>
    					</tr>
    					<tr>
    						<td>标题：</td> 
    						<td><input type="text" name="movieTitle" value="“化老气题材为狂霸酷炫屌炸天”" size="30px" style="height: 28px;"/></td>
    					</tr>
    					<tr>
    						<td>类型：</td> 
    						<td>
    						<label><input name="type" type="checkbox" value="" />喜剧 </label> 
    						<label><input name="type" type="checkbox" value="" />科幻 </label> 
    						<label><input name="type" type="checkbox" value="" />爱情 </label> 
    						<label><input name="type" type="checkbox" value="" />动作 </label> 
    						<label><input name="type" type="checkbox" value="" />剧情 </label> 
    						<label><input name="type" type="checkbox" value="" />惊悚 </label> 
    						<br/> 
    						<label><input name="type" type="checkbox" value="" />恐怖 </label> 
    						<label><input name="type" type="checkbox" value="" />犯罪 </label>
    						<label><input name="type" type="checkbox" value="" />动画 </label> 
    						<label><input name="type" type="checkbox" value="" />战争 </label>
    						<label><input name="type" type="checkbox" value="" />灾难 </label>
    						<label><input name="type" type="checkbox" value="" />其他 </label>   
    						</td>
    					</tr>
    					<tr>
    						<td>语言：</td> 
    						<td>
    							<input type="radio" name="language" />国语
    							<input type="radio" name="language" />英语
    							<input type="radio" name="language" />粤语
    						</td>
    					</tr>
    					<tr>
    						<td>地区：</td> <td><input type="text" name="area" value="中国" style="height: 28px;"/></td>
    					</tr>
    					<tr>
    						<td>时长：</td> <td><input type="text" name="time" value="141分钟" style="height: 28px;"/></td>
    					</tr>
    					<tr>
    						<td>导演：</td> <td><input type="text" name="director" value="徐克" style="height: 28px;"/></td>
    					</tr>
    					<tr>
    						<td>主演：</td> <td><input type="text" name="actor" value="张涵予 梁家辉 林更新 佟丽娅 余男 陈晓 韩庚" size="45px" style="height: 28px;"/></td>
    					</tr>
    					<tr>
    						
    						<td>上映时间：</td> 
    						<td><input type="text" name="showTime" value="2014-12-23" style="height: 28px;"/></td>
    					</tr>
    					<tr>
    						<td>更换图片：</td>
    						<td colspan="2"><input type="file" name="file"/></td>
    					</tr>
    					<tr>
    						<td>剧情介绍：</td>
    						<td>&nbsp;</td> 
    						<td>&nbsp;</td>
    					</tr>
    					<tr>
    						<td colspan="3">
    						<textarea name="article.content1" cols="100" rows="12">
    						   1947年冬，东北民主联军203小分队在首长少剑波（林更新 饰）的带领下，奉上级命令进入匪患猖獗的林海雪原保护百姓
							安全，侦查员杨子荣（张涵予 饰）与卫生员白茹（佟丽娅 饰）火线驰援。为彻底瓦解土匪势力，杨子荣执意请求乔装潜入匪窝“
							威虎山”。历经了重重考验的杨子荣因献宝有功，被匪首座山雕封为“威虎山老九”。
							        杨子荣一面与八大金刚周旋，一面涉险为山下战友传出情报，而山寨中的一名神秘女子却屡屡将他陷于生死绝境。被203
							小分队生擒的土匪联络副官栾平趁乱逃脱， 竟出现在威虎寨中与杨子荣当面对质……座山雕（梁家辉 饰 ）寿辰“百鸡宴”上，
							杨子荣与203小分队的战友们迎来了剿匪收网的最佳时机，一场鏖战在所难免……
							</textarea>
    						</td>
    					</tr>
    					<tr align="center">
    						<td colspan="3">
    							<input type="submit" value="确认修改" style="background-color: #eb6120;color: white;height: 28px"/>
    							<input type="button" value="取消" onClick="location.href='business/business_movieDetails.jsp'" style="background-color: #eb6120;color: white;height: 28px"/>
    						</td>
    					</tr>
    				</table>
    			  				
    			</form>
    			
    		</div>
    	</div>
  </body>
</html>
