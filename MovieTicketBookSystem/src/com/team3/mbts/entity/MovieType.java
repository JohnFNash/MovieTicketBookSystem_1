/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:MovieType.java 影片类型表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:35:54
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieType {
	/**
	 * 影片编号
	 */
	private int movieId;
	/**
	 * 影片类型名称
	 */
	private String typeName;
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
