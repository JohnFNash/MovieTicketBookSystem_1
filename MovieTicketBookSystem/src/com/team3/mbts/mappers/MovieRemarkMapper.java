/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.MovieRemark;

/**
 *  影片评论数据库操作类
 *  Project: moviebookticketsystem
 *  Packages: com.team3.mbts.mappers 
 *  FileName: MoiveRemarkDAO.java
 *  Comments:
 *  JDK Version:
 *	@author 徐晓聪
 *  Create Date: 2015-1-17 下午12:57:15
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public interface MovieRemarkMapper {
	/**
	 *  统计给定电影的评价的总数
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 下午12:58:16
	 *  Description:
	 *  @param moiveId 影片编号
	 *  @return
	 */
	@Select("select count(*) from TB_MOVIE_REMARK WHERE MOVIE_ID=#{movieId}")
	public int countMovieRemark(int movieId);
	
	/**
	 * 分页查询影片的评论
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 下午1:08:21
	 *  Description:
	 *  @param movieId 影片编号
	 *  @param rowBounds
	 *  @return
	 */
	@Select("select ID, TITLE, CONTENT from TB_MOVIE_REMARK where MOVIE_ID=#{movieId} and STATUS=2 order by TIME desc")
	@ResultMap("com.team3.mbts.mappers.MovieRemarkMapper.MovieRemarkResult")
	public List<MovieRemark> getMoiveRemarksByPage(@Param("movieId")int movieId, RowBounds rowBounds);
	
	/**
	 * 分页查询指定用户的评论
	 *  @author 徐晓聪
	 *  Create Time: 2015-2-17 下午12:57:24
	 *  Description:
	 *  @param userId 用户编号
	 *  @param rowBounds
	 *  @return
	 */
	@Select("select ID, MOVIE_ID, TITLE, SUBSTRING([CONTENT], 0, 50) as [CONTENT], TIME, STATUS from TB_MOVIE_REMARK where USER_ID=#{userId} order by TIME desc")
	@ResultMap("com.team3.mbts.mappers.MovieRemarkMapper.MovieRemarkResult")
	public List<MovieRemark> getMoiveRemarksForUserByPage(@Param("userId")int userId, RowBounds rowBounds);
	
	/**
	 * 统计指定用户的评论的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-2-17 下午1:03:34
	 *  Description:
	 *  @param userId 用户编号
	 *  @return
	 */
	@Select("select count(*) from TB_MOVIE_REMARK where USER_ID=#{userId}")
	public int selectMovieRemarksForUserCount(int userId);
	
	/**
	 * 返回最近的指定数目条电影评论
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 下午2:08:44
	 *  Description:
	 *  @param numberToGet 要获取的评论的数目
	 *  @return
	 */	
	@Select("select TOP(#{numberToGet}) r.ID, r.MOVIE_ID, r.USER_ID, r.TITLE, SUBSTRING([CONTENT], 0, 100) as [CONTENT]  from TB_MOVIE_REMARK as r, TB_MOVIE as m where r.MOVIE_ID=m.MOVIE_ID and DateDiff(day, m.PUBLISH, GETDATE()) <= 10 and r.STATUS=2 order by r.LIKE_COUNT desc")
	@ResultMap("com.team3.mbts.mappers.MovieRemarkMapper.MovieRemarkResult")
	public List<MovieRemark> getTopWonderfulMoiveRemarks(int numberToGet);
	
	/**
	 * 加载对指定影片的指定数目的评论
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 上午2:06:21
	 * Description:
	 * @param movieId 影片编号
	 * @param numToGet 要获取的评论的数目
	 * @param prevRecords 之前已经加载的评论数
	 * @return
	 */	
	@Select("select top(#{numToGet}) ID, USER_ID, TITLE, SUBSTRING([CONTENT], 0, 180) as [CONTENT], TIME, LIKE_COUNT  from TB_MOVIE_REMARK where MOVIE_ID=#{movieId} and ID not in (select top(#{prevRecords}) ID from TB_MOVIE_REMARK where MOVIE_ID=#{movieId} and STATUS=2 order by time desc) and STATUS=2 order by time desc")
	@ResultMap("com.team3.mbts.mappers.MovieRemarkMapper.MovieRemarkResult")
	public List<MovieRemark> selectSpecifiedNumMovieRemarks(@Param("movieId")int movieId, @Param("numToGet")int numToGet, @Param("prevRecords")int prevRecords);
	 
	/**
	 * 发表对影片的评论
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 上午2:37:14
	 * Description:
	 * @param movieRemark
	 * @return
	 */
	@Insert("insert into TB_MOVIE_REMARK (MOVIE_ID, USER_ID, TITLE, [CONTENT], GRADE, TIME)" +
			" values(#{movie.movieId}, #{user.userId}, #{title}, #{content}, #{grade}, #{time})")
	@Options(useGeneratedKeys=true, keyProperty="id")//自动加载主键，并设置给对象
	public int insertMovieRemark(MovieRemark movieRemark);
	
	/**
	 * 根据影片编号查找影评信息
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-26 下午4:38:43
	 *  Description:
	 *  @param movieRemarkId 影评编号
	 *  @return
	 */
	@Select("select *  from TB_MOVIE_REMARK where ID=#{movieRemarkId} and STATUS=2")
	@ResultMap("com.team3.mbts.mappers.MovieRemarkMapper.MovieRemarkResult")
	public MovieRemark getMovieRemarkById(int movieRemarkId);
}
