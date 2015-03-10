/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Seller;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.SellerService;
import com.team3.mbts.service.UserInfoService;
import com.team3.mbts.util.PageBean;

public class ShwoSellerServlet extends HttpServlet {

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
		//实例化SellerService
		SellerService sellerService = new SellerService();
		//获取总的数目
		int accestRecords =sellerService.countSeller();
		//分页包装类
		PageBean pageBean = 
				new PageBean(accestRecords, request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置商家用户列表
		List<Seller> sellerList=sellerService.selectSellerList(pageBean);
		pageBean.setDataList(sellerList);
		//查询的数据放入request中
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("Consult_lb.jsp").forward(request, response);

	
	}

}
