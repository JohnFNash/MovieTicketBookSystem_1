/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:MovieActor.java 影片-演员表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:49:12
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieActor {
	/**
	 * 影片编号
	 */
	private int movieId;
	/**
	 * 演员编号
	 */
	private int actorId;
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	
}
