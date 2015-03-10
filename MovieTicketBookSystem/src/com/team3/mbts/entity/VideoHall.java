/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:VideoHall.java 放映厅表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 下午1:16:04
 * Modified By :徐晓聪 
 * Modified Time:2015-2-10 下午9:21:23
 * What is Modified:去掉isInUse属性
 * Version:
 */
public class VideoHall {
	/**
	 * 影厅编号
	 */
	private int id;
	/**
	 * 影院编号
	 */
	private int cinemaId;
	/**
	 * 影院
	 */
	private Cinema cinema;
	/**
	 * 座位数
	 */
	private int seatCount;
	/**
	 * 排数
	 */
	private byte row;
	/**
	 * 列数
	 */
	private byte col;
	/**
	 * 厅号
	 */
	private byte no;
	/**
	 * 该厅是否被删除（默认没有）
	 */
	private boolean isExist = true;
	
	
	public VideoHall() {
		super();
	}
	
	public VideoHall(int cinemaId, int seatCount, byte row, byte col, byte no) {
		super();
		this.cinemaId = cinemaId;
		this.seatCount = seatCount;
		this.row = row;
		this.col = col;
		this.no = no;
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

	public byte getNo() {
		return no;
	}

	public void setNo(byte no) {
		this.no = no;
	}

	public boolean isExist() {
		return isExist;
	}
	
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}	
	
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCinemaId() {
		return cinemaId;
	}
	
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}	
	
}
