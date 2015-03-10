/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Movie;

import com.team3.mbts.service.CinemaService;
import com.team3.mbts.service.MovieService;

import com.team3.mbts.util.PageBean;

public class LoadCinemasServlet extends HttpServlet {
	private static final long serialVersionUID = -8843955179860344837L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("filter".equals(op)) {//有搜索关键字
			filter(request);
		} else {
			normalProcess(request);
		}
		
		request.getRequestDispatcher("searchCinema.jsp").forward(request, response);
	}

	private void filter(HttpServletRequest request) {		
		
	}

	private void normalProcess(HttpServletRequest request) {
		CinemaService service = new CinemaService();
		MovieService movieService = new MovieService();
		
		//获取影院的数目
		int totalRecords = service.countCinemas();
		//设置分页包装类
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? "5" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置影院
		List<Cinema> cinemaList = 
				service.getCinemasByPage(pageBean.getCurPage(), pageBean.getPageSize());
		pageBean.setDataList(cinemaList);
		request.setAttribute("pageBean", pageBean);
		//设置热映影片
		List<Movie> hotMovieList = 
				movieService.getHotestMoviesByPage(1, 5);
		request.setAttribute("hotMovieList", hotMovieList);
		//设置最受关注的电影院
		List<Cinema> mostAttentionCinemas = service.getMostAttentionCinemas(5);
		request.setAttribute("mostAttentionCinemas", mostAttentionCinemas);
	}

}
