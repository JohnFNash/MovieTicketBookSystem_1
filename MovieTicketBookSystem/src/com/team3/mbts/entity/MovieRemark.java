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
 * FileName:MovieRemark.java 影片评价表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:55:55
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieRemark {
	/**
	 * 影片评价编号
	 */
	private int id;
	/**
	 * 影评对应的影片
	 */
	private Movie movie;
	/**
	 * 发表该影评的用户
	 */
	private UserInfo user;
	/**
	 * 影评标题
	 */
	private String title = "影评";
	/**
	 * 影评内容
	 */
	private String content;
	/**
	 * 影评得分
	 */
	private float grade = 0.0f;
	/**
	 * 影评时间
	 */
	private Timestamp time;
	/**
	 * 影评被赞数
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
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}	
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
