$(function() {
	/* 生成座位列表 */	
		var $divNode = $("#seatList");
		//$divNode.empty();//清除之前的子元素
		
		var rows = ${videoHall.row};
		var rows = ${videoHall.col};
		
		for(var i=0; i<rows; i++) {											
			var $dlNode =$("<dl></dl>");
			$divNode.append($dlNode);
			for(var j=0; j<cols; j++) {
				
				<c:forEach items="${seatList}" var="seat">
					if(i==seat.row-1&&j==seat.col-1)
					{
						var $ddNode = $("<dd><div class='on'></div></dd>");
						$dlNode.append($ddNode);
					}
	    		</c:forEach>
			}				
		}
	});

	

	