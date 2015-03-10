/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.Ticket;
import com.team3.mbts.mappers.TicketMapper;
import com.team3.mbts.mappers.TicketSqlProvider;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 *  Project: moviebookticketsystem
 *  Packages: com.team3.mbts.service 
 *  FileName: TicketService.java
 *  Comments:
 *  JDK Version:
 *	@author 徐晓聪
 *  Create Date: 2015-1-26 下午1:11:52
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public class TicketService {

	
	/**
	 * 生成ticket信息
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 下午1:03:21
	 * Description:
	 * @param order
	 * @return
	 */
	@InsertProvider(type=TicketSqlProvider.class, method="insertTicket")
	public int insertTicket(Ticket ticket) {
		//获取SqlSession对象
	    SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取TicketDAO对象
		TicketMapper ticketMapper = sqlSession.getMapper(TicketMapper.class);
		int insertFlag = ticketMapper.insertTicket(ticket);
		sqlSession.commit();
		sqlSession.close();
		return insertFlag;
	}
	
	/**
	 * 加载给定场次的座位选择信息
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-27 上午9:26:25
	 *  Description:
	 *  @param screeningId 场次编号
	 *  @return
	 */
	public List<Ticket> loadTicketsForScreening(int screeningId) {
		//获取SqlSession对象
	    SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取TicketDAO对象
		TicketMapper ticketMapper = sqlSession.getMapper(TicketMapper.class);
		List<Ticket> tickets = ticketMapper.loadTicketsForScreening(screeningId);
		sqlSession.close();
		return tickets;
	}
}
