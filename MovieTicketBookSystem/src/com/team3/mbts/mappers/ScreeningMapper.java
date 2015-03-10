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
import org.apache.ibatis.annotations.Update;

import com.team3.mbts.entity.Screenings;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:ScreeningDAO.java
 * Comments:排期场次DAO操作类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-19 下午3:45:18
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface ScreeningMapper {
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午4:10:10
	 * Description:添加一个影片排期
	 * @param screenings
	 * @return int（1/0）
	 */
	@Insert("INSERT INTO TB_SCREENINGS (MOVIE_ID,VIDEO_HALL_ID,CINEMA_ID, START_TIME,ORIGINAL_PRICE,PRICE) values(#{movie.movieId}, #{videoHall.id}, #{cinema.cinemaId}, #{startTime}, #{originalPrice}, #{price})")
	public int insertScreening(Screenings screenings);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-20 上午9:41:34
	 * Description:查询排期列表
	 * @param cinemaId
	 * @param startTime
	 * @return List<Screenings>
	 */
	@Select("select * from TB_SCREENINGS where CINEMA_ID=#{cinemaId} and IS_EXIST=1 and DATEDIFF(day, START_TIME,#{startTime})=0 order by START_TIME")
	@ResultMap("com.team3.mbts.mappers.ScreeningMapper.ComplicateScreeningsResult")
	public List<Screenings> selectScreenings(@Param("cinemaId")int cinemaId,@Param("startTime")String startTime);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-21 上午9:32:10
	 * Description:添加排期输入的影厅编号和时间在已排期的列表中时间先于它的第一条场次数据
	 * @param cinemaId
	 * @param startTime
	 * @return List<Screenings>
	 */
	@Select("select * from TB_SCREENINGS where VIDEO_HALL_ID=#{videoHallId} and IS_EXIST=1 and START_TIME <= #{startTime} order by START_TIME desc")
	@ResultMap("com.team3.mbts.mappers.ScreeningMapper.ComplicateScreeningsResult")
	public List<Screenings> selectPre(@Param("videoHallId")int videoHallId,@Param("startTime")String startTime);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-21 上午9:32:10
	 * Description:添加排期输入的影厅编号和时间在已排期的列表中时间大于它的第一条场次数据
	 * @param cinemaId
	 * @param startTime
	 * @return List<Screenings>
	 */
	@Select("select * from TB_SCREENINGS where VIDEO_HALL_ID=#{videoHallId} and IS_EXIST=1 and START_TIME >= #{startTime} order by START_TIME")
	@ResultMap("com.team3.mbts.mappers.ScreeningMapper.ComplicateScreeningsResult")
	public List<Screenings> selectNext(@Param("videoHallId")int videoHallId,@Param("startTime")String startTime);
	
	/**
	 * 获取给定影片在给定影院，给定日期的排期
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午1:59:15
	 *  Description:
	 *  @param cinemaId 影院编号
	 *  @param movieId 影片编号
	 *  @param day 给定日期
	 *  @return
	 */
	@Select("select * from TB_SCREENINGS  where CINEMA_ID=#{cinemaId} and MOVIE_ID=#{movieId} and IS_EXIST=1 and DATEDIFF(day, START_TIME,#{day})=0 and START_TIME>=GETDATE() order by START_TIME")
	@ResultMap("com.team3.mbts.mappers.ScreeningMapper.ComplicateScreeningsResult")
	public List<Screenings> selectScreeningsForMovie(@Param("cinemaId")int cinemaId, @Param("movieId")int movieId, @Param("day")String day);

	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午1:10:07
	 * Description:删除排期场次
	 * @param id
	 * @return int
	 */
	@Update("update TB_SCREENINGS set IS_EXIST=0 where ID=#{id}")
	public int deleteScreening(@Param("id")int id);

	/**
	 * 根据场次编号查找场次信息
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-26 上午11:02:44
	 *  Description:
	 *  @param screeningId 场次编号
	 *  @return
	 */
	@Select("select screening.* from TB_SCREENINGS screening  where ID=#{screeningId}")
	@ResultMap("com.team3.mbts.mappers.ScreeningMapper.ComplicateScreeningsResult")
	public Screenings getScreeningById(int screeningId);
	
}
