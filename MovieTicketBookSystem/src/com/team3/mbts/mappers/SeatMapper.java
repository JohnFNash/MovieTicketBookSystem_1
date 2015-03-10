/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.team3.mbts.entity.Seat;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:SeatDAO.java
 * Comments:座位表信息操作DAO
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-15 下午6:33:01
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface SeatMapper {
	/**
	 * @author 徐晓聪
	 * Create Time : 2015-2-12 下午9:26:19
	 * Description:插入座位表
	 * @param seat 座位
	 * @return 受影响的记录行数
	 */
	@Insert("insert into TB_SEAT values ( #{videoHallId}, #{row}, #{col} )")
	public int insertSeat(Seat seat);
	
	/**
	 * @author 徐晓聪
	 * Create Time : 2015-2-12 下午9:26:19
	 * Description:插入座位表
	 * @param seatList 座位列表
	 * @return 受影响的记录行数
	 */
	public int insertSeatBatch(List<Seat> seatList);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-17 上午11:48:11
	 * Description:查找座位表
	 * @param hallId
	 * @return List<Seat>
	 */
	@Select("select * from TB_SEAT where VIDEO_HALL_ID=#{videoHallId}")
	@ResultMap("com.team3.mbts.mappers.SeatMapper.SeatResult")
	public List<Seat> selectSeat(@Param("videoHallId")int videoHallId);
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-18 上午8:13:13
	 * Description:查找给定订单的座位表
	 * @param orderId
	 * @return List<Seat>
	 */
	@Select("select s.* from TB_SEAT as s, TB_ORDER as o, TB_ORDER_TICKET as ot where o.ID = #{orderId} and ot.ORDER_ID=o.ID and ot.TICKET_ID=s.ID")
	public List<Seat> selectSeatForOrder(int orderId);	
	
}
