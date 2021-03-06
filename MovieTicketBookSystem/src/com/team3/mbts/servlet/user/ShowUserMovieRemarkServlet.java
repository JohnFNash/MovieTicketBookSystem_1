/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.mbts.entity.MovieRemark;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.MovieRemarkService;
import com.team3.mbts.service.UserInfoService;
import com.team3.mbts.util.PageBean;

/**
 * 
 *  Project: moviebookticketsystem
 *  Packages: com.team3.mbts.servlet.user 
 *  FileName: ShowUserInfoServlet.java
 *  Comments:
 *  JDK Version:
 *	@author 徐晓聪
 *  Create Date: 2015-2-17 上午9:13:49
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public class ShowUserMovieRemarkServlet extends HttpServlet {
	private static final long serialVersionUID = -5085896742027463905L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取当前登录的用户的信息
		HttpSession session = request.getSession(false);
		if(session == null) {
			return;
		}
		String userName =  (String)session.getAttribute("username");
		if(userName == null) {//还未登录
			return;
		}	
		
		//查询用户信息
		UserInfoService userService = new UserInfoService();
		UserInfo user = userService.getUserByAccount(userName);
		
		//查询用户的影评
		MovieRemarkService service = new MovieRemarkService();
		//获取用户的影评数目总数
		int totalRecords = service.selectMovieRemarksForUserCount(user.getUserId());
		//设置分页包装类
		final int remarkNum = 5;	//默认的加载的订单的数目
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? 
						remarkNum + "" : request.getParameter("pageSize"), request.getParameter("curPage"));
		List<MovieRemark> remarkList = 
			service.getMoiveRemarksForUserByPage(user.getUserId(), pageBean.getCurPage(), pageBean.getPageSize());
		pageBean.setDataList(remarkList);
		//将pageBean放入request
		request.setAttribute("pageBean", pageBean);		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("userMovieRemark.jsp").forward(request, response);
	}

}
