/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.CinemaRemark;
import com.team3.mbts.mappers.CinemaRemarkMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:CinemaRemarkService.java
 * Comments:影院评价服务类
 * JDK Version:
 * Author : LongJun 
 * Create Date:2015-1-23 上午10:07:27
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class CinemaRemarkService {
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-23 上午10:11:22
		 * Description:查询影院评论列表 并分页
		 * @param pageSize
		 * @param curPage
		 * @param cinemaId
		 * @param time
		 * @return List<CinemaRemark>
		 */
		public List<CinemaRemark> selectCinemaRemarks(int pageSize,int curPage,int cinemaId,String time1,String time2)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
			List<CinemaRemark> cinemaRemarks = cinemaRemarkMapper.selectCinemaRemarks(rowBounds, cinemaId, time1,time2);
			sqlSession.close();
			return cinemaRemarks;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-23 上午11:44:41
		 * Description:查询分页总记录数
		 * @param cinemaId
		 * @param time1
		 * @param time2
		 * @return int
		 */
		public int selectCount(int cinemaId,String time1,String time2)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			int count = cinemaRemarkMapper.selectCount(cinemaId, time1, time2);
			sqlSession.close();
			return count;
		}
		
		/**
		 * 查询指定数目的对给定影院的评价
		 *  @author 徐晓聪
		 *  Create Time: 2015-1-24 下午1:48:26
		 *  Description:
		 *  @param cinemaId 影院编号
		 *  @param size 一次加载的评论的数目
		 *  @param prevRecordsCount 已经加载的评论的数目
		 *  @return
		 */
		public List<CinemaRemark> selectCinemaRemarksByCinemaId(int cinemaId, int size, int prevRecordsCount) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			List<CinemaRemark> cinemaRemarks = cinemaRemarkMapper.selectCinemaRemarksByCinemaId(cinemaId, size, prevRecordsCount);
			sqlSession.close();
			return cinemaRemarks;
		}
		
		/**
		 * 加载对指定影院的指定数目的评论
		 * @author 徐晓聪
		 * Create Time : 2015-1-25 下午7:45:35
		 * Description:
		 * @param cinemaId 影院编号
		 * @param numToGet 要获取的评论的数目
		 * @param prevRecords 之前已经加载的评论数
		 * @return
		 */
		public List<CinemaRemark> selectCinemaRemarks(int cinemaId, int numToGet, int prevRecords) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			List<CinemaRemark> cinemaRemarks = 
				cinemaRemarkMapper.selectSpecifiedNumCinemaRemarks(cinemaId, numToGet, prevRecords);
			sqlSession.close();
			return cinemaRemarks;
		}
		
		/**
		 * 加载未过滤的对指定影院的指定数目的评论
		 * @author 徐晓聪
		 * Create Time : 2015-3-2 下午5:22:31
		 * Description:
		 * @param cinemaId 影院编号
		 * @param numToGet 要获取的评论的数目
		 * @param prevRecords 之前已经加载的评论数
		 * @return
		 */
		public List<CinemaRemark> selectSpecifiedNumNoFilterCinemaRemarks(int cinemaId, int numToGet, int prevRecords) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			List<CinemaRemark> cinemaRemarks = 
				cinemaRemarkMapper.selectSpecifiedNumNoFilterCinemaRemarks(cinemaId, numToGet, prevRecords);
			sqlSession.close();
			return cinemaRemarks;
		}
		
		/**
		 * 发表对影院的评论
		 * @author 徐晓聪
		 * Create Time : 2015-1-25 下午11:56:42
		 * Description:
		 * @param cinemaRemark
		 * @return
		 */
		public int insertCinemaRemark(CinemaRemark cinemaRemark) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			int insertFlag = cinemaRemarkMapper.insertCinemaRemark(cinemaRemark);
			sqlSession.commit();
			sqlSession.close();
			return insertFlag;
		}
		
		/**
		 * 统计用户发表的影院评价的数目
		 * @author 徐晓聪
		 * Create Time : 2015-2-17 下午5:20:14
		 * Description:
		 * @param userId 用户编号
		 * @return
		 */
		public int selectUserCinemaRemarkCount(int userId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			int count = cinemaRemarkMapper.selectUserCinemaRemarkCount(userId);
			sqlSession.close();
			return count;
		}
		
		/**
		 * 分页查询用户发表的影院评价
		 * @author 徐晓聪
		 * Create Time : 2015-2-17 下午5:22:15
		 * Description:
		 * @param userId 用户编号
		 * @param curPage
		 * @param pageSize
		 * @return
		 */
		public List<CinemaRemark> selectUserCinemaRemark(int userId, int curPage, int pageSize) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
			List<CinemaRemark> remarkList = cinemaRemarkMapper.selectUserCinemaRemark(userId, rowBounds);
			sqlSession.close();
			return remarkList;
		}
		
		/**
		 * 
		 * @author 徐晓聪
		 * Create Time : 2015-3-2 下午3:41:31
		 * Description:审核影院评论
		 * @param cinemaRemarkId 影院评论编号
		 * @param type 2表示审核通过，3表示审核不通过
		 * @return
		 */
		public int updateCinemaRemark(int cinemaRemarkId, byte type) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaRemarkDAO对象
			CinemaRemarkMapper cinemaRemarkMapper = sqlSession.getMapper(CinemaRemarkMapper.class);
			int updateFlag = cinemaRemarkMapper.updateCinemaRemark(cinemaRemarkId, type);
			sqlSession.commit();
			sqlSession.close();
			return updateFlag;
		}
}
