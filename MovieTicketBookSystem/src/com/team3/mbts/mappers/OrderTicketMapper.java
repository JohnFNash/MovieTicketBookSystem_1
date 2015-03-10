package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.team3.mbts.entity.OrderTicket;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:OrderTicketMapper.java
 * Comments:订单-座位关联信息操作DAO
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-17 下午9:52:34
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface OrderTicketMapper {
	/**
	 * 插入订单-座位记录
	 * @author 徐晓聪
	 * Create Time : 2015-2-17 下午9:56:15
	 * Description:
	 * @param orderTicket
	 * @return
	 */
	@Insert("insert into TB_ORDER_TICKET values(#{orderId}, #{ticketId})")
	public int insertOrderTicket(OrderTicket orderTicket);
	
	/**
	 * @author 徐晓聪
	 * Create Time : 2015-2-17 下109:15:13
	 * Description:批量插入订单-票表
	 * @param orderTicketList 订单-票列表
	 * @return 受影响的记录行数
	 */
	public int insertOrderTicketBatch(List<OrderTicket> orderTicketList);
}
