/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.mbts.entity.Order;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.OrderService;
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
 *  Create Date: 2015-1-27 下午6:11:49
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public class ShowUserOrderServlet extends HttpServlet {
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
		
		//查询用户的订单
		OrderService orderService = new OrderService();
		//获取用户的订单总数
		int totalRecords = orderService.countOrdersForUser(user.getUserId());
		//设置分页包装类
		final int orderNum = 5;	//默认的加载的订单的数目
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? 
						orderNum + "" : request.getParameter("pageSize"), request.getParameter("curPage"));
		List<Order> orderList = 
			orderService.selectOrdersForUser(user.getUserId(), pageBean.getCurPage(), pageBean.getPageSize());
		pageBean.setDataList(orderList);
		//将pageBean放入request
		request.setAttribute("pageBean", pageBean);		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("userOrder.jsp").forward(request, response);
	}

}
