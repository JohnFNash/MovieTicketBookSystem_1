function noValidate() {
	var noStr = $("input[name='no']").val();
	if(noStr == '') {
		alert("厅编号不能为空!");
		return false;
	}
	$.post('business/VideoHallServlet', {op: 'selectSeat', no:noStr}, function(data) {
		if(data == 'id exist') {
			alert("厅编号已经存在!");
			$("input[name='no']").trigger("focus");
			return false;
		} else {
			return true;
		}
	});		
}

$(function() {
	/* 生成座位列表 */
	$("#startSelect").click(function() {
		var noStr = document.getElementsByName("no")[0].value;
		if(isNaN(noStr)) {
			return;
		}
	
		var $divNode = $("#seatList");
		$divNode.empty();//清除之前的子元素
		
		var rows = 15, cols = 15;
		var rowNode = document.getElementsByName("row")[0];
		var rowStr = rowNode.value;
		var colNode = document.getElementsByName("col")[0];
		var colStr = colNode.value;
		if(!isNaN(rowStr)) {
			rows = parseInt(rowStr);
		} else {
			rowNode.value = rows;					
		}
		if(!isNaN(colStr)) {
			cols = parseInt(colStr);
		} else {
			colNode.value = cols;					
		}
		
		if(cols < 10 || cols > 20 || rows < 10 || rows > 20) {
			alert("放映厅行数、列数均需在10-20之间(含10,20)");
			return;
		}
		
		for(var i=0; i<rows; i++) {											
			var $dlNode =$("<dl></dl>");
			$divNode.append($dlNode);
			for(var j=0; j<cols; j++) {
				var $ddNode = $("<dd><div class='on'></div></dd>");			
				$ddNode.click(function() {
					var $child= $(this).find("div");							
					if($child.hasClass("on")) {//之前为被选为状态							
						$child.removeClass("on");
						$child.addClass("un");	
						return;							
					} else if($child.hasClass("un")) {//之前为未被选为状态
						$child.removeClass("un");
						$child.addClass("on");
					}
				});
				$dlNode.append($ddNode);			
			}				
		}
	});
	
	
	/* 绑定重置选座按钮点击事件 */
	$("#resetBtn").click(function() {
		var $ddNode = $("dd");
		if($ddNode.size() > 0) {
			$ddNode.each(function() {
				var $child= $(this).find("div");
				if($child.hasClass("un")) {//之前为为未被选状态							
					$child.removeClass("un");
					$child.addClass("on");						
				}
			});
		}
	});
	
	//ajax判断厅编号是否已经存在
	$("input[name='no']").blur(noValidate);
	
	$("input[type='submit']").click(function(){		
		
		var noseat = [];
		var $dlList = $(".seatList dl");		
		$(".seatList dd > div").each(function() {
			var $dlParent = $(this).parent().parent();
			if($(this).hasClass('un')) {
				var row = $dlList.index($dlParent) + 1;
				var col = $dlParent.find("dd").index($(this).parent()) + 1;
				noseat.push({'row':row, 'col':col});
			}
		});	
		$("input[name='seat']").val(JSON.stringify(noseat));
		//alert('ss:' + JSON.stringify(noseat));
	});
});	