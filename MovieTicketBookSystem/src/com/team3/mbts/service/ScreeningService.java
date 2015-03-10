/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.Screenings;
import com.team3.mbts.mappers.ScreeningMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:ScreeningService.java
 * Comments:排期场次服务类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-19 下午4:11:18
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class ScreeningService {
		
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-19 下午4:13:50
		 * Description:添加一个影片排期
		 * @param screenings
		 * @return int
		 */
		public int insertScreening(Screenings screenings)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			ScreeningMapper screeningMapper = sqlSession.getMapper(ScreeningMapper.class);
			int row = screeningMapper.insertScreening(screenings);
			sqlSession.commit();
			sqlSession.close();
			return row;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-20 上午9:43:39
		 * Description:查询排期列表
		 * @param cinemaId
		 * @param startTime
		 * @return List<Screenings>
		 */
		public List<Screenings> selectScreenings(int cinemaId,String startTime)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			ScreeningMapper screeningMapper = sqlSession.getMapper(ScreeningMapper.class);
			List<Screenings> sList = screeningMapper.selectScreenings(cinemaId, startTime);
			sqlSession.close();
			return sList;
		}
		
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-20 上午9:43:39
		 * Description:添加排期输入的影厅编号和时间在已排期的列表中时间先于它的第一条场次数据
		 * @param cinemaId
		 * @param startTime
		 * @return List<Screenings>
		 */
		public List<Screenings> selectPre(int videoHallId,String startTime)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			ScreeningMapper screeningMapper = sqlSession.getMapper(ScreeningMapper.class);
			List<Screenings> sList = screeningMapper.selectPre(videoHallId, startTime);
			sqlSession.close();
			return sList;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-20 上午9:43:39
		 * Description:添加排期输入的影厅编号和时间在已排期的列表中时间大于它的第一条场次数据
		 * @param cinemaId
		 * @param startTime
		 * @return List<Screenings>
		 */
		public List<Screenings> selectNext(int videoHallId,String startTime)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			ScreeningMapper screeningMapper = sqlSession.getMapper(ScreeningMapper.class);
			List<Screenings> sList = screeningMapper.selectNext(videoHallId, startTime);
			sqlSession.close();
			return sList;
		}
		
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
		public List<Screenings> selectScreeningsForMovie(int cinemaId, int movieId, String day) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			ScreeningMapper screeningMapper = sqlSession.getMapper(ScreeningMapper.class);
			List<Screenings> screenings = screeningMapper.selectScreeningsForMovie(cinemaId, movieId, day);
			sqlSession.close();
			return screenings;
		}
		
		public int deleteScreening(int id )
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			ScreeningMapper screeningMapper = sqlSession.getMapper(ScreeningMapper.class);
			int row = screeningMapper.deleteScreening(id);
			sqlSession.commit();
			sqlSession.close();
			return row;
		}
		
		/**
		 * 根据场次编号查找场次信息
		 *  @author 徐晓聪
		 *  Create Time: 2015-1-26 上午11:04:14
		 *  Description:
		 *  @param screeningId 场次编号
		 *  @return
		 */	
		public Screenings getScreeningById(int screeningId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			ScreeningMapper screeningMapper = sqlSession.getMapper(ScreeningMapper.class);
			Screenings screenings = screeningMapper.getScreeningById(screeningId);
			sqlSession.close();
			return screenings;
		}
}
