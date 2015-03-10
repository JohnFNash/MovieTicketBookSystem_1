/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.team3.mbts.entity.Order;
import com.team3.mbts.entity.OrderTicket;
import com.team3.mbts.entity.Screenings;
import com.team3.mbts.entity.Ticket;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.OrderService;
import com.team3.mbts.service.ScreeningService;
import com.team3.mbts.service.UserInfoService;

public class SelectSeatServlet extends HttpServlet {
	private static final long serialVersionUID = -1953312213221136980L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取当前登录的用户的信息
		String userName =  (String) request.getSession(false).getAttribute("username");
		if(userName == null) {//还未登录
			return;
		}	
		
		//查询用户信息
		UserInfoService userService = new UserInfoService();
		UserInfo user = userService.getUserByAccount(userName);
		
		ScreeningService service = new ScreeningService();
		OrderService orderService = new OrderService();
		
		int screeningId = Integer.parseInt(request.getParameter("screeningId"));
		int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
		String jsonStr = request.getParameter("seat");
		JSONArray jsonarray = new JSONArray(jsonStr);
		List<Ticket> ticketList = new ArrayList<Ticket>();
		List<OrderTicket> orderTicketList = new ArrayList<OrderTicket>();
		for(int k=0;k<jsonarray.length();k++){
			int row1 = jsonarray.getJSONObject(k).getInt("row");
			int col1 = jsonarray.getJSONObject(k).getInt("col");			
			//构造Ticket对象
			Ticket ticket = new Ticket();
			ticket.setUserId(user.getUserId());
			ticket.setRow((byte) row1);
			ticket.setCol((byte) col1);
			ticket.setScreeningsId(screeningId);	
			ticketList.add(ticket);
			
			//构造OrderTicket对象
			OrderTicket orderTicket = new OrderTicket();
			orderTicketList.add(orderTicket);
		}
		
		//构造Order对象
		Order order = new Order();
		order.setCinemaId(cinemaId);
		Screenings screenings = service.getScreeningById(screeningId);
		order.setScreenings(screenings);			
		order.setUser(user);
		order.setTime(new Timestamp(System.currentTimeMillis()));
		
		//插入订单
		orderService.insertOrder(order, ticketList, orderTicketList);
		
		response.sendRedirect("personIndex.jsp");
	}

}
