/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Movie;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.util.PageBean;

public class LoadHotestMovieServlet extends HttpServlet {
	private static final long serialVersionUID = -8432118070854727268L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MovieService service = new MovieService();
		
		//获取正在热映的影片的数目
		int totalRecords = service.countHotestMovies();
		//设置分页包装类
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? "10" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置最热影片
		List<Movie> movieList = 
				service.getHotestMoviesByPage(pageBean.getCurPage(), pageBean.getPageSize());
		pageBean.setDataList(movieList);
	
		PrintWriter out = response.getWriter();
		out.write(getJson(movieList));//输出json数组
		out.close();
		out.flush();
	}

	//将电影列表转换为json
	private String getJson(List<Movie> movieList) {
		StringBuffer json = new StringBuffer();
		for(Movie movie: movieList) {
			json.append(movie.toString());			
		}
		return json.toString();
	}
	
}
