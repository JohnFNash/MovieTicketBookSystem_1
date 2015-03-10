package com.team3.mbts.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {	
	private static final long serialVersionUID = 3655727812137380455L;
	
	/**
	 * 订单编号
	 */
	private long id;
	/**
	 * 用户
	 */
	private UserInfo user;
	/**
	 * 影院编号
	 */
	private int cinemaId;
	/**
	 * 场次
	 */
	private Screenings screenings;
	/**
	 * 下单时间
	 */
	private Timestamp time;
	/**
	 * 订单状态（false表示待付款，true表示完成）
	 */
	private boolean status = false;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}
	
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	public int getCinemaId() {
		return cinemaId;
	}
	
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	public Screenings getScreenings() {
		return screenings;
	}
	
	public void setScreenings(Screenings screenings) {
		this.screenings = screenings;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}	
	
}
