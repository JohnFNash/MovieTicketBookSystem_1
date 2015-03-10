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

import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.UserInfoService;

public class UpdatePwdServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}


	/**
	 * 管理员密码修改
	 *  @param userInfo 用户信息
	 *  @return 密码更新成功返回1；否则返回0
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		//获取修改密码表单值
		String id= request.getParameter("loginid");
		String pwd = request.getParameter("loginpwd");
		String pwd1 = request.getParameter("loginpwd1");
		if(!pwd.equals(pwd1)){
			response.sendRedirect("admin_mmxg.html");
			return;
		}
		
		UserInfo userInfo = new UserInfo();
		//获取当前登录管理员账号
		userInfo.setAccount((String) request.getSession().getAttribute("admin"));
		userInfo.setPassword(id);
		userInfo.setAdmin(true);
		//实例化UserInfoService
		UserInfoService userInfoService = new UserInfoService();
		int use=userInfoService.loginValidate(userInfo);
		if(use==1){//原密码输入正确			
			userInfo.setPassword(pwd);			
			int up= userInfoService.update(userInfo);
			if(up==1){//密码修改成功
				response.sendRedirect("index.html");
			}else{
				response.sendRedirect("admin_mmxg.html");
			}
		}else{
			response.sendRedirect("admin_mmxg.html");
		}
	
	}

}
