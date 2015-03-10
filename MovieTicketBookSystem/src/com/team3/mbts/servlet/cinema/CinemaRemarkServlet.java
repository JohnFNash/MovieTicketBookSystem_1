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
import com.team3.mbts.mappers.CinemaMapper;

/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.cinema
 *FileName:CinemaRemarkServlet.java
 *Comments(意见):
 *JDK Version
 *Author:谢洪章
 *Create Date:2015-1-19下午4:05:06
 * @author Administrator
 *
 */
public class CinemaRemarkServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cinema cinema = new Cinema();
		
	}

}
