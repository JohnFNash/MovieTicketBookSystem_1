<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="form" required="true" type="java.lang.String" description="要提交的分页表单的名字" %>
<%@ attribute name="pageBean" required="true" type="com.team3.mbts.util.PageBean" description="分页实体对象" %>
<%@ attribute name="optionValues" required="false" type="java.util.List" description="分页下拉框的选项值集合" %>

<script type="text/javascript" src="js/business/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
	function jumpPage(index) {
		var curPage = parseInt('${pageBean.curPage}');	//获取当前页数
		var totalPage = parseInt('${pageBean.totalPage}');	//获取总页数
		var page;	//当前页
		if(index == 0) {	
			if(curPage == 1) {//已经是第1页，不用再发出请求
				return;
			}		
			page = 1;			
		} else if(index == 1) {//上一页
			if(curPage == 1) {//已经是第1页，没有上一页，不用再发出请求
				return;
			}
			page = curPage - 1;
		} else if(index == 2){	//下一页
			if(curPage == totalPage) {//已经是最后页，没有下一页，不用再发出请求
				return;
			}
			page = curPage + 1;
		} else if(index == 3) {	//尾页
			if(curPage == totalPage) {//已经是最后页，没有下一页，不用再发出请求
				return;
			}
			page = totalPage;
		}
		var $curPageNode = $("input[name='curPage']");
		$curPageNode.val(page);
		document["${form}"].submit();
	}

	$(function() {
		//初始选中每页大小下拉框
		var options = $("#selRows option");
		for(var i=0; i<options.length; i++) {
			if(options[i].value == '${pageBean.pageSize}') {					
				options[i].selected = true;
				break;
			}
		}						
		
		//为跳转至指定页面按钮添加事件
		$("#goPageBtn").click(function(){
			var pageStr = $("#goPage").val();	//获取输入的页数
			if(isNaN(pageStr) || parseInt(pageStr) <= 0) {
				$("#goPage").val('${pageBean.curPage}');
				return;
			}
			var $curPageNode = $("input[name='curPage']");
			$curPageNode.val(parseInt(pageStr));
			document["${form}"].submit();
		});				
		
		//为每页记录数下拉框添加事件监听
		$("#selRows").change(function() {
			document["${form}"].submit();
		});
	});
</script>

<input type="hidden" name="curPage" value="${pageBean.curPage}"/>
<ul style="display: inline; list-style: none; font-size: 13px; height: 25px; line-height: 25px;">  		
	<li onclick="jumpPage(0);" style="display: inline; margin-right: 10px; padding: 2px 4px; border: 1px solid #A9CBEE; cursor: pointer;">首页</li>
	<li  onclick="jumpPage(1);" style="display: inline; margin-right: 10px; padding: 2px 4px; border: 1px solid #A9CBEE; cursor: pointer;">上一页</li>
	<li onclick="jumpPage(2);" style="display: inline;  margin-right: 10px; padding: 2px 4px; border: 1px solid #A9CBEE; cursor: pointer;">下一页</li>
	<li onclick="jumpPage(3);" style="display: inline; margin-right: 10px; padding: 2px 4px; border: 1px solid #A9CBEE; cursor: pointer;">尾页</li>	  				  		
</ul>
<input style="text-align:center; font-size: 12px;" type="text" size="8" value="${pageBean.curPage }" id="goPage"/>
<input type="button" id="goPageBtn" value="GO" /> 
<span style="font-size: 13px; margin-left: 10px;">每页条数：</span>
<select id="selRows" name="pageSize">
	<c:choose>
		<c:when test="${optionValues != null}">
			<c:forEach items="${optionValues}" var="opValue">
				<c:out value="${opValue}"></c:out>
				<option value="${opValue}">${opValue}</option>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<option value="10">10</option><option value="20">20</option><option value="30">30</option>
			<option value="40">40</option><option value="50">50</option>
		</c:otherwise>
	</c:choose>
</select>
<span style="font-size: 13px; margin-left: 10px;">总数：${pageBean.totalRecords}</span><span style="font-size: 12px;"> 当前&nbsp;${pageBean.curPage}/${pageBean.totalPage}页</span>