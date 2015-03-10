/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.mappers.MovieTypeMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:MovieTypeService.java
 * Comments:影片类型关联表服务类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-26 下午3:55:17
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieTypeService {
	
	
	public int insertMovieType(int movieId,String typeName)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象
		MovieTypeMapper movieTypeMapper = sqlSession.getMapper(MovieTypeMapper.class);
		int row = movieTypeMapper.insertMovieType(movieId, typeName);
		sqlSession.commit();
		sqlSession.close();
		return row;
	}
}
