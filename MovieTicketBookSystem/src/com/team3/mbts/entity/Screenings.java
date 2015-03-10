/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Screenings.java 放映场次表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 下午1:20:03
 * Modified By : 徐晓聪
 * Modified Time:2015-2-10 下午9:11:05
 * What is Modified:添加cinema属性
 * Version:
 */
public class Screenings implements Serializable {
	private static final long serialVersionUID = 1459357968742209056L;
	
	/**
	 * 场次编号
	 */
	private long id;
	/**
	 * 影片
	 */
	private Movie movie;
	/**
	 * 影厅
	 */
	private VideoHall videoHall;
	/**
	 * 影院
	 */
	private Cinema cinema;	
	/**
	 * 开始时间
	 */
	private Timestamp startTime;
	/**
	 * 原票价
	 */
	private float originalPrice;
	/**
	 * 现票价
	 */
	private float price;	
	/**
	 * 场次是否存在，未被删除，默认为true 
	 */
	private boolean isExist = true;
	
	public Screenings() {
		super();
	}
		
	public Screenings(Movie movie, VideoHall videoHall, Timestamp startTime,
			float originalPrice, float price) {
		super();
		this.movie = movie;
		this.videoHall = videoHall;
		this.startTime = startTime;
		this.originalPrice = originalPrice;
		this.price = price;
	}	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public VideoHall getVideoHall() {
		return videoHall;
	}

	public void setVideoHall(VideoHall videoHall) {
		this.videoHall = videoHall;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
		
}
