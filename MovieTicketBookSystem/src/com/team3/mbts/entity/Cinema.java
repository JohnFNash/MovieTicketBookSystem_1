/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;

/**
 * 
 * Project:moviebookticketsystem 
 * Package:com.team3.mbts.entity
 * FileName:Cinema.java 影院表 
 * Comments: 
 * JDK Version: 
 * Author : LongJun 
 * Create Date:2015-1-12 上午10:27:08 
 * Modified By :徐晓聪 
 * Modified Time:2015-2-10 下午8:47:13
 * What is Modified: 
 * Version:
 */
public class Cinema {
	/**
	 * 影院编号
	 */
	private int cinemaId;
	/**
	 * 影院名称
	 */
	private String cinemaName;

	/**
	 * 影院所在行政区
	 */
	private Area area;

	/**
	 * 地址
	 */
	private String address;
	/**
	 * 影院LOGO
	 */
	private String logo;
	/**
	 * 影院实景图
	 */
	private String scene = "images/cinema/default_logo.png";
	/**
	 * 影院电话
	 */
	private String tel;
	/**
	 * 影院特色
	 */
	private String special;
	/**
	 * 影院描述
	 */
	private String description;
	/**
	 * 影院关注数
	 */
	private int visitCount = 0;
	/**
	 * 影院是否营业（默认营业）
	 */
	private boolean isOpen = true;
	/**
	 * 影院得分
	 */
	private Float score = 7.0f;

	public Cinema() {
		super();
	}

	public Cinema(String cinemaName, Area area, String address, String logo,
			String scene, String tel, String special, String description) {
		super();
		this.cinemaName = cinemaName;
		this.area = area;
		this.address = address;
		this.logo = logo;
		this.scene = scene;
		this.tel = tel;
		this.special = special;
		this.description = description;
	}

	public Cinema(int cinemaId, String cinemaName, Area area, String address,
			String logo, String scene, String tel, String special,
			String description) {
		super();
		this.cinemaId = cinemaId;
		this.cinemaName = cinemaName;
		this.area = area;
		this.address = address;
		this.logo = logo;
		this.scene = scene;
		this.tel = tel;
		this.special = special;
		this.description = description;
	}		

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

}
