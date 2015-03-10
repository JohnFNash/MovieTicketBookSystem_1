/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.movie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.Movie;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.util.PageBean;

public class FutureMovieServlet extends HttpServlet {
	private static final long serialVersionUID = -5452653609809415923L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MovieService service = new MovieService();
		
		filterMovie(request, service);//筛选即将上映电影
		
		//获取最受关注的10部影片
		List<Movie> top10Movies = service.getMostAttentionFutureMovies(10);
		request.setAttribute("attentionMovies", top10Movies);
		
		request.getRequestDispatcher("futureMovie.jsp").forward(request, response);		
	}

	
	private void filterMovie(HttpServletRequest request, MovieService service) throws UnsupportedEncodingException {
		String typeName = request.getParameter("typeName");
		String key = request.getParameter("key");
		String order = request.getParameter("order");
		if(typeName != null && !"".equals(typeName)) {
			typeName = new String(typeName.getBytes("ISO-8859-1"),"UTF-8");
			request.setAttribute("typeName", typeName);
		}
		if(key != null && !"".equals(key)) {
			key = new String(key.getBytes("ISO-8859-1"),"UTF-8");
			request.setAttribute("key", key);
		}
		if(order != null && !"".equals(order)) {
			order = new String(order.getBytes("ISO-8859-1"),"UTF-8");
			request.setAttribute("order", order);
		}
		
		int totalRecords = service.countFutureMoviesByDynamicParameter(typeName, key, order);
		final int futureMovieNum = 6;	//默认加载的即将上演影片的数目
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? 
						futureMovieNum + "" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置最热影片
		int cutPage = pageBean.getCurPage();
		int pageSize = pageBean.getPageSize();
		RowBounds rowBounds = new RowBounds(pageSize*(cutPage-1), pageBean.getPageSize());
		List<Movie> movieList = 
				service.getFutureMoviesByDynamicParameter(typeName, key, order, rowBounds);
		pageBean.setDataList(movieList);
		//将pageBean放入request
		request.setAttribute("pageBean", pageBean);		
	}

}
