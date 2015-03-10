/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Ticket.java 电影票表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 下午1:24:34
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class Ticket {
	/**
	 * 票编号
	 */
	private long id;
	/**
	 * 场次编号
	 */
	private long screeningsId;
	/**
	 * 用户编号
	 */
	private int userId;
	/**
	 * 排数
	 */
	private byte row;
	/**
	 * 列数（号）
	 */
	private byte col;	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getScreeningsId() {
		return screeningsId;
	}
	
	public void setScreeningsId(long screeningsId) {
		this.screeningsId = screeningsId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public byte getRow() {
		return row;
	}
	
	public void setRow(byte row) {
		this.row = row;
	}
	
	public byte getCol() {
		return col;
	}
	
	public void setCol(byte col) {
		this.col = col;
	}	
	
}
