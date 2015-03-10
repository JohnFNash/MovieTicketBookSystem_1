package com.team3.mbts.servlet.movie;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Movie;
import com.team3.mbts.entity.MovieRemark;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.MovieRemarkService;
import com.team3.mbts.service.UserInfoService;

public class WriteMovieRemarkServlet extends HttpServlet {	
	private static final long serialVersionUID = -6442608239950270143L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取当前登录的用户的信息
		String userName =  (String) request.getSession(false).getAttribute("username");
		String movieIdStr = request.getParameter("movieId");
		if(userName == null || movieIdStr==null) {//还未登录或参数错误
			return;
		}	
		
		//查询用户信息
		UserInfoService userService = new UserInfoService();
		UserInfo user = userService.getUserByAccount(userName);
		
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		float point = Float.parseFloat(request.getParameter("point"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");				
		
		MovieRemark movieRemark = new MovieRemark();
		if(!"".equals(title)) {
			movieRemark.setTitle(title);
		}
		movieRemark.setContent(formatRemarkContent(content));
		movieRemark.setTime(new Timestamp(System.currentTimeMillis()));
		Movie movie = new Movie();
		movie.setMovieId(movieId);
		movieRemark.setMovie(movie);
		movieRemark.setGrade(point);
		movieRemark.setUser(user);
		
		MovieRemarkService service = new MovieRemarkService();
		int insertFlag = service.insertMovieRemark(movieRemark);
		if(insertFlag > 0) {//评论发表成功
			response.sendRedirect("writeMovieRemarkSuccess.jsp?id="+movieRemark.getId());
			return;
		} else {//评论发表失败
			response.sendRedirect("writeMovieRemark.jsp?movieId="+movieId);
			return;
		}
	}

	//处理评论中的特殊字符	
	private String formatRemarkContent(String content) {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<content.length(); i++) {
			char ch = content.charAt(i); 
			switch(ch) {
				case '\"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&#39;");
					break;
				/*case '>':
					sb.append("&gt;");
					break;	
				case '<':
					sb.append("&lt;");
					break;*/
				default:
					sb.append(ch);
			}			
		}
		return sb.toString();
	}
	
}
