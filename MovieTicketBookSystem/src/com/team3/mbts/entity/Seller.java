/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Seller.java 商家表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:33:37
 * Modified By : 徐晓聪
 * Modified Time:2015-2-10 下午9:14:41
 * What is Modified:去掉cinemaId属性
 * Version:
 */
public class Seller {
	/**
	 * 商家编号
	 */
	private int sellerId;
	/**
	 * 商家账号
	 */
	private String account;
	/**
	 * 商家密码
	 */
	private String password;	
	/**
	 * 商家是否被删除（默认没有）
	 */
	private boolean isExist;
	
	
	public boolean isExist() {
		return isExist;
	}
	
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	
	public Seller() {
		super();
	}
	
	public Seller(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	
	public int getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
