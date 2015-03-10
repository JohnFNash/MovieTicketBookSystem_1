package com.team3.mbts.entity;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Director.java 导演表
 * Comments:
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-10 下午8:53:35
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class Director {
	/**
	 * 导演编号
	 */
	private int directorId;
	
	/**
	 * 导演姓名
	 */
	private String directorName;		
	
	public Director() {
		super();
	}

	public Director(String directorName) {
		super();
		this.directorName = directorName;
	}

	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	
}
