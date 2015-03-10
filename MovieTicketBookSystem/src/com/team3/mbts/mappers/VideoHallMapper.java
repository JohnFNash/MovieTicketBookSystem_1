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
import org.apache.ibatis.annotations.Update;
 
import com.team3.mbts.entity.VideoHall;
/**
  * 
  * Project:moviebookticketsystem
  * Package:com.team3.mbts.mappers
  * FileName:ScreeningDAO.java
  * Comments:影厅信息操作DAO
  * JDK Version:
  * Author : LongJun
  * Create Date:2015-1-14 下午4:28:52
  * Modified By : 
  * Modified Time:
  * What is Modified:
  * Version:
  */
public interface VideoHallMapper {
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 下午4:37:01
	 * Description:	查询影院影厅列表
	 * @param cinemaId
	 * @return  List<VideoHall>
	 */
	@Select("select * from TB_VIDEO_HALL where CINEMA_ID=#{cinemaId} and IS_EXIST=1")
	@ResultMap("com.team3.mbts.mappers.VideoHallMapper.VideoHallResult")
	public List<VideoHall> selectVideoHalls(@Param("cinemaId")int cinemaId);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 下午6:11:47
	 * Description:删除影院影厅
	 * @param hallId
	 * @return int （1/0）
	 */
	
	@Update("update TB_VIDEO_HALL set IS_EXIST=0 where ID=#{id}")
	public int deleteVideoHall(@Param("id")int id);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-15 上午10:09:05
	 * Description:查询影厅编号是否存在
	 * @param id
	 * @return int
	 */
	@Select("select count(*) from TB_VIDEO_HALL where CINEMA_ID=#{cinemaId} and NO=#{no}")
	public int selectIDExist(@Param("cinemaId")int cinemaId,@Param("no")int no);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-17 上午9:15:04
	 * Description:添加放映厅
	 * @param videoHall
	 * @return int
	 */
	@Insert("insert into TB_VIDEO_HALL (CINEMA_ID,NO,SEAT_COUNT,ROW,COL) values (#{cinemaId},#{no},#{seatCount},#{row},#{col})")
	@Options(useGeneratedKeys=true,keyProperty="id")
	public int insertHall(VideoHall videoHall);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-17 上午9:44:19
	 * Description:查询影厅编号
	 * @param cinemaId
	 * @param no
	 * @return id
	 */
	@Select("select ID from TB_VIDEO_HALL where CINEMA_ID=#{cinemaId} and NO=#{no}")
	public int selectId(@Param("cinemaId")int cinemaId,@Param("no")int no);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-17 下午2:23:22
	 * Description: 根据影厅编号查询影厅
	 * @param id
	 * @return VideoHall
	 */
	@Select("select * from TB_VIDEO_HALL where ID=#{id}")
	@ResultMap("com.team3.mbts.mappers.VideoHallMapper.VideoHallResult")
	public VideoHall selectById(@Param("id")int id);
}
