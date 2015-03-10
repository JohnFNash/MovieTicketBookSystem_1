/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:MovieDirector.java 影片-导演表
 * Comments:
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-10 下午9:03:12
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieDirector {
	/**
	 * 影片编号
	 */
	private int movieId;
	/**
	 * 导演编号
	 */
	private int directorId;
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}	
	
}
