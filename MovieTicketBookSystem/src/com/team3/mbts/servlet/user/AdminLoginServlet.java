/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.UserInfoService;
import com.team3.mbts.util.SqlSessionUtil;
/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.common
 *FileName:CreateCodeServlet.java
 *Comments
 *JDK Version
 *Author:XiongWei
 *Create Date:2015-01-13 下午1:23:34
 *Modified By:
 *Modified Time:
 *What is Modified:
 *Version:
 */
public class AdminLoginServlet extends HttpServlet {

	private Object seller;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}
	
	/**
	 *管理员 验证用户登录信息
	 *  @param userInfo 用户信息
	 *  @return 管理员登录验证通过，返回1；否则返回0
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//存放登录的管理信息
		HttpSession session = request.getSession();
		//获取验证码
		HttpSession httpSession = request.getSession();
		String	sesion = (String) httpSession.getAttribute("code");
		//获取用户输入验证
		String co =request.getParameter("code");
		if(!co.equals(sesion)){
			response.sendRedirect("adminLogin.html");
			session.setAttribute("seller", seller);
			return;
		}
		
		//获取管理员表单用户名密码
		String name = request.getParameter("account");
		String pwd = request.getParameter("password"); 
	    UserInfo userInfo = new UserInfo();
	    userInfo.setAccount(name);
	    userInfo.setPassword(pwd);
	    userInfo.setAdmin(true);
		//实例化UserInfoService
		UserInfoService userInfoService = new UserInfoService();
		int user = userInfoService.loginValidate(userInfo);		
		if(user==1){
			//验证通过
			response.sendRedirect("houtai.jsp");	
			session.setAttribute("admin", name);
		}//不通过
		else{
			response.sendRedirect("adminLogin.html");
			
		}
		
	}

}
