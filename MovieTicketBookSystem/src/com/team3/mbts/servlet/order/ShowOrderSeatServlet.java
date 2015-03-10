package com.team3.mbts.servlet.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Order;
import com.team3.mbts.entity.Seat;
import com.team3.mbts.entity.VideoHall;
import com.team3.mbts.service.OrderService;
import com.team3.mbts.service.SeatService;

/**
 * 显示用户订单所选座位
 * @author JohnFNash
 *
 */
public class ShowOrderSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 8701220225000738430L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = new OrderService();
		SeatService seatService = new SeatService();
		
		//查找查询的订单
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		Order order = orderService.selectOrderById(orderId);
		//查询对应场次的放映厅的座位信息
		VideoHall videoHall = order.getScreenings().getVideoHall();
		int videoHallId = videoHall.getId();
		List<Seat> seatList = seatService.selectSeat(videoHallId);
		//查询订单中所选的座位
		List<Seat> seatSelected = seatService.selectSeatForOrder(orderId);
		
		//将数据放入request
		request.setAttribute("videoHall", videoHall);
		request.setAttribute("seatList", seatList);
		request.setAttribute("seatSelected", seatSelected);
		
		request.getRequestDispatcher("showOrderSeat.jsp").forward(request, response);
	}

}
