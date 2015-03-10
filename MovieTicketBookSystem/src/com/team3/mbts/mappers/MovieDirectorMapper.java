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
 * FileName:MovieDirectorMapper.java
 * Comments:影片导演关联表DAO
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-20 下午10:40:25
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface MovieDirectorMapper {	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-20 下午10:42:12
	 * Description:添加影片导演关联表
	 * @param movieId
	 * @param directorId
	 * @return
	 */
	@Insert("insert into TB_MOVIE_DIRECTOR (MOVIE_ID,DIRECTOR_ID) values (#{movieId},#{directorId})")
	public int insertMovieDirector(@Param("movieId")int movieId,@Param("directorId")int directorId);
}
