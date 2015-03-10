/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.team3.mbts.entity.Actor;
import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Director;
import com.team3.mbts.entity.Movie;
import com.team3.mbts.entity.MovieRemark;
import com.team3.mbts.mappers.*;
import com.team3.mbts.util.SqlSessionUtil;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:MovieService.java
 * Comments:影片DAO服务类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-14 下午1:51:55
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieService {	
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 下午1:52:34
	 * Description:查询影片列表
	 * @param page
	 * @param date
	 * @return List<Movie>
	 */
	public List<Movie> selectMovies(int page,String date,int pageSize) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		RowBounds rowBounds = new RowBounds((page-1)*pageSize, pageSize);
		List<Movie> movieList = movieMapper.selectMovies(rowBounds, date);
		sqlSession.close();
		return movieList;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午7:00:29
	 * Description:按关键字查询影片
	 * @param key
	 * @return List<Movie>
	 */
	public List<Movie> selectMovieByKey(String key,String time) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		List<Movie> movieList = movieMapper.selectMovieByKey(key,time);
		sqlSession.close();
		return movieList;
	}
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午7:00:29
	 * Description:按关键字查询影片
	 * @param key
	 * @return List<Movie>
	 */
	public List<Movie> getMovieByKey(String key) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		List<Movie> movieList = movieMapper.getMovieByKey(key);
		sqlSession.close();
		return movieList;
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午10:29:21
	 * Description:得到影片列表总记录数
	 * @param date
	 * @return int
	 */
	public int getMovieCount(String date)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		int count = movieMapper.getMovieCount(date);
		sqlSession.close();
		return count;
	}
	
	/**
	 * 统计所有影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-22 上午0:21:44
	 *  Description:
	 *  @return
	 */
	public int countMovies() {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		int count = movieMapper.countMovies();
		sqlSession.close();
		return count;
	}
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 下午1:52:34
	 * Description:添加排期影片选择列表
	 * @param page
	 * @param date
	 * @return List<Movie>
	 */
	public List<Movie> selectAddListing(String date,String date2) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		List<Movie> movieList = movieMapper.selectAddListing(date, date2);
		sqlSession.close();
		return movieList;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 下午1:52:34
	 * Description:添加排期影片选择列表
	 * @param page
	 * @param date
	 * @return List<Movie>
	 */
	public List<Movie> selectMovieList(String date) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		List<Movie> movieList = movieMapper.selectMovieList(date);
		sqlSession.close();
		return movieList;
	}
	
	/**
	 * 查询正在热映的影片的总数
	 * @author 徐晓聪
	 * Create Time : 2015-1-16 下午11:02:03
	 * Description:查询影片列表
	 * @return 正在热映的影片的数目
	 */
	public int countHotestMovies() {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		int count  = movieMapper.countHotestMovies();
		sqlSession.close();
		return count;
	}
	
	/**
	 * 统计即将上映的影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 下午3:39:45
	 *  Description:
	 *  @return
	 */
	public int countFutureMovies() {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		int count  = movieMapper.countFutureMovies();
		return count;
	}

	/**
	 * 分页查询热映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-14 上午10:29:47
	 *  Description:
	 *  @param curPage 当前页
	 *  @param pageSize 页大小
	 *  @return 查询到的影片列表
	 */
	public List<Movie> getHotestMoviesByPage(int curPage, int pageSize) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		List<Movie> movies = movieMapper.getHotestMoviesByPage(rowBounds);
		sqlSession.close();
		return movies;
	}
	
	/**
	 * 获取热门影院的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 上午11:43:03
	 *  Description:
	 *  @return
	 */
	public int countHotCinemas() {
		 SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
		 int count = cinemaMapper.countCinemas();
		 sqlSession.close();
		return count;
	}
	
	/**
	 * 按页获取热门影院
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 上午11:45:51
	 *  Description:
	 *  @param curPage 当前页
	 *  @param pageSize 页大小
	 *  @return
	 */
	public List<Cinema> getHotCinemasByPage(int curPage, int pageSize) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
		 RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		 List<Cinema> cinemas = cinemaMapper.getSimplifiedCinemasByPage(rowBounds);
		 sqlSession.close();
		return cinemas;
	}
	
	/**
	 *  统计给定电影的评价的总数
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 下午12:58:16
	 *  Description:
	 *  @param moiveId 影片编号
	 *  @return
	 */
	public int countMovieRemark(int movieId) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieRemarkMapper remarkDAO = sqlSession.getMapper(MovieRemarkMapper.class);
		 int count = remarkDAO.countMovieRemark(movieId);
		 sqlSession.close();
		return count;
	}	

	/**
	 * 返回最近的指定数目条电影评论
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 下午2:08:44
	 *  Description:
	 *  @param numberToGet 要获取的评论的数目
	 *  @return
	 */
	public List<MovieRemark> getTopWonderfulMoiveRemarks(int numberToGet) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieRemarkMapper remarkDAO = sqlSession.getMapper(MovieRemarkMapper.class);
		 List<MovieRemark> movieRemarks = remarkDAO.getTopWonderfulMoiveRemarks(numberToGet);
		 sqlSession.close();
		return movieRemarks;
	}

	/**
	 * 根据影片编号获取影片信息
	 * @param movieId 影片编号
	 * @return 影片信息
	 */
	public Movie getMovieById(int movieId) {
		 SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 Movie movie = movieMapper.getMovieById(movieId);
		 sqlSession.close();
		return movie;
	}
	
	/**
	 * 根据影片类型和搜索关键字等查询相应的热映影片数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-2-19 下午10:15:54
	 *  Description:
	 *  @param typeName 影片类型名
	 *  @param key 搜索关键字
	 *  @param order 排序规则
	 *  @return
	 */
	public int selectHotMoviesCountByDynamicParameter(String typeName, String key, String order) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 
		int movieCount = movieMapper.selectHotMoviesCountByDynamicParameter(typeName, key, order);
		sqlSession.close();
		return movieCount;
	}
	
	/**
	 * 根据影片类型和搜索关键字等查询热映影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 上午10:42:34
	 *  Description:
	 *  @param typeName 类型名称
	 *  @param key 搜索关键字
	 *  @param order 排序规则
	 *  @param rowBounds 分页信息对象
	 *  @return
	 */
	public List<Movie> getHotMoviesByDynamicParameter(String typeName, String key, String order, RowBounds rowBounds) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 
		 List<Movie> movies = movieMapper.getHotMoviesByDynamicParameter(rowBounds, typeName, key, order);
		 sqlSession.close();
		return movies;
	}
	
	/**
	 * 分页查询即将上映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 下午3:29:42
	 *  Description:
	 *  @param curPage 当前页
	 *  @param pageSize 页大小
	 *  @return
	 */
	public List<Movie> getFutureMoviesByPage(int curPage, int pageSize) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		 List<Movie> movies = movieMapper.getFutureMoviesByPage(rowBounds);
		 sqlSession.close();
		return movies;
	}
	
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
	public int countFutureMoviesByDynamicParameter(String typeName, String key, String order) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		int movieCount = movieMapper.countFutureMoviesByDynamicParameter(typeName, key, order);
		sqlSession.close();
		return movieCount;
	}
	
	/**
	 * 根据影片类型和搜索关键字等查询即将上映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-21 下午5:25:29
	 *  Description:
	 *  @param typeName
	 *  @param key
	 *  @param order
	 *  @param rowBounds
	 *  @return
	 */
	public List<Movie> getFutureMoviesByDynamicParameter(String typeName, String key, String order, RowBounds rowBounds) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 List<Movie> movies = movieMapper.getFutureMoviesByDynamicParameter(typeName, key, order, rowBounds);
		 sqlSession.close();
		return movies;
	}
	
	/**
	 * 分页查询影片
	 * @author 徐晓聪
	 * Create Time : 2015-1-22 上午0:25:42
	 * Description:
	 * @param curPage 当前页
	 * @param pageSize 页大小 
	 * @return List<Movie>
	 */	
	public List<Movie> selectAllMoviesByPage(int curPage,int pageSize) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		List<Movie> movies = movieMapper.selectAllMoviesByPage(rowBounds);
		sqlSession .close();
		return movies;
	}
	
	/**
	 * 筛选电影库中的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-22 下午6:22:06
	 *  Description:
	 *  @param area 区域
	 *  @param typeName 类型名称
	 *  @param startTime 起止时间
	 *  @param endTime 结束时间
	 *  @param key 搜索关键字
	 *  @param rowBounds
	 *  @return
	 */
	public List<Movie> filterMoviesInMovieStore(String area, String typeName, 
			String startTime, String endTime, String key, RowBounds rowBounds) {
		 SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 List<Movie> movies =  
			 movieMapper.filterMoviesInMovieStore(area, typeName, startTime, endTime, key, rowBounds);
		 sqlSession.close();
		return movies;
	}
	
	/**
	 * 统计筛选后的影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-22 下午6:33:44
	 *  Description:
	 *  @param area 区域
	 *  @param typeName 类型名称
	 *  @param startTime 时间上限
	 *  @param endTime 时间下限
	 *  @param key 搜索
	 *  @return
	 */
	public int countFilterMoviesInMovieStore(String area, String typeName, String startTime, String endTime, String key) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 int count = movieMapper.countFilterMoviesInMovieStore(area, typeName, startTime, endTime, key);
		 sqlSession.close();
		return count;
	}
	
	/**
	 * 查找给定影院当天在上映的影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午12:55:44
	 *  Description:
	 *  @param cinemaId 影院编号
	 *  @param day 日期字符串
	 *  @param curPage 当前页
	 *  @param pageSize 页大小
	 *  @return
	 */
	public List<Movie> getCinemaHotestMoviesByPage(int cinemaId, String day) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 List<Movie> movies = movieMapper.getCinemaHotestMoviesByPage(cinemaId, day);
		 sqlSession.close();
		return movies;
	}
	
	/**
	 * 统计给定影院当天在上映的影片的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午12:55:44
	 *  Description:
	 *  @param cinemaId
	 *  @return
	 */
	public int countCinemaHotestMovies(int cinemaId, String day) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 int count = movieMapper.countCinemaHotestMovies(cinemaId, day);
		 sqlSession .close();
		return count;
	}
	
	/**
	 * 获取最受关注的指定数目部影片
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午12:58:23
	 *  Description:
	 *  @param numToGet 要获取的影片的数目
	 *  @return
	 */
	public List<Movie> getMostAttentionFutureMovies(int numToGet) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 List<Movie> movies = movieMapper.getMostAttentionFutureMovies(numToGet);
		 sqlSession.close();
		return movies;
	}
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午2:09:28
	 * Description:根据影片类型查询 商家影片列表 加入分页
	 * @param typeName
	 * @return
	 */
	public List<Movie> selectMovieByType(int pageSize,int curPage,String typeName,String time)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		 List<Movie> movies = movieMapper.selectMovieByType(rowBounds,typeName,time);
		 sqlSession.close();
		return movies;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午2:09:28
	 * Description:根据影片类型查询 影库 影片列表 加入分页
	 * @param typeName
	 * @return
	 */
	public List<Movie> getMovieByType(int pageSize,int curPage,String typeName)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		 List<Movie> movies = movieMapper.getMovieByType(rowBounds,typeName);
		 sqlSession.close();
		return movies;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午3:25:42
	 * Description:根据影片类型查询 商家影片列表 分页总记录数
	 * @param typeName
	 * @param time
	 * @return
	 */
	public int pageCount(String typeName,String time)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 int count = movieMapper.selectMovieBytypeCount(typeName, time);
		 sqlSession.close();
		return count;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午3:25:42
	 * Description:根据影片类型查询 商家影片列表 分页总记录数
	 * @param typeName
	 * @param time
	 * @return
	 */
	public int getPageCount(String typeName)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 int count = movieMapper.getMovieBytypeCount(typeName);
		 sqlSession.close();
		return count;
	}
	
	/**
	 * 添加影片
	 * @param movie 影片
	 * @param directors 导演名
	 * @param actors 主演名
	 * @param movieTypes 影片类型
	 * @return 影片及相关信息插入成功，返回true;否则，返回false
	 */
	public boolean insertMovie(Movie movie, String[] directors, String[] actors, List<String> movieTypes)
	{			
		TransactionFactory transactionFactory = new JdbcTransactionFactory();  //事务工厂
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();			
		Transaction newTransaction = 
				transactionFactory.newTransaction(sqlSession.getConnection());  //开启事务
		
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);
		DirectorMapper directoryMapper = sqlSession.getMapper(DirectorMapper.class);
		ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
		MovieActorMapper movieActorMapper = sqlSession.getMapper(MovieActorMapper.class);
		MovieDirectorMapper movieDirectorMapper = sqlSession.getMapper(MovieDirectorMapper.class);
		MovieTypeMapper movieTypeMapper = sqlSession.getMapper(MovieTypeMapper.class);
		try {
			int row = movieMapper.insertMovie(movie);			
			if(row > 0) {//电影信息插入成功
				int movieId = movie.getMovieId();
				//循环插入导演信息
				for(String directorName : directors) {
					Director director = new Director(directorName);
					int insertFlag =  directoryMapper.insertDirector(director);
					if(insertFlag == 0) {
						throw new Exception("导演插入失败");
					} else {//插入导演成功，则插入影片-导演关联信息						
						int flag = movieDirectorMapper.insertMovieDirector(movieId, director.getDirectorId());
						if(flag == 0) {
							throw new Exception("影片-主演信息插入失败");
						}
					}
				}
				
				//循环插入主演信息
				for(String actorName : actors) {
					Actor actor = new Actor(actorName);
					int insertFlag =  actorMapper.insertActor(actor);
					if(insertFlag == 0) {
						throw new Exception("主演插入失败");
					} else {//插入主演成功，则插入影片-主演关联信息						
						int flag = movieActorMapper.insertMovieActor(movieId, actor.getActorId());
						if(flag == 0) {
							throw new Exception("影片-主演信息插入失败");
						}
					}
				}
				
				//循环插入影片类型信息
				for(String typeName : movieTypes) {
					int insertFlag = movieTypeMapper.insertMovieType(movieId, typeName);
					if(insertFlag == 0) {
						throw new Exception("影片-类型关联信息插入失败");
					}
				}
				
				return true;
			}
						
		} catch (Exception ex) {
		    try {
			   newTransaction.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    ex.printStackTrace();
		} finally {
		   try {
			   newTransaction.commit();
			   
			   newTransaction.close();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   }
		}
		
		return false;
	}
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-3-1 下午12:54:11
	 * Description:更新影片
	 * @param movie
	 * @return （返回）1 表示成功，0表示失败 
	 */
	public int updateMovie(Movie movie) {
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		int updateFlag = movieMapper.updateMovie(movie);
		sqlSession.commit();
		sqlSession.close();
		return updateFlag;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 下午2:05:56
	 * Description:根据片名查询影片
	 * @param name
	 * @return
	 */
	public Movie selectMovieByName(String name)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		Movie movie = movieMapper.selectMovieByName(name);
		sqlSession.close();
		return movie;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-27 下午1:58:07
	 * Description:查询所有影片的数量
	 * @return
	 */
	public int selectAllMovieCount()
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 int count = movieMapper.selectAllMovieCount();
		 sqlSession.close();
		 return count;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-27 下午1:58:07
	 * Description:查询所有影片
	 * @return
	 */
	public List<Movie> selectAllMovie(int pageSize,int curPage)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象	
		 MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);	//获取MovieMapper对象
		 RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		 List<Movie> movies = movieMapper.selectAllMovie(rowBounds);
		 sqlSession.close();
		 return movies;
	}
}
