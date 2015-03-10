/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.mappers;

import org.apache.ibatis.jdbc.SQL;

import com.team3.mbts.entity.Ticket;

/**
 * 
 *  Project: moviebookticketsystem
 *  Packages: com.team3.mbts.dao 
 *  FileName: TicketSqlProvider.java
 *  Comments:
 *  JDK Version:
 *	@author 徐晓聪
 *  Create Date: 2015-1-26 下午1:05:24
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public class TicketSqlProvider {
	/**
	 * 生成ticket信息
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 下午01:05:13
	 * Description:
	 * @param order
	 * @return
	 */
	public String insertTicket(final Ticket ticket) {
		final String values = ticket.getScreeningsId() + "," + ticket.getUserId()
				+ "," +ticket.getRow() + "," + ticket.getCol();
		String sql = new SQL (){
			{
				INSERT_INTO("TB_TICKET");				
				VALUES("SCREENINGS_ID, USER_ID, ROW, COL", values);
			}
			
		}.toString();
		return sql;
	}
}
