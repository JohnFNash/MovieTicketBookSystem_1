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

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.team3.mbts.service.UserInfoService;

public class DeletUserServlet extends HttpServlet {

	private UserInfoService userInfoService=new UserInfoService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

		
	}
	/**
	 *Create Time:2015-1-15 下午5:15:51
	 *Description:删除用户
	 *@param userId 用户的编号
	 *@return 删除成功返回1，否者返回0
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		//获取用户ID
		int userId =Integer.parseInt(request.getParameter("userId"));
		int remove = userInfoService.removeUser(userId);
		 if(remove ==1){//删除成功返回1，否者返回0
			 response.sendRedirect("ShwoUserServlet");
		 }
		 else{
			 response.sendRedirect("ShwoUserServlet");
		 }
		
		
	}

}
