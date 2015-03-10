/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;


import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Seat;
import com.team3.mbts.entity.VideoHall;
import com.team3.mbts.service.SeatService;
import com.team3.mbts.service.VideoHallService;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.servlet.cinema
 * FileName:VideoHallServlet.java
 * Comments:影院影厅servlet
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-14 下午4:50:09
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class VideoHallServlet extends HttpServlet {
	private static final long serialVersionUID = -936706359288716352L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String op = request.getParameter("op");
			 cinema2 =  (Cinema) request.getSession().getAttribute("cinema");
			
			if("addHall".equals(op))
			{
				addHall(request,response);
			}
			else if("hallList".equals(op))
			{
				hallList(request,response);
			} else if("delete".equals(op)) {
				delete(request,response);
			}
			 else if("showSeat".equals(op)) {
				 showSeat(request,response);
			}else if("selectSeat".equals(op)) {
				selectSeat(request,response);
			} else  {
				addVideoHall(request,response);
			}
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 下午5:14:54
	 * Description:显示座位分布
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showSeat(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			int hallId = Integer.parseInt(request.getParameter("hallId"));
			VideoHall videoHall = videoHallService.selectById(hallId);
			List<Seat> seatList = seatService.selectSeat(hallId);
			/*System.out.println(seatList.get(0).getRow());
			System.out.println(seatList.size());
			System.out.println(videoHall.getRow());*/
			request.setAttribute("seatList", seatList);
			request.setAttribute("videoHall", videoHall);
			request.getRequestDispatcher("business_showSeat.jsp")
					.forward(request, response);		
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-26 下午5:14:54
	 * Description:显示座位分布
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addVideoHall(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String jsonString = request.getParameter("seat");
				
		JSONArray jsonarray = new JSONArray(jsonString);
		int use = jsonarray.length();
		
		byte row = Byte.parseByte(request.getParameter("row"));
		byte col = Byte.parseByte(request.getParameter("col"));
		byte no = Byte.parseByte(request.getParameter("no"));
		int seatCount = row*col-use;
		
		if(cinema2!=null) {		
			//存放座位列表
			List<Seat> seatList = new ArrayList<Seat>();		
			//遍历n*n座位 找出没座位的（row，col）
			for(int i=1;i<=row;i++) {
				for(int j=1;j<=col;j++) {
					boolean flag = true;//true可以添加此座位					
					for(int k=0;k<jsonarray.length();k++){
						int row1 = jsonarray.getJSONObject(k).getInt("row");
						int col1 = jsonarray.getJSONObject(k).getInt("col");
						if(i==row1 && j==col1) {
							flag = false;
							break;
						}
					}
					if(flag) {	//添加入座位列表						
						seatList.add(new Seat(0, (byte)i, (byte)j));
					}
				}
			}
			
			//构造放映厅
			VideoHall videoHall = new VideoHall(cinema2.getCinemaId(), seatCount, row, col, no);
			//插入放映厅及座位
			boolean insertFlag = videoHallService.insertHall(videoHall, seatList);			
			if(insertFlag) {//放映厅及座位插入成功
				request.getRequestDispatcher("business_addHallSuccess.jsp")
						.forward(request, response);
			}
			else {
				request.getRequestDispatcher("business_addVideoHall.jsp")
				.forward(request, response);
			}
			
		} else {
			request.getRequestDispatcher("business_addVideoHall.jsp")
			.forward(request, response);
		}
	}
	

	private void addHall(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("business_addVideoHall.jsp")
					.forward(request, response);
	}

	private void selectSeat(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		String no = request.getParameter("no");

		if(cinema2!=null) {
			int row = videoHallService.selectExist(cinema2.getCinemaId(),Integer.parseInt(no));					
			if(row==1) {
				out.write("id exist");
				out.flush();
				out.close();
			} else {
				out.write("not");
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 下午6:07:33
	 * Description:删除影厅
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int hallId = Integer.parseInt(request.getParameter("hallId"));
			videoHallService.deleteVideoHall(hallId);
			hallList(request, response);
			
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 下午6:06:56
	 * Description:加载影院影厅列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void hallList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			//int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
			//System.out.println(cinemaId);
			List<VideoHall> hallList = videoHallService.selectVideoHalls(cinema2.getCinemaId());
			request.setAttribute("hallList", hallList);
			request.getRequestDispatcher("business_screenings.jsp")
					.forward(request, response);
	}
	
	private VideoHallService videoHallService = new VideoHallService();
	private SeatService seatService = new SeatService();
	//private int cinemaId;
	private Cinema cinema2;
	
}
