/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Movie;
import com.team3.mbts.service.MovieService;

/**
 * 
 *  Project: moviebookticketsystem
 *  Packages: com.team3.mbts.servlet.movie 
 *  FileName: AdminGetMovieServlet.java
 *  Comments:
 *  JDK Version:
 *	@author 徐晓聪
 *  Create Date: 2015-1-27 上午10:15:50
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public class AdminGetMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 7574591821187383006L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String movieIdStr = request.getParameter("movieId");
		if(!movieIdStr.matches("[0-9]+")) {//传入的影片编号非法
			response.sendRedirect("IndexServlet");
			return;
		}
		
		//查找影片信息
		MovieService service = new MovieService();
		Movie movie = service.getMovieById(Integer.parseInt(movieIdStr));
		request.setAttribute("movie", movie);
		
		request.getRequestDispatcher("movieDetails.jsp").forward(request, response);
	}

}
