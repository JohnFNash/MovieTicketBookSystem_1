package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.MovieRemark;
import com.team3.mbts.mappers.MovieRemarkMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * @author 徐晓聪
 *
 */
public class MovieRemarkService {
	
	
	/**
	 * 加载对指定影片的指定数目的评论
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 上午2:09:15
	 * Description:
	 * @param movieId 影片编号
	 * @param numToGet 要获取的评论的数目
	 * @param prevRecords 之前已经加载的评论数
	 * @return
	 */
	public List<MovieRemark> selectSpecifiedNumMovieRemarks(int movieId, int numToGet, int prevRecords) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取CinemaRemarkDAO对象
		MovieRemarkMapper movieRemarkMapper = sqlSession.getMapper(MovieRemarkMapper.class);
		List<MovieRemark> movieRemarks = 
			movieRemarkMapper.selectSpecifiedNumMovieRemarks(movieId, numToGet, prevRecords);
		sqlSession.close();
		return movieRemarks;
	}
	
	/**
	 * 发表对影片的评论
	 * @author 徐晓聪
	 * Create Time : 2015-1-26 上午2:44:34
	 * Description:
	 * @param movieRemark
	 * @return
	 */
	public int insertMovieRemark(MovieRemark movieRemark) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取CinemaRemarkDAO对象
		MovieRemarkMapper movieRemarkMapper = sqlSession.getMapper(MovieRemarkMapper.class);
		int insertFlag = movieRemarkMapper.insertMovieRemark(movieRemark);
		sqlSession.commit();
		sqlSession.close();
		return insertFlag;
	}
	
	/**
	 * 根据影片编号查找影评信息
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-26 下午4:38:43
	 *  Description:
	 *  @param movieRemarkId 影评编号
	 *  @return
	 */
	public MovieRemark getMovieRemarkById(int movieRemarkId) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取CinemaRemarkDAO对象
		MovieRemarkMapper movieRemarkMapper = sqlSession.getMapper(MovieRemarkMapper.class);
		MovieRemark movieRemark = movieRemarkMapper.getMovieRemarkById(movieRemarkId);
		sqlSession.close();
		return movieRemark;
	}
	
	/**
	 * 分页查询指定用户的评论
	 *  @author 徐晓聪
	 *  Create Time: 2015-2-17 下午12:59:13
	 *  Description:
	 *  @param userId 用户编号
	 *  @param rowBounds
	 *  @return
	 */
	public List<MovieRemark> getMoiveRemarksForUserByPage(int userId, int curPage, int pageSize) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取CinemaRemarkDAO对象
		MovieRemarkMapper movieRemarkMapper = sqlSession.getMapper(MovieRemarkMapper.class);
		RowBounds rowBounds = new RowBounds(pageSize*(curPage-1), pageSize);
		List<MovieRemark> movieRemarkList = movieRemarkMapper.getMoiveRemarksForUserByPage(userId, rowBounds);
		sqlSession.close();
		return movieRemarkList;
	}

	/**
	 * 统计指定用户的评论的数目
	 *  @author 徐晓聪
	 *  Create Time: 2015-2-17 下午1:04:34
	 *  Description:
	 *  @param userId 用户编号
	 *  @return
	 */
	public int selectMovieRemarksForUserCount(int userId) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取CinemaRemarkDAO对象
		MovieRemarkMapper movieRemarkMapper = sqlSession.getMapper(MovieRemarkMapper.class);
		int movieRemarkCount = movieRemarkMapper.selectMovieRemarksForUserCount(userId);
		sqlSession.close();
		return movieRemarkCount;
	}
	
}
