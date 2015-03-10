/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.Area;
import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.CinemaRemark;
import com.team3.mbts.mappers.AreaMapper;
import com.team3.mbts.mappers.CinemaMapper;
import com.team3.mbts.mappers.SellerCinemaMapper;
import com.team3.mbts.util.SqlSessionUtil;

public class CinemaService {				
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-14 上午9:20:19
		 * Description:	根据商家外键影院编号查询影院
		 * @param cinemaId
		 * @return cinema
		 */
		public Cinema selectCinemaById(int cinemaId)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			Cinema cinema = cinemaMapper.selectCinemaById(cinemaId);
			sqlSession.close();
			return cinema;
		}
		
		/**
		 * 查询所有影院的方法
		 * Create Time:2015-1-21上午11:30:14
		 * returnType:List<Cinema>
		 * @return
		 */
		public List<Cinema> getAllCinemas() {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			return cinemaMapper.getAllCinemas1();
		}
		
		/**
		 * 获取指定商家的影院中的第一家影院
		 * @param sellerId 商家编号
		 * @return
		 */
		public Cinema getFirstCinemaForSeller(int sellerId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			return cinemaMapper.selectFirstCinemaForSeller(sellerId);
		}
		
		/**
		 * 根据名字查询影院的方法
		 * @author 谢洪章
		 * Create Time:2015-1-19下午3:52:56
		 * returnType:Cinema
		 * @return
		 */
		public Cinema getCinemaByName(String cinemaName){
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			Cinema cinema = cinemaMapper.getCinemaByName(cinemaName);
			sqlSession.close();
			return cinema;
		}
	
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-22 下午3:11:36
		 * Description:
		 * @param cinema
		 * @return int 
		 */
		public int insertCinema(Cinema cinema) 	{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			int row = cinemaMapper.insertCinema(cinema);
			sqlSession.commit();
			sqlSession.close();
			return row;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-22 下午4:42:46
		 * Description:修改商家信息
		 * @param cinema
		 * @return int
		 */
		public int updateCinema(Cinema cinema)
		{
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			int row = cinemaMapper.updateCinema(cinema);
			sqlSession.commit();
			sqlSession.close();
			return row;
		}
		

		public Cinema getAllCinemas(String text) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			Cinema cinema = cinemaMapper.getAllCinemas(text); 
			sqlSession.close();
			return cinema;       
		}
		/**
		 *Create Time:2015-1-22 上午10:15:51
		 *Description:删除评价
		 *@param 根据account删除
		 *@return 删除成功返回1，否者返回0
		 */
		public int removeCinemaRemarkt(int id) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			int deleteFlag = cinemaMapper.deleteCinemaRemark(id);
			sqlSession.commit();
			sqlSession.close();
			return deleteFlag ;
		}
		
		/**
		 * 根据影院编号显示评论
		 * Create Time:2015-1-21下午3:52:56
		 * returnType:List<Cinema>
		 * @return
		 */
		public List<CinemaRemark> getcinemaRemark(int cinemaId ,int prev){
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			try{
				CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
				return cinemaMapper.getcinemaRemark(cinemaId,prev);
			}finally{
				sqlSession.clearCache();
				sqlSession.close();
			}
		}
			
		/**
		 * 统计电影院的数目
		 *  @author 徐晓聪
		 *  Create Time: 2015-1-23 上午10:30:30
		 *  Description:
		 *  @return
		 */
		public int countCinemas() {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			int count = cinemaMapper.countCinemas();
			sqlSession.close();
			return count;
		}	
		
		/**
		 * 分页获取影院的详细信息
		 *  @author 徐晓聪
		 *  Create Time: 2015-1-23 上午10:31:25
		 *  Description:
		 *  @param curPage
		 *  @param pageSize
		 *  @return
		 */
		public List<Cinema> getCinemasByPage(int curPage, int pageSize) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
			List<Cinema> cinemas = cinemaMapper.getCinemasByPage(rowBounds);
			sqlSession.close();
			return cinemas;
		}

		/**
		 * 查询指定日期在放映指定影片的影院中最受关注的几个
		 * @author 徐晓聪
		 * Create Time: 2015-1-24 上午23:13:40
		 * Description: 
		 * @param movieId
		 * @param day
		 * @param numToGet 要获得的影院的个数
		 * @return
		 */
		public List<Cinema> selectCinemasForMovieOnShow(int movieId, String day, int numToGet) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			List<Cinema> cinemas = cinemaMapper.selectCinemasForMovieOnShow(movieId, day, numToGet);
			sqlSession.close();
			return cinemas;
		}
		
		/**
		 * 获取指定数目的最受关注的电影院
		 * @author 徐晓聪
		 * Create Time: 2015-1-25 下午14:31:40
		 * Description: 
		 * @param numToGet 要获得的影院的个数
		 * @return
		 */
		public List<Cinema> getMostAttentionCinemas(int cinemaNumToGet) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取UserInfoDAO对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			List<Cinema> cinemas = cinemaMapper.getMostAttentionCinemas(cinemaNumToGet);
			sqlSession.close();
			return cinemas;
		}
		
		/**
		 * 
		 * @author 徐晓聪
		 * Create Time : 2015-2-28 下午3:03:23
		 * Description:查询省、直辖市级行政区域
		 * @return
		 */	
		public List<Area> selectFirstLevelAreas() {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取AreaMapper对象
			AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
			List<Area> areaList = areaMapper.selectFirstLevelAreas();
			sqlSession.close();
			return areaList;
		}
		
		/**
		 * 
		 * @author 徐晓聪
		 * Create Time : 2015-2-28 下午3:03:23
		 * Description:查询省、直辖市级行政区域下的第二级划分
		 * @param firstLevelId 省、直辖市级行政区域的编号
		 * @return
		 */	
		public List<Area> selectSecondLevelAreas(int firstLevelId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取AreaMapper对象
			AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
			List<Area> areaList = areaMapper.selectSecondLevelAreas(firstLevelId);
			sqlSession.close();
			return areaList;
		}
		
		/**
		 * 
		 * @author 徐晓聪
		 * Create Time : 2015-2-28 下午3:03:23
		 * Description:查询市辖区、县下的第三级划分
		 * @param secondLevelId 市辖区、县级行政区域的编号
		 * @return
		 */	
		public List<Area> selectThirdLevelAreas(int secondLevelId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取AreaMapper对象
			AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
			List<Area> areaList = areaMapper.selectThirdLevelAreas(secondLevelId);
			sqlSession.close();
			return areaList;
		}
		
		/**
		 * 
		 * @author 徐晓聪
		 * Create Time : 2015-2-28 下午7:18:23
		 * Description:查询给定行政区域的父级行政区域
		 * @param areaId 政区域编号
		 * @return
		 */	
		public Area selectParentLevelAreas(int areaId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取AreaMapper对象
			AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
			Area parentArea = areaMapper.selectParentLevelAreas(areaId);
			sqlSession.close();
			return parentArea;
		}
		
		/**
		 * 
		 * @author 徐晓聪
		 * Create Time : 2015-2-28 下午8:07:51
		 * Description:添加商家-影院关联信息
		 * @param sellerId 商家编号
		 * @param cinemaId 影院编号
		 * @return
		 */
		public int insertSellerCinema(int sellerId, int cinemaId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取AreaMapper对象
			SellerCinemaMapper sellerCinemaMapper = sqlSession.getMapper(SellerCinemaMapper.class);
			int insertFlag = sellerCinemaMapper.insertSellerCinema(sellerId, cinemaId);
			sqlSession.commit();
			sqlSession.close();
			return insertFlag;
		}
		
		/**
		 * 获取指定商家的所有影院
		 * @param sellerId 商家编号
		 * @return
		 */
		public List<Cinema> selectAllCinemaForSeller(int sellerId) {
			//获取SqlSession对象
			SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
			//获取CinemaMapper对象
			CinemaMapper cinemaMapper = sqlSession.getMapper(CinemaMapper.class);
			List<Cinema> cinemas = cinemaMapper.selectAllCinemaForSeller(sellerId);
			sqlSession.close();
			return cinemas;
		}
}
