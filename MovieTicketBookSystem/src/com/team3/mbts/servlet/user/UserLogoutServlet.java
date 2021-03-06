package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 318910419814948715L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();	//用户注销
		}
		PrintWriter out = response.getWriter();
		out.write("ok");
		out.flush();
		out.close();
	}

}
