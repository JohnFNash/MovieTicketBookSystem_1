package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.UserInfoService;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = -6778072688823915080L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//验证用户的验证码是否输入正确
		String validateCode = request.getParameter("validateCode");
		String correctCode = (String) request.getSession(false).getAttribute("code");
		if(!correctCode.equals(validateCode)) {//用户输入的验证码不正确
			out.write("code");
			out.flush();
			out.close();
			return;
		}
						
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		//判断用户名是否已经存在
		UserInfoService service = new UserInfoService();
		int isUserNameExists = service.isUserNameExists(userName);
		if(isUserNameExists > 0) {//用户名已经存在
			out.write("exists");
			out.flush();
			out.close();
			return;
		}
		//常见UserInfo对象，并保存到数据库
		UserInfo userInfo = new UserInfo();
		userInfo.setAccount(userName);
		userInfo.setPassword(password);
		int registerFlag = service.register(userInfo);//注册
		if(registerFlag == 1) {//注册成功
			out.write("success");
			out.flush();
			out.close();
			return;
		} else {//注册失败
			out.write("error");
			out.flush();
			out.close();
			return;
		}		
	}

}
