/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.CinemaRemark;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:CinemaDAO.java
 * Comments:影院操作信息类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-14 上午9:12:58
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface CinemaMapper {
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 上午9:16:50
	 * Description:	根据商家外键影院编号查询影院
	 * @param cinemaId
	 * @return Cinema
	 */
	@Select("select * from TB_CINEMA where CINEMA_ID =#{cinemaId}")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public Cinema selectCinemaById(int cinemaId);
	
	/**
	 * 查询指定日期在放映指定影片的影院中最受关注的几个
	 * @author 徐晓聪
	 * Create Time: 2015-1-24 上午23:08:49
	 * Description: 
	 * @param movieId
	 * @param day
	 * @return
	 */
	@Select("select top(#{numToGet}) c2.CINEMA_ID, c2.CINEMA_Name from TB_CINEMA as c2 "
			+ " where c2.CINEMA_ID in(select c.CINEMA_ID from TB_VIDEO_HALL as v, TB_SCREENINGS as s, TB_CINEMA as c "
			+ " where s.MOVIE_ID=#{movieId} and v.CINEMA_ID=c.CINEMA_ID and v.ID=s.VIDEO_HALL_ID " +
			" and DATEDIFF(DAY, s.START_TIME, #{day})=0 and s.START_TIME >= GETDATE()) order by c2.VISIT_COUNT desc")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public List<Cinema> selectCinemasForMovieOnShow(@Param("movieId")int movieId, @Param("day")String day, @Param("numToGet")int numToGet);
	
	/**
	 *  分页获取影院的基本信息
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 上午11:00:49
	 *  Description: 
	 *  @param rowBounds 
	 *  @return
	 */
	@Select("select CINEMA_ID, CINEMA_NAME from TB_CINEMA  order by CINEMA_ID")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.SimpliedCinemaResult")
	public List<Cinema> getSimplifiedCinemasByPage(RowBounds rowBounds);
	
	/**
	 *  分页获取影院的详细信息
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 上午11:00:49
	 *  Description: 
	 *  @param rowBounds 
	 *  @return
	 */
	@Select("select CINEMA_ID,CINEMA_NAME,AREA_ID,ADDRESS,LOGO,VISIT_COUNT from TB_CINEMA order by VISIT_COUNT desc")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public List<Cinema> getCinemasByPage(RowBounds rowBounds);
	
	/**
	 * 统计电影院的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 上午11:41:17
	 *  Description:
	 *  @return
	 */
	@Select("select count(*) from TB_CINEMA")
	public int countCinemas();
	
	/**
	 * 查询所有影院的方法
	 * Create Time:2015-1-21上午11:23:14
	 * returnType:影院列表
	 * @return
	 */
	@Select("select CINEMA_ID,CINEMA_NAME,SCENE from TB_CINEMA")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public List<Cinema> getAllCinemas1();
	
	/**
	 * 根据名字查询影院的方法
	 * Create Time:2015-1-21下午3:52:56
	 * returnType:Cinema
	 * @return
	 */
	@Select("select * from TB_CINEMA where CINEMA_NAME=#{cinemaName}")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public Cinema getCinemaByName(@Param("cinemaName")String cinemaName);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-22 下午3:10:12
	 * Description:新增影院资料
	 * @param cinema
	 * @return int
	 */
	@Insert("insert into TB_CINEMA (CINEMA_NAME,AREA_ID,ADDRESS,LOGO,SCENE,TEL,SPECIAL,DESCRIPTION) values(#{cinemaName},#{area.areaId},#{address},#{logo},#{scene},#{tel},#{special},#{description})")
	@Options(useGeneratedKeys=true,keyProperty="cinemaId")
	public int insertCinema(Cinema cinema);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-22 下午4:41:36
	 * Description:修改影院信息
	 * @param cinema
	 * @return int
	 */
	@Update("update TB_CINEMA set CINEMA_NAME=#{cinemaName},AREA_ID=#{area.areaId},ADDRESS=#{address},LOGO=#{logo},SCENE=#{scene},TEL=#{tel},SPECIAL=#{special},DESCRIPTION=#{description} where CINEMA_ID=#{cinemaId}")
	public int updateCinema(Cinema cinema);
	
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public Cinema getCinemaByName();

	
	@Select("select CINEMA_ID,CINEMA_NAME from TB_CINEMA")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public Cinema getAllCinemas(String text);

	/**
	 * 获取指定商家的影院中的第一家影院
	 * @param sellerId 商家编号
	 * @return
	 */
	@Select("select top(1) c.* from TB_CINEMA as c, TB_SELLER as s, TB_SELLER_CINEMA as sc where s.SELLER_ID=#{sellerId} and sc.SELLER_ID=s.SELLER_ID and c.CINEMA_ID=sc.CINEMA_ID order by c.CINEMA_ID")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public Cinema selectFirstCinemaForSeller(int sellerId);
	
	/**
	 * 获取指定商家的所有影院
	 * @param sellerId 商家编号
	 * @return
	 */
	@Select("select c.CINEMA_ID, c.CINEMA_NAME from TB_CINEMA as c, TB_SELLER as s, TB_SELLER_CINEMA as sc where s.SELLER_ID=#{sellerId} and sc.SELLER_ID=s.SELLER_ID and c.CINEMA_ID=sc.CINEMA_ID order by c.CINEMA_ID")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.SmallestCinemaResult")
	public List<Cinema> selectAllCinemaForSeller(int sellerId);
	
	/**
	 * 根据影院编号显示评论
	 * Create Time:2015-1-21下午3:52:56
	 * returnType:List<Cinema>
	 * @return
	 */
	@Select("select TOP(5) * from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and ID NOT IN(select TOP(#{prev}) ID from TB_CINEMA_REMARK order by TIME) order by TIME ")
	@ResultMap("com.team3.mbts.mappers.CinemaRemarkMapper.CinemaRemarkResult")
	@Options(useCache=false)
	public List<CinemaRemark> getcinemaRemark(@Param("cinemaId")int cinemaId, @Param("prev")int prev);
	/**
	*Create Time:2015-1-22 上午10:15:51
	*Description:删除评价
	*@param 根据account删除
	*@return 删除成功返回1，否者返回0
	*/ 
	@Delete("delete from TB_CINEMA_REMARK  where ID=#{id}")
	public int deleteCinemaRemark(int id);
	
	/**
	 * 获取指定数目的最受关注的电影院
	 * Create Time:2015-1-22 上午10:15:51
	 * @author 徐晓聪
	*  Description:删除评价
	 * @param cinemaNumToGet 要获得的电影院的数目
	 * @return
	 */
	@Select("select top(#{cinemaNumToGet}) CINEMA_ID, CINEMA_NAME from TB_CINEMA order by VISIT_COUNT desc")
	@ResultMap("com.team3.mbts.mappers.CinemaMapper.CinemaResult")
	public List<Cinema> getMostAttentionCinemas(int cinemaNumToGet);

}
