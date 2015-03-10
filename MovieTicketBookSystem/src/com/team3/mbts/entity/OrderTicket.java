/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:OrderTicket.java 订单-座位关联类
 * Comments:
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-17 下午9:54:51
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class OrderTicket {
	/**
	 * 订单编号
	 */
	private long orderId;
	/**
	 * 座位编号
	 */
	private long ticketId;
	
	//getters, setters	
	public long getTicketId() {
		return ticketId;
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	
}
