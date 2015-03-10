/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台管理权限控制过滤器
 *  Project: moviebookticketsystem
 *  Packages: com.team3.mbts.filter 
 *  FileName: AdminAccessFilter.java
 *  Comments:
 *  JDK Version:
 *	@author 徐晓聪
 *  Create Date: 2015-1-28 下午12:19:54
 *  Modified By: 徐晓聪
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public class AdminAccessFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();
		if(!url.contains(".")&&!url.contains("CreateCodeServlet")&&!url.contains("AdminLoginServlet")
				|| url.contains(".jsp")&&!url.contains("CreateCodeServlet")&&!url.contains("AdminLoginServlet")) {//如果请求jsp或servlet,则查看是否已经登录
			HttpSession session = request.getSession(false);
			if(session == null) {
				response.sendRedirect("adminLogin.html");
				return;
			}
			String admin = (String) session.getAttribute("admin");
			if(admin == null) {//还未登录
				response.sendRedirect("adminLogin.html");
				return;
			}
			chain.doFilter(arg0, arg1);
		} else {		
			chain.doFilter(arg0, arg1);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
