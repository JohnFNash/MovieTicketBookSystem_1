/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.service.CinemaService;
import com.team3.mbts.servlet.cinema.CinemaRemarkServlet;

public class DeletCinemaRemarServlet extends HttpServlet {
	CinemaService cinemaService = new CinemaService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int Id =Integer.parseInt(request.getParameter("id"));
		 int remove= cinemaService.removeCinemaRemarkt(Id);
		 PrintWriter out = response.getWriter();
		 if(remove==1){
			 out.write("success");
			 out.flush();
			 out.close();
		 }
	}

}
