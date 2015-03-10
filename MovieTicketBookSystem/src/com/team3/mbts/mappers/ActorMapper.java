/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.team3.mbts.entity.Actor;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:ActorDAO.java
 * Comments:演员、导演信息操作DAO
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-26 上午11:43:39
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface ActorMapper {
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 上午11:47:49
	 * Description:根据导演、演员姓名查询
	 * @param actorName
	 * @return
	 */
	@Select("select top 1 * from TB_ACTOR where ACTOR_NAME=#{actorName}")
	@ResultMap("com.team3.mbts.mappers.ActorMapper.ActorResult")
	public Actor selectActor(@Param("actorName")String actorName);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 上午11:55:10
	 * Description:添加一个演员、导演
	 * @param actorName
	 * @return
	 */
	@Insert("insert into TB_ACTOR (ACTOR_NAME) values (#{actorName})")
	@Options(useGeneratedKeys=true, keyProperty="actorId")
	public int insertActor(Actor actor);
}
