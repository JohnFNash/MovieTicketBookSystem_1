/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.team3.mbts.entity.Ticket;

/**
 * 订单相关操作的数据库访问类
 *  Project: moviebookticketsystem
 *  Packages: com.team3.mbts.mappers 
 *  FileName: TicketDAO.java
 *  Comments:
 *  JDK Version:
 *	@author 徐晓聪
 *  Create Date: 2015-1-26 下午1:02:19
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public interface TicketMapper {
	/**
	 * 生成ticket信息
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 下午1:03:21
	 * Description:
	 * @param order
	 * @return
	 */
  	@Insert("insert into TB_TICKET ( SCREENINGS_ID, USER_ID, ROW, COL) values(#{screeningsId}, #{userId}, #{row}, #{col})")
  	@Options(useGeneratedKeys=true, keyProperty="id")
	public int insertTicket(Ticket ticket);
	
	/**
	 * @author 徐晓聪
	 * Create Time : 2015-2-17 下109:15:13
	 * Description:批量插入票表
	 * @param ticketList 票列表
	 * @return 受影响的记录行数
	 */	
	public int insertTicketBatch(List<Ticket> ticketList);
	
	/**
	 * 加载给定场次的座位选择信息
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-27 上午9:26:25
	 *  Description:
	 *  @param screeningId 场次编号
	 *  @return
	 */
	@Select("select * from TB_TICKET where SCREENINGS_ID=#{screeningId}")
	@ResultMap("com.team3.mbts.mappers.TicketMapper.TicketResult")
	public List<Ticket> loadTicketsForScreening(int screeningId);
}
