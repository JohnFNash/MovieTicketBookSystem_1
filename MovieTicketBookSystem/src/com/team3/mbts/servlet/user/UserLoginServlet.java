/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.UserInfoService;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = -2731629828290019097L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//获取用户输入的验证码
		String validateCode = request.getParameter("validateCode");
		//获取正确的验证码
		HttpSession session = request.getSession(false);
		String code = (String) session.getAttribute("code");
		if(!code.equals(validateCode)) {//验证码错误
			out.write("code");
			out.flush();
			out.close();
			return;
		} 
		
		UserInfoService service = new UserInfoService();
		//获取输入的用户名和密码
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount(userName);
		userInfo.setPassword(password);
		//验证用户登录
		int loginFlag = service.loginValidate(userInfo);
		if(loginFlag == 1) {//登录成功
			session.setAttribute("username", userName);//记录当前登录的用户的信息
			UserInfo user = service.getUserByAccount(userName);
			session.setAttribute("user", user);//记录当前登录的用户的信息
			session.setMaxInactiveInterval(800);
			out.write("success");
			out.flush();
			out.close();
		} else {//用户名或者密码错误，登录失败
			out.write("other");
			out.flush();
			out.close();
		}
	}

}
