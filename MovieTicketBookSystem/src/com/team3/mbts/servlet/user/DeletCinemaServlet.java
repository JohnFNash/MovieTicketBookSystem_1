/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.Remove;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Woodstox;
import com.team3.mbts.service.CinemaService;

public class DeletCinemaServlet extends HttpServlet {
	private CinemaService cinemaService = new CinemaService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//获取用户账号
		 String  account= request.getParameter("account");
		   // String remove=cinemaService.removeAccount(account);	  
		 
	}

}
