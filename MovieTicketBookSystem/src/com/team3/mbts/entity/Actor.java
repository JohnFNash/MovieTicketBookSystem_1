/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Actor.java 演员表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:37:53
 * Modified By : ShiGuiXu
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class Actor {
	/**
	 * 演员/导演编号
	 */
	private int actorId;
	/**
	 * 演员/导演姓名
	 */
	private String actorName;
		
	public Actor() {
		super();
	}
	public Actor(String actorName) {
		super();
		this.actorName = actorName;
	}
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
}
