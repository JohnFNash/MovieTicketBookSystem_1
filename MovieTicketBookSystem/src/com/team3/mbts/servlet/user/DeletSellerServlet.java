/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.service.SellerService;
import com.team3.mbts.service.UserInfoService;

public class DeletSellerServlet extends HttpServlet {
	private SellerService sellerService=new  SellerService();
	private int sellerId;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//获取商家ID
				int sellerId =Integer.parseInt(request.getParameter("sellerId"));
				int remove = sellerService.removeSeller(sellerId);
				 if(remove ==1){//删除成功返回1，否者返回0
					 response.sendRedirect("ShwoSellerServlet");
				 }
				 else{
					 response.sendRedirect("ShwoSellerServlet");
				 }
				
				
			}

	}


