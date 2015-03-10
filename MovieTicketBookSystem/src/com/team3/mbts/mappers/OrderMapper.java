/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.Order;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:OrderDAO.java
 * Comments:订单操作DAO
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-23 下午4:10:39
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface OrderMapper {
	
	/**
	 * 
	 * @author LongJun 
	 * Create Time : 2015-1-23 上午10:05:37
	 * Description:根据影院id 查询影院订单列表 并分页
	 * @param rowBounds
	 * @param cinemaId
	 * @param time1
	 * @param time2
	 * @return List<Order>
	 */
	@Select("select * from TB_ORDER where CINEMA_ID=#{cinemaId} and TIME >=#{time1} and TIME <=#{time2} order by TIME desc")
	@ResultMap("com.team3.mbts.mappers.OrderMapper.OrderResult")
	public List<Order> selectOrderList(RowBounds rowBounds, @Param("cinemaId")int cinemaId,@Param("time1")String time1,@Param("time2")String time2);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 上午11:43:31
	 * Description:查询订单分页的总记录数
	 * @param cinemaId
	 * @param time1
	 * @param time2
	 * @return int 
	 */
	@Select("select count(*) from TB_ORDER where CINEMA_ID=#{cinemaId} and TIME >=#{time1} and TIME <=#{time2}")
	public int selectCount(@Param("cinemaId")int cinemaId,@Param("time1")String time1,@Param("time2")String time2);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 下午5:32:51
	 * Description:按订单号号查询订单
	 * @param orderId
	 * @return order
	 */
	@Select("select * from TB_ORDER where ID=#{orderId}")
	@ResultMap("com.team3.mbts.mappers.OrderMapper.OrderResult")
	public Order selectOrderById(@Param("orderId")int orderId);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 下午6:06:36
	 * Description:按影片名模糊查询订单
	 * @param key
	 * @return List<Order>
	 */
	@Select("select ord.* from TB_ORDER ord, TB_SCREENINGS sc,TB_MOVIE mo where " +
			"ord.SCREENINGS=sc.ID  and ord.CINEMA_ID=#{cinemaId} and sc.MOVIE_ID=mo.MOVIE_ID " +
			"and mo.MOVIE_ID =(select MOVIE_ID from TB_MOVIE where NAME like '%${key}%') " +
			"and ord.TIME>=#{time1} and ord.TIME<=#{time2} " +
			"order by TIME desc")
	@ResultMap("com.team3.mbts.mappers.OrderMapper.OrderResult")
	public List<Order> selectOrListByMKey(@Param("key")String key,@Param("cinemaId")int cinemaId,@Param("time1")String time1,@Param("time2")String time2);
	
	/**
	 * 生成订单
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 下午12:02:13
	 * Description:
	 * @param order
	 * @return
	 */
	@Insert("insert into TB_ORDER (USER_ID, CINEMA_ID, SCREENINGS, TIME) values(#{user.userId}, #{cinemaId}, #{screenings.id}, #{time})")
	@Options(useGeneratedKeys=true, keyProperty="id")//自动加载主键，并设置给对象
	public int insertOrder(Order order);
	
	/**
	 * 获取给定用户的所有订单
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-27 下午6:26:34
	 *  Description:
	 *  @param userId 用户编号
	 *  @param rowBounds
	 *  @return
	 */
	@Select("select * from TB_ORDER where USER_ID=#{userId} order by time desc")
	@ResultMap("com.team3.mbts.mappers.OrderMapper.OrderResult")
	public List<Order> selectOrdersForUser(@Param("userId")int userId, RowBounds rowBounds);
	
	/**
	 * 统计用户的订单的数目
	 * @param userId 用户编号
	 * @return
	 */
	@Select("select count(*) from TB_ORDER where USER_ID=#{userId}")
	public int countOrdersForUser(int userId);
}
