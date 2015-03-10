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
 * FileName:MovieTypeDAO.java
 * Comments:影片类型关联DAO
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-26 下午3:51:54
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface MovieTypeMapper {
	
	@Insert("insert into TB_MOVIE_TYPE (MOVIE_ID,TYPE_NAME) values (#{movieId},#{typeName})")
	public int insertMovieType(@Param("movieId")int movieId,@Param("typeName")String typeName);
}
