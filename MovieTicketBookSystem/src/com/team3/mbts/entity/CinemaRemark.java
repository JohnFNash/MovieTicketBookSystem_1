/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;

import java.sql.Timestamp;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:CinemaRemark.java 影院评价表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:51:35
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class CinemaRemark {
	/**
	 * 影院评价编号
	 */
	private int id;
	/**
	 * 影院
	 */
	private Cinema cinema;
	/**
	 * 用户
	 */
	private UserInfo user;
	/**
	 * 评价内容
	 */
	private String content;
	/**
	 * 影院评分（默认7.0）
	 */
	private float grade = 7.0f;
	/**
	 * 评价时间
	 */
	private Timestamp time;
	/**
	 * 被赞数
	 */
	private int likeCount = 0;
	/**
	 * 评论是否存在
	 */
	private boolean isExist = true;
	/**
	 * 评论的状态，1尚未审核，2审核通过，3审核未通过
	 */
	private byte status = (byte)1;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public UserInfo getUser() {
		return user;
	}
	
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public float getGrade() {
		return grade;
	}
	
	public void setGrade(float grade) {
		this.grade = grade;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	
}
