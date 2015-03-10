/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Seat.java 座位表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 下午1:17:43
 * Modified By :徐晓聪 
 * Modified Time:2015-2-10 下午9:12:36
 * What is Modified:去掉isInUse属性
 * Version:
 */
public class Seat {
	/**
	 * 座位表编号
	 */
	private int id;
	/**
	 * 影厅编号
	 */
	private int videoHallId;
	/**
	 * 排数
	 */
	private byte row;
	/**
	 * 列数（号）
	 */
	private byte col;
	
	public Seat() {
	}
	
	public Seat(int videoHallId, byte row, byte col) {
		this.videoHallId = videoHallId;
		this.row = row;
		this.col = col;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getVideoHallId() {
		return videoHallId;
	}
	
	public void setVideoHallId(int videoHallId) {
		this.videoHallId = videoHallId;
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
