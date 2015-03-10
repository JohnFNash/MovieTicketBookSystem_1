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

/**
 * 请求响应编码设置过滤器<br>
 *  Project: moviebookticketsystem<br>
 *  Packages: com.team3.mbts.servlet.common<br> 
 *  FileName: EncodingFilter.java<br>
 *  Comments:
 *  JDK Version:
 *	@author Administrator
 *  Create Date: 2015-1-13 上午11:42:40
 *  Modified By: Administrator
 *  Modified Time: 
 *  What is modified:
 *  Version:
 */
public class EncodingFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");		
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
