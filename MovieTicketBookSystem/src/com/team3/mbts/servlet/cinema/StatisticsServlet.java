/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.CinemaRemark;
import com.team3.mbts.entity.Movie;
import com.team3.mbts.entity.Order;
import com.team3.mbts.service.CinemaRemarkService;
import com.team3.mbts.service.OrderService;
import com.team3.mbts.util.PageBean;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.servlet.cinema
 * FileName:StatisticsServlet.java
 * Comments:统计信息Servlet
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-23 上午9:36:14
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class StatisticsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String op = request.getParameter("op");
			HttpSession session = request.getSession();
			cinema = (Cinema) session.getAttribute("cinema");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
 			//获取当前时间  	2015-1-01-23 10:55:00
			java.util.Date dateTime = new java.util.Date(); 
			timeString = sdf.format(dateTime); //当前时间  String
			
			//当前年开始  2015-01-01 String 
			timeString1 = sdf1.format(dateTime)+"-01-01 00:00:00";
			java.util.Date dateTime3 = null;
			try {
				dateTime3 = sdf.parse(timeString1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long time2 = dateTime3.getTime();
			
			//当前时间毫秒值
			long time = dateTime.getTime();
			
			//获得当前时间近一周
			long tlong1 = time-3600*24*7*1000l;
			java.util.Date dTime1 = new java.util.Date(tlong1);
			tString1 = sdf.format(dTime1); //当前时间近一周的时间  String  2015-01-16 
			
			//获得当前时间近一月
			long tlong2 = time-3600*24*30*1000l;
			java.util.Date dTime2 = new java.util.Date(tlong2);
			tString2 = sdf.format(dTime2); //当前时间近一个月的时间  String  2014-12-23 
			
			//获得当前时间近三个月的时间
			long time1 = time-3600*24*30*3*1000l;
			java.util.Date dateTime4 = new java.util.Date(time1);
			timeString2 = sdf.format(dateTime4); //当前时间近三个月的时间  String  2014-10-25
			
			//获得当前时间近一年
			long tlong3 = time-3600*24*30*12*1000l;
			java.util.Date dTime3 = new java.util.Date(tlong3);
			tString3 = sdf.format(dTime3); //当前时间近一年的时间  String  2014-01-23 
			
			
			//获得当前年份内 例2015年内
			
			
			//获得从去年内 2014 内
			long time3 = time2-3600*24*30*12*1000l;
			java.util.Date dateTime5 = new java.util.Date(time3);
		    timeString3 = sdf1.format(dateTime5)+"-01-01 00:00:00"; //2014-01-01 00:00:00 String 
			
			//获得从去年内 2013 内
			long time4 = time2-3600*24*30*24*1000l;
			java.util.Date dateTime6 = new java.util.Date(time4);
			 timeString4 = sdf1.format(dateTime6)+"-01-01 00:00:00"; //2014-01-01 00:00:00 String 
			
			/*System.out.println(timeString);
			System.out.println(timeString1);
			System.out.println(timeString2);
			System.out.println(timeString3);
			System.out.println(timeString4);*/
			
			if("ticket".equals(op))
			{
				ticket(request,response);
			}
			else if ("order".equals(op)) {
				order(request,response);
			}
			else if ("evaluate".equals(op)) {
				evaluate(request,response);
			}
			else if ("orderSearch".equals(op)) {
				orderSearch(request,response);
			}
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 下午5:13:24
	 * Description:按订单号、影片名模糊查询订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void orderSearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		int orderId =-1;
		try {
			//解析成功 表示输入的是订单号
			orderId =Integer.parseInt(key);
			Order order = orderService.selectOrderById(orderId);
			if(order!=null)
			{
				List<Order> orderList = new ArrayList<Order>();
				orderList.add(order);
				request.setAttribute("orderList", orderList);
				
			}
			request.setAttribute("key", key);
			request.getRequestDispatcher("business_indentList.jsp")
					.forward(request, response);
			return;
			
		} catch (Exception e) {
				
		}
		//解析不成功 表示输入的是影片名
		/*String value = request.getParameter("value");*/
		if(cinema!=null)
		{
		/*	 if ("2".equals(value)) {
				 List<Order> oList = orderService.selectOrListByMKey(key, tString2, timeString1);
				 request.setAttribute("orderList", oList);
			 }
			else if ("3".equals(value)) {
				List<Order> oList = orderService.selectOrListByMKey(key, timeString2, timeString1);
				request.setAttribute("orderList", oList);
			}
			else if ("4".equals(value)) {
				List<Order> oList = orderService.selectOrListByMKey(key, tString3, timeString1);
				request.setAttribute("orderList", oList);
			}
			else {*/
			 	List<Order> oList = orderService.selectOrListByMKey(key,cinema.getCinemaId(), tString3,timeString1);
			 	request.setAttribute("orderList", oList);
			 	
			/*}
			 
			request.setAttribute("value", value);*/
			request.setAttribute("key", key);
			request.getRequestDispatcher("business_indentList.jsp")
				.forward(request, response);			 
		}
		
	}


	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 上午9:38:49
	 * Description:影院评价列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void evaluate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			String value = request.getParameter("value");
			if(cinema!=null)
			{
				
		
				 if ("2".equals(value)) {
					int pageCount = cinemaRemarkService.selectCount(cinema.getCinemaId(), timeString1, timeString);
					//分页包装类
					PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
					List<CinemaRemark> crList = cinemaRemarkService.selectCinemaRemarks(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), timeString1, timeString);
					pageBean.setDataList(crList);
					
					request.setAttribute("pageBean", pageBean);
				}
				else if ("3".equals(value)) {
					int pageCount = cinemaRemarkService.selectCount(cinema.getCinemaId(), timeString3, timeString);
					//分页包装类
					PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
					List<CinemaRemark> crList = cinemaRemarkService.selectCinemaRemarks(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), timeString3, timeString);
					pageBean.setDataList(crList);
					
					request.setAttribute("pageBean", pageBean);
				}
				else if ("4".equals(value)) {
					int pageCount = cinemaRemarkService.selectCount(cinema.getCinemaId(), timeString4, timeString3);
					//分页包装类
					PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
					List<CinemaRemark> crList = cinemaRemarkService.selectCinemaRemarks(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), timeString4, timeString3);
					pageBean.setDataList(crList);
					
					request.setAttribute("pageBean", pageBean);
				}
				else
				{
					int pageCount = cinemaRemarkService.selectCount(cinema.getCinemaId(), timeString2, timeString);
					//分页包装类
					PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
					List<CinemaRemark> crList = cinemaRemarkService.selectCinemaRemarks(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), timeString2, timeString);
					pageBean.setDataList(crList);
					
					request.setAttribute("pageBean", pageBean);
				}
				
			}
			request.setAttribute("value", value);
			request.getRequestDispatcher("business_evaluate.jsp")
					.forward(request, response);
		
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 上午9:39:15
	 * Description: 影院订单列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String value = request.getParameter("value");
		if(cinema!=null)
		{
			
	
			 if ("2".equals(value)) {
				 int pageCount = orderService.orderCount(cinema.getCinemaId(), tString2, timeString);
					//分页包装类
					PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
					List<Order> orderList = orderService.selectOrderList(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), tString2, timeString);
					pageBean.setDataList(orderList);
					
					request.setAttribute("pageBean", pageBean);
			}
			else if ("3".equals(value)) {
				int pageCount = orderService.orderCount(cinema.getCinemaId(), timeString2, timeString);
				//分页包装类
				PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
				List<Order> orderList = orderService.selectOrderList(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), timeString2, timeString);
				pageBean.setDataList(orderList);
				
				request.setAttribute("pageBean", pageBean);
			}
			else if ("4".equals(value)) {
				int pageCount = orderService.orderCount(cinema.getCinemaId(), tString3, timeString);
				//分页包装类
				PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
				List<Order> orderList = orderService.selectOrderList(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), tString3, timeString);
				pageBean.setDataList(orderList);
				
				request.setAttribute("pageBean", pageBean);
			}
			else
			{
				int pageCount = orderService.orderCount(cinema.getCinemaId(), tString1, timeString);
				//分页包装类
				PageBean pageBean = new PageBean(pageCount, request.getParameter("pageSize"),request.getParameter("curPage"));
				List<Order> orderList = orderService.selectOrderList(pageBean.getPageSize(),pageBean.getCurPage(), cinema.getCinemaId(), tString1, timeString);
				pageBean.setDataList(orderList);
				
				request.setAttribute("pageBean", pageBean);
			}
			
		}
		
		request.setAttribute("value", value);
		request.getRequestDispatcher("business_indentList.jsp")
			.forward(request, response);
			
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 上午9:39:40
	 * Description: 影院售票统计
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void ticket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("business_sptj.jsp")
				.forward(request, response);
	}
	private CinemaRemarkService cinemaRemarkService = new CinemaRemarkService();
	private OrderService orderService = new OrderService();
	private Cinema cinema;
	private String timeString;
	private String timeString1;
	private String timeString2;
	private String timeString3;
	private String timeString4;
	
	private String tString1;
	private String tString2;
	private String tString3;
}
