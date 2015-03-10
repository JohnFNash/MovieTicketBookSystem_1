/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.service.CinemaRemarkService;

/**
 * 影院评论审核Servlet
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.cinema
 *FileName:CinemaRemarkCirtic.java
 *Comments(意见):
 *JDK Version
 *Author:徐晓聪
 *Create Date:2015-3-2 下午3:36:40
 * @author Administrator
 *
 */
public class CinemaRemarkCirticServlet extends HttpServlet {
	private static final long serialVersionUID = 5471491689180452012L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取传入的参数
		int cinemaRemarkId = Integer.parseInt(request.getParameter("id"));
		
		byte type = 3;	//2表示审核通过，3表示审核未通过
		String typeStr = request.getParameter("type");
		try {
			type = Byte.parseByte(typeStr);
		} catch (NumberFormatException e) {
		}
		
		//审核评论
		CinemaRemarkService cinemaRemarkService = new CinemaRemarkService();
		int flag = cinemaRemarkService.updateCinemaRemark(cinemaRemarkId, type);
		
		PrintWriter out = response.getWriter();
		if(flag == 1) {//审核成功
			out.write("success");
		} else {
			out.write("fail");
		}
		out.flush();
		out.close();
	}

}
