/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.team3.mbts.entity.Seat;
import com.team3.mbts.entity.VideoHall;
import com.team3.mbts.mappers.SeatMapper;
import com.team3.mbts.mappers.VideoHallMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:VideoHallService.java
 * Comments:影院影厅服务类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-14 下午4:39:32
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class VideoHallService {
	
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-14 下午6:12:58
		 * Description:	查询影院影厅列表
		 * @param cinemaId
		 * @return List<VideoHall>
		 */
		public List<VideoHall> selectVideoHalls(int cinemaId)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			VideoHallMapper videoHallMapper = sqlSession.getMapper(VideoHallMapper.class);
			List<VideoHall> hallList = videoHallMapper.selectVideoHalls(cinemaId);
			sqlSession.close();
			return hallList;
		}
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-15 上午10:10:36
		 * Description:删除影厅
		 * @param id
		 * @return int
		 */
		public int deleteVideoHall(int id)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			VideoHallMapper videoHallMapper = sqlSession.getMapper(VideoHallMapper.class);
			int row = videoHallMapper.deleteVideoHall(id);
			sqlSession.commit();
			sqlSession.close();
			return row;
		}
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-15 上午11:13:14
		 * Description: 查询影厅是否存在
		 * @param cinemaId
		 * @param no
		 * @return	int（1/0）
		 */
		public int selectExist(int cinemaId,int no)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			VideoHallMapper videoHallMapper = sqlSession.getMapper(VideoHallMapper.class);
			int row = videoHallMapper.selectIDExist(cinemaId,no);
			sqlSession.close();
			return row;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-17 上午9:16:54
		 * Description:添加影厅及其座位
		 * @param videoHall
		 * @return 插入放映厅及座位成功，返回true;否则，返回false
		 */
		public boolean insertHall(VideoHall videoHall, List<Seat> seatList)
		{	
			TransactionFactory transactionFactory = new JdbcTransactionFactory();  //事务工厂
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();			
			Transaction newTransaction = 
					transactionFactory.newTransaction(sqlSession.getConnection());  //开启事务
			
			VideoHallMapper videoHallMapper = sqlSession.getMapper(VideoHallMapper.class);
			SeatMapper seatMapper = sqlSession.getMapper(SeatMapper.class);
			try {
				int row = videoHallMapper.insertHall(videoHall);				
				if(row > 0) {
					int videoHallId = videoHall.getId();//获取放映厅编号
					for(Seat seat : seatList) {//为每个座位设置videoHallId
						seat.setVideoHallId(videoHallId);
					}
					
					for(Seat seat: seatList) {//插入座位
						int flag = seatMapper.insertSeat(seat);
						if(flag == 0) {
							throw new Exception("座位插入出错!");
						}
					}
			
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
		 * @author LongJun
		 * Create Time : 2015-1-17 上午9:46:37
		 * Description:查询影厅编号
		 * @param cinemaId
		 * @param no
		 * @return id
		 */
		public int selectId(int cinemaId,int no)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			VideoHallMapper videoHallMapper = sqlSession.getMapper(VideoHallMapper.class);
			int id = videoHallMapper.selectId(cinemaId, no);
			sqlSession.close();
			return id;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-17 下午2:24:48
		 * Description:根据影厅编号查询影厅
		 * @param id
		 * @return videoHall
		 */
		public VideoHall selectById(int id)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			VideoHallMapper videoHallMapper = sqlSession.getMapper(VideoHallMapper.class);
			VideoHall videoHall = videoHallMapper.selectById(id);
			sqlSession.close();
			return videoHall;
		}
}
