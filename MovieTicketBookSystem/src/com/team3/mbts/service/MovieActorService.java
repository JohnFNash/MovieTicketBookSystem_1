/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.mappers.MovieActorMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:MovieActorService.java
 * Comments:影片演员关联表信息服务类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-26 下午2:14:59
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieActorService {
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 下午2:17:01
	 * Description:添加影片演员关联表
	 * @param actorId
	 * @param actorName
	 * @return
	 */
	public int insertMvoieActor(int movieId,int actorId)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象
		MovieActorMapper movieActorMapper = sqlSession.getMapper(MovieActorMapper.class);
		int row = movieActorMapper.insertMovieActor(movieId,actorId);
		sqlSession.commit();
		sqlSession.close();
		return row;
		
	}
}
