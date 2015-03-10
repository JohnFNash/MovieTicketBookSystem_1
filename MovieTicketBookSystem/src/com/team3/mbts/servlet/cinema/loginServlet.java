/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.servlet.cinema;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Seller;
import com.team3.mbts.service.CinemaService;
import com.team3.mbts.service.SellerService;
/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.servlet.cinema
 * FileName:loginServlet.java
 * Comments:	登陆、注册Servlet
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-13 下午1:03:42
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 4477823760650067039L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			session = request.getSession();
			session.setMaxInactiveInterval(1200);
			String op = request.getParameter("op");
			if("login".equals(op))
			{
				login(request,response);
			}
			
			else if("reg".equals(op))
			{
				reg(request,response);
			}
			else if("exit".equals(op))
			{
				exit(request,response);
			}
	}
	
	private void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
			session.invalidate();
			response.sendRedirect("business_login.html");
	}

	private void reg(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String username = request.getParameter("username");
			String pwd = request.getParameter("password");
			String pwdAgain = request.getParameter("pwdAgain");
			String code = (String) session.getAttribute("code");
			String code1 = request.getParameter("code");
			
			Seller seller = sellerService.selectSellerByAccount(username);
			if(seller!=null) {//该商家用户名已经存在，不能再次添加
				response.sendRedirect("business_register.jsp");
				return;
			}
			
			if(pwd.equals(pwdAgain) && code.equals(code1)) {//注册信息输入合法
				int row = sellerService.insertSeller(new Seller(username, pwd));				
				if(row==1) {
					//seller2 = sellerService.selectSellerByAccount(username);
					response.sendRedirect("business_addInformation.jsp");					
					return;
				} else {
					response.sendRedirect("business_register.jsp");
					return;
				}				
			}else {
				response.sendRedirect("business_register.jsp");
				return;
			}
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-13 下午1:04:24
	 * Description:	登陆验证
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				
				String account = request.getParameter("name");
				String pwd = request.getParameter("pwd");
				String code1 = request.getParameter("code");
				String code = (String) session.getAttribute("code");
				int row = sellerService.selectSeller(account, pwd);
			
				if(row==1&&code1.equals(code)) 
				{
					Seller seller = sellerService.selectSellerByAccount(account);
					if(seller!=null)
					{
						//查询该商家旗下的第一家影院
						Cinema cinema = cinemaService.getFirstCinemaForSeller(seller.getSellerId());
						if(cinema!=null)
						{
							session.setAttribute("cinema", cinema);
						}
						
					}
					session.setAttribute("seller", seller);
					//进行内部跳转
					request.getRequestDispatcher("business_frame.jsp")
							.forward(request, response);
				}
				else {
					response.sendRedirect("business_login.html");
				}
				
	}
	
	private SellerService sellerService = new SellerService();
	private CinemaService cinemaService = new CinemaService();
	private	HttpSession session;
//	private Seller seller2;
}
