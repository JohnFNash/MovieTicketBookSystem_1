/*
*www.dyr.com
*Copyright (c) 2014 All Rights Revered
*/

/**
 * 电影刀类
 */
package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.Movie;

/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.mappers
 *FileName:MovieMapper.java
 *Comments(意见):
 *JDK Version
 *Author:谢洪章
 *Create Date:2015-1-13上午11:18:29
 * @author Administrator
 *
 */
public interface MovieMapper {
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 上午11:24:10
	 * Description:添加影片
	 * @param movie
	 * @return （返回）1 表示成功，0表示失败 
	 */
	@InsertProvider(type=MovieDAOSqlProvider.class,method="insertMovie")
	@Options(useGeneratedKeys=true, keyProperty="movieId")
	public int insertMovie(Movie movie);
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-3-1 下午12:54:11
	 * Description:更新影片
	 * @param movie
	 * @return （返回）1 表示成功，0表示失败 
	 */
	@InsertProvider(type=MovieDAOSqlProvider.class,method="updateMovie")
	public int updateMovie(Movie movie);
	
	/**
	 * 分页查询热映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-14 上午10:29:47
	 *  Description:
	 *  @param curPage 当前页
	 *  @param pageSize 页大小
	 *  @return 查询到的影片列表
	 */	
	@Select("select m.MOVIE_ID, NAME, POST, TITLE, LANGUAGE, DURATION, ATTENTION_COUNT, BUY_COUNT from TB_MOVIE as m "
			+ " where exists (select s.MOVIE_ID from TB_SCREENINGS s " 
			+ "	where m.MOVIE_ID=s.MOVIE_ID and s.START_TIME >= GETDATE()) and m.PUBLISH <= GETDATE() order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> getHotestMoviesByPage(RowBounds rowBounds);
	
	/**
	 * 根据影片类型和搜索关键字等查询相应的热映影片数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-2-19 下午10:12:28
	 *  Description:
	 *  @param rowBounds
	 *  @param typeName 影片类型名
	 *  @param key 搜索关键字
	 *  @param order 排序规则
	 *  @return
	 */
	public int selectHotMoviesCountByDynamicParameter(@Param("typeName")String typeName, @Param("key")String key, @Param("order")String order);
	
	/**
	 * 根据影片类型和搜索关键字等查询热映影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 上午10:40:28
	 *  Description:
	 *  @param rowBounds
	 *  @param typeName 影片类型名
	 *  @param key 搜索关键字
	 *  @param order 排序规则
	 *  @return
	 */	
	public List<Movie> getHotMoviesByDynamicParameter(RowBounds rowBounds, @Param("typeName")String typeName, @Param("key")String key, @Param("order")String order);
	
	/**
	 * 查询正在热映的影片的总数
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-14 上午11:40:59
	 *  Description:
	 *  @return 影片数目
	 */
	public int countHotestMovies();
	
	/**
	 * 统计即将上映的影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 下午3:38:44
	 *  Description:
	 *  @return
	 */
	@Select("select count(*) from TB_MOVIE m where m.PUBLISH >= GETDATE()")
	public int countFutureMovies();
	
	/**
	 * 统计所有影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-22 上午0:21:44
	 *  Description:
	 *  @return
	 */
	@Select("select count(*) from TB_MOVIE")
	public int countMovies();
	
	/**
	 * 根据电影编号获取影片的信息
	 * @param movieId 影片编号
	 * @return
	 */
	@Select("select * from TB_MOVIE WHERE MOVIE_ID=#{movieId}")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public Movie getMovieById(int movieId);
	
	/**
	 * 分页查询影片
	 * @author 徐晓聪
	 * Create Time : 2015-1-22 上午0:22:49
	 * Description:
	 * @param rowBounds 
	 * @return List<Movie>
	 */
	@Select("select MOVIE_ID, NAME,  POST, TITLE, LANGUAGE, DURATION, PUBLISH, ATTENTION_COUNT, BUY_COUNT from TB_MOVIE "
			+ " order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> selectAllMoviesByPage(RowBounds rowBounds);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午9:32:26
	 * Description:根据上映日期降序并筛选掉日期过去一个月的影片 加分页
	 * @param rowBounds
	 * @param date
	 * @return List<Movie>
	 */	
	@Select("select * from TB_MOVIE where PUBLISH >= #{date} order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> selectMovies(RowBounds rowBounds, @Param("date")String date);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午9:32:26
	 * Description:根据上映日期降序并筛选掉日期过去一个月的影片 加分页
	 * @param date
	 * @return int
	 */
	@Select("select count(*) from TB_MOVIE where PUBLISH >= #{date}")
	public int getMovieCount(String date);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-27 下午1:55:41
	 * Description:查询所有影片的数量
	 * @return
	 */
	@Select("select count(*) from TB_MOVIE ")
	public int selectAllMovieCount();
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-27 下午1:55:41
	 * Description:查询所有影片 并分页
	 * @return
	 */
	@Select("select * from TB_MOVIE order by PUBLISH desc  ")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> selectAllMovie(RowBounds rowBounds);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午11:31:42
	 * Description:得到排期影片选择列表
	 * @param date
	 * @return List<Movie>
	 */
	@Select("select * from TB_MOVIE where PUBLISH >= #{date} and PUBLISH <= #{date2} order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> selectAddListing(@Param("date")String date,@Param("date2")String date2);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午11:31:42
	 * Description:得到排期影片选择列表
	 * @param date
	 * @return List<Movie>
	 */
	@Select("select * from TB_MOVIE where PUBLISH >= #{date} order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> selectMovieList(String date);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午6:59:01
	 * Description:按关键字查询影片
	 * @param key
	 * @return List<Movie>
	 */
	@Select("select * from TB_MOVIE where NAME like '%${key}%' and PUBLISH>=#{time} ")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> selectMovieByKey(@Param("key")String key,@Param("time")String time);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午6:59:01
	 * Description:按关键字查询影片
	 * @param key
	 * @return List<Movie>
	 */
	@Select("select * from TB_MOVIE where NAME like '%${key}%' ")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> getMovieByKey(@Param("key")String key);
	
	
	/**
	 * 分页查询即将上映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 下午2:32:34
	 *  Description:
	 *  @param rowBounds
	 *  @return
	 */
	@Select("select MOVIE_ID, NAME,  POST, TITLE, LANGUAGE, DURATION, PUBLISH, ATTENTION_COUNT, LIKE_COUNT from TB_MOVIE "
		+ " where PUBLISH >= GETDATE() order by PUBLISH")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> getFutureMoviesByPage(RowBounds rowBounds);
	
	/**
	 * 查询最受关注的即将上映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-25 下午1:53:34
	 *  Description:
	 *  @param numToGet 要获取的影片的数目
	 *  @return
	 */
	@Select("select TOP(#{numToGet}) MOVIE_ID, NAME, ATTENTION_COUNT from TB_MOVIE "
		+ " where PUBLISH >= GETDATE() order by ATTENTION_COUNT")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> getMostAttentionFutureMovies(int numToGet);
		
	/**
	 * 根据影片类型和搜索关键字等统计筛选出得即将上映的影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 下午5:21:49
	 *  Description:
	 *  @param typeName 类型名称
	 *  @param key 搜索关键字
	 *  @param order 排序规则
	 *  @return
	 */	
	public int countFutureMoviesByDynamicParameter(@Param("typeName")String typeName, @Param("key")String key, @Param("order")String order);
		
	/**
	 * 根据影片类型和搜索关键字等查询即将上映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 下午5:21:49
	 *  Description:
	 *  @param typeName 类型名称
	 *  @param key 搜索关键字
	 *  @param order 排序规则
	 *  @param rowBounds
	 *  @return
	 */
	public List<Movie> getFutureMoviesByDynamicParameter(@Param("typeName")String typeName, @Param("key")String key, 
			@Param("order")String order, RowBounds rowBounds);
	
	/**
	 * 筛选电影库中的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-22 上午11:00:18
	 *  Description:
	 *  @param area 区域
	 *  @param typeName 类型
	 *  @param startTime 起始时间
	 *  @param endTime 中止时间
	 *  @param key 搜索关键字
	 *  @param rowBounds
	 *  @return
	 */
	public List<Movie> filterMoviesInMovieStore(@Param("area")String area, @Param("typeName")String typeName, 
			@Param("startTime")String startTime, @Param("endTime")String endTime, 
			@Param("key")String key, RowBounds rowBounds);
	
	/**
	 * 统计筛选之后所剩影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-22 下午6:28:01
	 *  Description:
	 *  @param area 区域
	 *  @param typeName 类型名称
	 *  @param startTime 时间上限
	 *  @param endTime 时间下限
	 *  @param key 搜索关键字
	 *  @return
	 */
	public int countFilterMoviesInMovieStore(@Param("area")String area, @Param("typeName")String typeName, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param("key")String key);
	
	
	/**
	 * 分页查找给定影院当天在上映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午12:55:44
	 *  Description:
	 *  @param cinemaId
	 *  @param day
	 *  @param curPage
	 *  @param pageSize
	 *  @return
	 */
	@Select("select m.MOVIE_ID, NAME, POST from TB_MOVIE as m   where EXISTS (select s.MOVIE_ID from TB_SCREENINGS as s, TB_VIDEO_HALL as v "
			+ " where m.MOVIE_ID=s.MOVIE_ID and s.START_TIME >= GETDATE() "
			+ " and DATEDIFF(day, s.START_TIME, #{day})=0 and s.VIDEO_HALL_ID=v.ID and v.CINEMA_ID=#{cinemaId}) order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> getCinemaHotestMoviesByPage(@Param("cinemaId")int cinemaId, @Param("day")String day);
	
	/**
	 * 统计给定影院当天在上映的影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午12:55:44
	 *  Description:
	 *  @param cinemaId
	 *  @param day
	 *  @return
	 */
	@Select("select count(m.MOVIE_ID) from TB_MOVIE as m, TB_SCREENINGS as s, TB_VIDEO_HALL as v " 
			+ " where m.MOVIE_ID=s.MOVIE_ID and s.START_TIME >= GETDATE() and DATEDIFF(day, s.START_TIME, #{day})=0 and s.VIDEO_HALL_ID=v.ID and v.CINEMA_ID=#{cinemaId}")
	public int countCinemaHotestMovies(@Param("cinemaId")int cinemaId, @Param("day")String day);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午3:16:54
	 * Description:按影片类型查询商家影片列表 加入分页
	 * @param rowBounds
	 * @param typeName
	 * @param time
	 * @return
	 */
	@Select("select mo.* from TB_MOVIE mo,TB_MOVIE_TYPE type " +
			"where mo.MOVIE_ID=type.MOVIE_ID and type.TYPE_NAME=#{typeName} and mo.PUBLISH>=#{time} " +
			"order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> selectMovieByType(RowBounds rowBounds, @Param("typeName")String typeName,@Param("time")String time);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午3:16:54
	 * Description:按影片类型查询影库影片列表 加入分页
	 * @param rowBounds
	 * @param typeName
	 * @param time
	 * @return
	 */
	@Select("select mo.* from TB_MOVIE mo,TB_MOVIE_TYPE type " +
			"where mo.MOVIE_ID=type.MOVIE_ID and type.TYPE_NAME=#{typeName} " +
			"order by PUBLISH desc")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public List<Movie> getMovieByType(RowBounds rowBounds, @Param("typeName")String typeName);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午3:24:20
	 * Description:按影片类型查询商家影片列表 分页总记录数
	 * @param typeName
	 * @param time
	 * @return
	 */
	@Select("select count(*) from TB_MOVIE mo,TB_MOVIE_TYPE type " +
			"where mo.MOVIE_ID=type.MOVIE_ID and type.TYPE_NAME=#{typeName} and mo.PUBLISH>=#{time}")
	public int selectMovieBytypeCount(@Param("typeName")String typeName,@Param("time")String time);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午3:24:20
	 * Description:按影片类型查询影库影片列表 分页总记录数
	 * @param typeName
	 * @param time
	 * @return
	 */
	@Select("select count(*) from TB_MOVIE mo,TB_MOVIE_TYPE type " +
			"where mo.MOVIE_ID=type.MOVIE_ID and type.TYPE_NAME=#{typeName}")
	public int getMovieBytypeCount(@Param("typeName")String typeName);
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 下午2:03:53
	 * Description:根据影片名查询影片
	 * @param name
	 * @return
	 */
	@Select("select * from TB_MOVIE where NAME=#{name}")
	@ResultMap("com.team3.mbts.mappers.MovieMapper.MovieResult")
	public Movie selectMovieByName(@Param("name")String name);
	
}
