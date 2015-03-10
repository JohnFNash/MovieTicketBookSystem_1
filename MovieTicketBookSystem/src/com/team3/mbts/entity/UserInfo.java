/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:UserInfo.java 用户表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:23:35
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class UserInfo {
		/**
		 * 用户编号
		 */
		private int userId;
		/**
		 * 用户账号
		 */
		private String account;
		/**
		 * 用户密码
		 */
		private String password;
		/**
		 * 用户头像
		 */
		private	String headPath = "images/head/default_head.jpg";
		/**
		 * 是否是管理员（默认不是）
		 */
		private boolean isAdmin = false;
		/**
		 * 该用户是否被删除（默认没有）
		 */
		private boolean isExist=true;
		
		public boolean isExist() {
			return isExist;
		}
		
		public void setExist(boolean isExist) {
			this.isExist = isExist;
		}
		
		public int getUserId() {
			return userId;
		}
		
		public void setUserId(int userId) {
			this.userId = userId;
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
		
		public String getHeadPath() {
			return headPath;
		}
		
		public void setHeadPath(String headPath) {
			this.headPath = headPath;
		}
		
		public boolean isAdmin() {
			return isAdmin;
		}
		
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}			
		
}
