/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Screenings;
import com.team3.mbts.entity.Seat;
import com.team3.mbts.entity.Ticket;
import com.team3.mbts.entity.VideoHall;
import com.team3.mbts.service.CinemaService;
import com.team3.mbts.service.ScreeningService;
import com.team3.mbts.service.SeatService;
import com.team3.mbts.service.TicketService;

public class BeforeSelectSeatServlet extends HttpServlet {
	private static final long serialVersionUID = -2447356307847835623L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int screeningId = Integer.parseInt(request.getParameter("screeningId"));
		
		ScreeningService service = new ScreeningService();				
		SeatService seatService = new SeatService();
		TicketService ticketService = new TicketService();
		
		//读取场次信息
		Screenings screening = service.getScreeningById(screeningId);
		//读取放映厅信息		
		VideoHall videoHall = screening.getVideoHall();
		int videoHallId = videoHall.getId();
		//读取影院信息
		int cinemaId = videoHall.getCinema().getCinemaId(); 
				//videoHall.getCinemaId();
		CinemaService cinemaService = new CinemaService();
		Cinema cinema = cinemaService.selectCinemaById(cinemaId);
		//读取座位布置信息
		List<Seat> seatList = seatService.selectSeat(videoHallId);
		//读取已选的座位信息
		List<Ticket> ticketList = ticketService.loadTicketsForScreening(screeningId);
		
		request.setAttribute("cinema", cinema);
		request.setAttribute("screening", screening);
		request.setAttribute("seatList", seatList);
		request.setAttribute("ticketList", ticketList);
		
		request.getRequestDispatcher("selectSeat.jsp").forward(request, response);
	}

}
