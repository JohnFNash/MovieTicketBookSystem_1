/*
*www.dyr.com
*Copyright (c) 2014 All Rights Revered
*/

/**
 * 
 */
package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Movie;
import com.team3.mbts.mappers.MovieMapper;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.util.PageBean;

/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.cinema
 *FileName:MovieListServlert.java
 *Comments(意见):
 *JDK Version
 *Author:谢洪章
 *Create Date:2015-1-26上午10:56:40
 * @author Administrator
 *
 */
public class MovieListServlert extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Movie movie = new Movie();
		MovieService service = new MovieService();
		
		//获取影院的数目
		int totalRecords = service.countMovies();
		//设置分页包装类
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? "5" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置影院
		List<Movie> cinemaList = 
				service.selectAllMoviesByPage(pageBean.getCurPage(), pageBean.getPageSize());
		pageBean.setDataList(cinemaList);
		request.setAttribute("pageBean", pageBean);
	}

}
