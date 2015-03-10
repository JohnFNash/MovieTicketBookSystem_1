/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.Seat;
import com.team3.mbts.mappers.SeatMapper;
import com.team3.mbts.util.SqlSessionUtil;

public class SeatService {		
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-17 上午11:49:31
	 * Description:查询座位表服务类
	 * @param hallId
	 * @return Seat
	 */
	public List<Seat> selectSeat(int videoHallId)
	{
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SeatMapper seatMapper = sqlSession.getMapper(SeatMapper.class);
		List<Seat> seats = seatMapper.selectSeat(videoHallId);
		sqlSession.close();
		return seats;
	}
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-18 上午8:14:13
	 * Description:查找给定订单的座位表
	 * @param orderId
	 * @return List<Seat>
	 */
	public List<Seat> selectSeatForOrder(int orderId) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SeatMapper seatMapper = sqlSession.getMapper(SeatMapper.class);
		List<Seat> seats = seatMapper.selectSeatForOrder(orderId);
		sqlSession.close();
		return seats;
	}
	
}
