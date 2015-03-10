package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.CinemaRemark;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.CinemaRemarkService;
import com.team3.mbts.service.UserInfoService;

public class WriteCinemaRemarkServlet extends HttpServlet {
	private static final long serialVersionUID = -3312979264677628222L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取当前登录的用户的信息
		String userName =  (String) request.getSession(false).getAttribute("username");
		String cinemaIdStr = request.getParameter("cinemaId");
		if(userName == null || cinemaIdStr==null) {//还未登录或参数错误
			return;
		}
		
		int cinemaId = Integer.parseInt(cinemaIdStr);
		float score = 7.0f;
		try {
			score = Float.parseFloat(request.getParameter("score"));
		} catch(NumberFormatException e) {			
		}				
		String remarkContent = request.getParameter("remarkContent");
		
		//查询用户信息
		UserInfoService userService = new UserInfoService();
		UserInfo user = userService.getUserByAccount(userName);
		
		CinemaRemark remark = new CinemaRemark();
		remark.setUser(user);
		Cinema cinema = new Cinema();
		cinema.setCinemaId(cinemaId);
		remark.setCinema(cinema);
		remark.setContent(remarkContent);
		remark.setGrade(score);
		remark.setTime(new Timestamp(System.currentTimeMillis()));
		
		//插入影院评论
		CinemaRemarkService service = new CinemaRemarkService();
		int insertFlag = service.insertCinemaRemark(remark);
		PrintWriter out = response.getWriter();
		if(insertFlag == 1) {//插入成功
			//out.write(getCinemaRemarkJson(remark));
			out.write("success");
		} else {
			out.write("fail");
		}
		out.flush();
		out.close();
	}
	
}
