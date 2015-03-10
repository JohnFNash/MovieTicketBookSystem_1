/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.Actor;
import com.team3.mbts.mappers.ActorMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:ActorService.java
 * Comments:导演、演员信息服务类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-26 上午11:48:39
 * Modified By : LongJun
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class ActorService {
	
	
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 上午11:51:02
	 * Description:根据导演、演员查询
	 * @param actorName
	 * @return
	 */
	public Actor selectActor(String actorName)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象
		ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
		Actor actor = actorMapper.selectActor(actorName);
		sqlSession.close();
		return actor;
			
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 上午11:56:33
	 * Description:添加一个演员、导演
	 * @param actorName
	 * @return
	 */
	public int insertActor(String actorName)
	{
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();	//获取SqlSession对象
		ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);
		int row = actorMapper.insertActor(new Actor(actorName));
		sqlSession.commit();
		sqlSession.close();
		return row;
	}
}
