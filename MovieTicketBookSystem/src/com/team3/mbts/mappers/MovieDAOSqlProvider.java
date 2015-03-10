/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import java.text.SimpleDateFormat;

import org.apache.ibatis.jdbc.SQL;

import com.team3.mbts.entity.Movie;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.dao
 * FileName:MovieDAOSqlProvider.java
 * Comments:自定义影片查询操作类 分页 
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-14 上午10:31:03
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class MovieDAOSqlProvider {	
	/**
	 * 插入影片信息
	 * @param movie 影片信息
	 * @return
	 */
	public String insertMovie(final Movie movie) {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String publish = sdf.format(movie.getPublish());
		final int is3D = movie.isIs3D() ? 1:0;
		String sql = new SQL() {
			{
				INSERT_INTO("TB_MOVIE");
				VALUES("NAME,POST,TITLE,IS_3D,AREA,DURATION,LANGUAGE,PUBLISH,DESCRIPTION",
						"'"+movie.getName()+"','"+movie.getPost()+"','"+movie.getTitle()+"',"+is3D+",'"+movie.getArea()+"',"+movie.getDuration()+",'"+movie.getLanguage()+"','"+publish+"','"+movie.getDescription()+"'" );							
			}		
		}.toString();
		return sql;
	}
	
	/**
	 * 插入影片信息
	 * @param movie 影片信息
	 * @return
	 */
	public String updateMovie(final Movie movie) {		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final String publish = sdf.format(movie.getPublish());
		final int is3D = movie.isIs3D() ? 1:0;		 
		String sql = new SQL() {
			{
				UPDATE("TB_MOVIE");
				SET("NAME='"+movie.getName()+"', POST='"+movie.getPost()+"', TITLE='"+movie.getTitle() 
					+ "', IS_3D="+is3D+", AREA='"+movie.getArea()+"', DURATION="+movie.getDuration()
					+", LANGUAGE='"+movie.getLanguage()+"', PUBLISH='"+publish
					+"', DESCRIPTION='"+movie.getDescription()+"'");
				WHERE("MOVIE_ID="+movie.getMovieId());
			}		
		}.toString();
		return sql;
	}
	
}

