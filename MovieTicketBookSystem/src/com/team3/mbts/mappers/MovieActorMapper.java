/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:Movie_ActorDAO.java
 * Comments:影片演员关联表DAO
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-26 下午2:11:15
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface MovieActorMapper {
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 下午2:13:57
	 * Description:添加影片演员关联表
	 * @param movieId
	 * @param actorId
	 * @return
	 */
	@Insert("insert into TB_MOVIE_ACTOR (MOVIE_ID,ACTOR_ID) values (#{movieId},#{actorId})")
	public int insertMovieActor(@Param("movieId")int movieId,@Param("actorId")int actorId);
}
