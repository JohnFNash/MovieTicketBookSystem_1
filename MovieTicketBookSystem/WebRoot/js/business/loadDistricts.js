/**
 * 行政区域加载
 */
$(function(){		
	//省、直辖市级行政区域下拉框改变事件监听
	$("select[name='firstLevelArea']").change(function() {
		$.post('business/BusinessInformationServlet', {op: 'changeFirstLevel',
				 firstLevelArea:$("select[name='firstLevelArea']").val()}, 
			function(data) {
				 //清空之前的选项
				$("select[name='secondLevelArea'] option").remove();
				$("select[name='thirdLevelArea'] option").remove();
				
				var jsonArr = JSON.parse(data);
				//加载新的第二级选项
				var data = jsonArr.secondLevelAreaList;				
				var $secondAreaSel = $("select[name='secondLevelArea']");											
				for(var i in data) {
					var jsonObj = data[i];
					var str = "<option value='" + jsonObj.areaId + "'>"
						+ jsonObj.areaName + "</option>";				 						
	           		$secondAreaSel.append($(str));
	            }	
				//加载新的第三级选项
				var data_2 = jsonArr.thirdLevelAreaList;				
				var $thridAreaSel = $("select[name='thirdLevelArea']");											
				for(var i in data_2) {
					var jsonObj = data_2[i];
					var str = "<option value='" + jsonObj.areaId + "'>"
						+ jsonObj.areaName + "</option>";				 						
	           		$thridAreaSel.append($(str));
	            }
			});
	});
	
	//第二级行政区域下拉框改变事件监听
	$("select[name='secondLevelArea']").change(function() {
		$.post('business/BusinessInformationServlet', {op: 'changeSecondLevel',
				 secondLevelArea:$("select[name='secondLevelArea']").val()}, 
			function(data) {
				//清空之前的选项
				$("select[name='thirdLevelArea'] option").remove();
				//加载新的选项
				var $thridAreaSel = $("select[name='thirdLevelArea']");
				var jsonArr = JSON.parse(data);						 
				for(var i in jsonArr) {
					var jsonObj = jsonArr[i];
					var str = "<option value='" + jsonObj.areaId + "'>"
						+ jsonObj.areaName + "</option>";				 						
	           		$thridAreaSel.append($(str));
	            }
			});
	});
});