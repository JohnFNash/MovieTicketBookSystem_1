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

import com.team3.mbts.entity.MovieRemark;
import com.team3.mbts.service.MovieRemarkService;

public class GetMovieRemarkServlet extends HttpServlet {
	private static final long serialVersionUID = -523789196132862025L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int movieRemarkId = Integer.parseInt(request.getParameter("movieRemarkId"));
		MovieRemarkService service = new MovieRemarkService();
		MovieRemark movieRemark = service.getMovieRemarkById(movieRemarkId);
		request.setAttribute("movieRemark", movieRemark);
		request.getRequestDispatcher("movieRemark.jsp").forward(request, response);
	}

}
