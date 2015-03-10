package com.team3.mbts.entity;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Area.java 行政区划表
 * Comments:
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-10 下午8:37:53
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class Area {
	/**
	 * 行政区编号
	 */
	private int areaId;
	
	/**
	 * 行政区名
	 */
	private String areaName;
	
	/**
	 * 父级行政区编号
	 */
	private int parentId;
	
	/**
	 * 行政区级别 1省/2市/3区县
	 */
	private byte areaLevel;
	
	/**
	 * 状态，1可用/0不可用
	 */
	private byte status = 1;

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public byte getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(byte areaLevel) {
		this.areaLevel = areaLevel;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
