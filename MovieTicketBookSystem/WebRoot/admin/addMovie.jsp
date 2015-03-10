<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加影片</title>
    
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
	
	<script charset="utf-8" src="js/business/jquery-1.11.1.min.js">	
	</script>
	
	<script type="text/javascript" src="js/admin/My97DatePicker/WdatePicker.js"></script>
	
	
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
	
	<script type="text/javascript" src="js/admin/validateMovieInput.js"></script>
	
	<style type="text/css">		
		/* 整体框架css  */
		.all{
			width: 100%;
			height: 1300px;
			/* border: 1px solid orange; */
		}
		/* 标题css  */
		.title{
			width: 100%;
			height: 100px;
			
			/* border: 1px solid pink; */
		}
		.title h2{
			width:90%;
			height: 55px;
			float: left;
			text-align:center;
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
		/*表格的每一行*/
		.content table tr{
			height: 50px;			
		}
		.content .submit{
			cursor: pointer;
			border:none;
			border-radius:3px;
		}
		.redBt {
			text-decoration:none;
			color:#fff;
			height:30px;
			padding: 6px 12px;
			border-radius:3px;
			background-color:#EB6120;
		}
		.redBt:hover {
			background-color:#FE7600;
		}
		
		/*提示文字样式*/
		.span{
			font-family: "微软雅黑";
			font-size: 12px;
			color: red;
		}
	</style>

  </head>
  
  <body>
    	<div class="all">
    		<div class="title">
    			<h2>添加影片</h2>
    		</div>
    		
    		<div class="content">
    			<form action="admin/AddMovieServlet" method="post" enctype="multipart/form-data">
    				<table>
    					<tr>
    						<td >片名：</td> <td><input type="text" name="movieName" size="40px" maxlength="50" style="height: 30px;"/></td>
    					</tr>
    					<tr>
    						<td >标题：</td> <td><input type="text" name="movieTitle" size="40px" maxlength="50" style="height: 30px;"/></td>
    					</tr>
    					<tr>
    						<td >类型：</td> 
    						<td>
    						<label><input name="type" type="checkbox" value="喜剧" />喜剧 </label> 
    						<label><input name="type" type="checkbox" value="科幻" />科幻 </label> 
    						<label><input name="type" type="checkbox" value="爱情" />爱情 </label> 
    						<label><input name="type" type="checkbox" value="动作" />动作 </label> 
    						<label><input name="type" type="checkbox" value="剧情" />剧情 </label> 
    						<label><input name="type" type="checkbox" value="奇幻 " />奇幻 </label> 
    						<br/> 
    						<label><input name="type" type="checkbox" value="恐怖 " />恐怖 </label> 
    						<label><input name="type" type="checkbox" value="犯罪" />犯罪 </label>
    						<label><input name="type" type="checkbox" value="动画" />动画 </label> 
    						<label><input name="type" type="checkbox" value="战争" />战争 </label>
    						<label><input name="type" type="checkbox" value="灾难" />灾难 </label>
    						<label><input name="type" type="checkbox" value="纪录" />纪录 </label>   
    						</td>
    					</tr>
    					
    					<tr>
    						<td >3D：</td> 
    						<td>
    							<input type="radio" name="is3D" checked="checked" value="2D" />2D
    							<input type="radio" name="is3D" value="3D"/>3D
    						</td>
    					</tr>
    					
    					<tr>
    						<td >语言：</td> 
    						<td>
    							<input type="radio" name="language" checked="checked" value="国语" />国语
    							<input type="radio" name="language" value="英语" />英语
    							<input type="radio" name="language"  value="粤语"/>粤语
    						</td>
    					</tr>
    					<tr>
    						<td >地区：</td>
    						<td>
    							<select name="area">
    								<option value="大陆">大陆</option>
    								<option value="日本">日本</option>
    								<option value="韩国">韩国</option>
    								<option value="法国">法国</option>
    								<option value="美国">美国</option>
    								<option value="港台">港台</option>
    								<option value="泰国">泰国</option>
    								<option value="英国">英国</option>
    								<option value="印度">印度</option>
    							</select>
    							
    							
    						</td>
    					</tr>
    					<tr>
    						<td >时长：</td> <td><input type="text" name="time" placeholder="请输入整数（分钟）" maxlength="3" style="height: 30px;"/></td>
    					</tr>
    					<tr>
    						<td >导演：</td> <td><input type="text" name="directors" size="45px" style="height: 30px;"/></td>
    					</tr>
    					<tr>
    						<td >主演：</td> <td><input type="text" name="actor" size="45px" style="height: 30px;" /></td>
    					</tr>
    					<tr>
    						<td >上映日期：</td> <td><input class="Wdate" type="text" name="publish" onClick="WdatePicker({lang:'zh-tw'})" style="height: 30px;" placeholder="点我选择日期"></td>
    					</tr>
    					<tr>
    						<td >上传图片：</td> <td><input type="file" name="file" /></td>
    					</tr>
    					<tr>
    						<td>剧情介绍：</td>
    						<td>&nbsp;</td> 
    						
    					</tr>
    					<tr>
    						<td colspan="2">
    						<textarea name="description" cols="78" rows="15" maxlength="1000"></textarea>
    						</td>
    					</tr>
    					<tr align="center">
    						<td colspan="2">
    							<input class="submit redBt" type="submit" value="确认添加" style="background-color: #eb6120;color: white;height: 28px;" />
    						</td>
    					</tr>
    				</table>
    			</form>
    			
    		</div>
    	</div>
  </body>
</html>
