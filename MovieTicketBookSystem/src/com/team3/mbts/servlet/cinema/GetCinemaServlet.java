/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.CinemaRemark;
import com.team3.mbts.entity.Movie;
import com.team3.mbts.entity.Screenings;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.CinemaRemarkService;
import com.team3.mbts.service.CinemaService;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.service.ScreeningService;

public class GetCinemaServlet extends HttpServlet {
	private static final long serialVersionUID = -6325543352833829370L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cinemaIdStr = request.getParameter("cinemaId");
		if(cinemaIdStr != null && !cinemaIdStr.matches("[0-9]+")) {//传入的影院编号不合法
			response.sendRedirect("LoadCinemasServlet");
			return;
		}
		
		String op = request.getParameter("op");
		if("switchMovie".equals(op)) {//切换影片
			switchMovie(request, response, cinemaIdStr);
		} else if("loadRemarks".equals(op)) {//加载评论
			loadRemarks(request, response, cinemaIdStr);
		} else {
			normalHandling(request, cinemaIdStr);
			request.getRequestDispatcher("cinema.jsp").forward(request, response);			
		}	
	}

	//加载影影院评论
	private void loadRemarks(HttpServletRequest request,
			HttpServletResponse response, String cinemaIdStr) throws IOException {						
		int cinemaId = Integer.parseInt(cinemaIdStr);
		//获取之前已经加载的评论数目
		int prevRecords = 0;
		try {
			prevRecords = Integer.parseInt(request.getParameter("prevRecords"));
		} catch(NumberFormatException e) {
			prevRecords = 0;
		}
		//加载5条评论
		CinemaRemarkService service = new CinemaRemarkService();
		List<CinemaRemark> cinemaRemarks = service.selectCinemaRemarks(cinemaId, 5, prevRecords);
		//将评论列表转换为json数组，并写入输出流
		String remarksStr = remarkListToJson(cinemaRemarks);
		PrintWriter out = response.getWriter();
		out.write(remarksStr);
		out.flush();
		out.close();
	}

	//影片切换之后随之更新排期信息
	private void switchMovie(HttpServletRequest request, HttpServletResponse response, String cinemaIdStr) throws IOException {
		//当前时间
		String dayStr = request.getParameter("day");
		Date dateTime;
		if(dayStr == null || "".equals(dayStr)) {
			dateTime = new Date(System.currentTimeMillis());
			dayStr = new SimpleDateFormat("yyyy-MM-dd").format(dateTime);
		} else {
			dateTime = Date.valueOf(dayStr);
		}
		request.setAttribute("daySelected", dateTime.toString());
		request.setAttribute("datStr", dayStr);
		
		ScreeningService screeningService = new ScreeningService();
	
		String movieIdStr = request.getParameter("movieId");
		if(movieIdStr!=null && movieIdStr.matches("[0-9]+")) {
			int movieId = Integer.parseInt(movieIdStr);
			int cinemaId = Integer.parseInt(cinemaIdStr);
			//查询选定影片在该影院当日所有的排期
			List<Screenings> sList = screeningService.selectScreeningsForMovie(cinemaId, movieId, dayStr);
			PrintWriter out = response.getWriter();
			String screeningsStr = screeningsListToJson(sList);
			out.write(screeningsStr);
			out.flush();
			out.close();
		}				
	}

	private void normalHandling(HttpServletRequest request, String cinemaIdStr) {
		CinemaService service = new CinemaService();
		int cinemaId = Integer.parseInt(cinemaIdStr);
		Cinema cinema = service.selectCinemaById(Integer.parseInt(cinemaIdStr));
		request.setAttribute("cinema", cinema);
						
		generateFilterHead(request);	//生成日期筛选列表
		
		//当前时间
		String dayStr = request.getParameter("day");
		Date dateTime;
		if(dayStr == null || "".equals(dayStr)) {
			dateTime = new Date(System.currentTimeMillis());
			dayStr = new SimpleDateFormat("yyyy-MM-dd").format(dateTime);
		} else {
			dateTime = Date.valueOf(dayStr);
		}
		request.setAttribute("daySelected", dateTime.toString());
		request.setAttribute("datStr", dayStr);
		
		ScreeningService screeningService = new ScreeningService();
		MovieService movieService = new MovieService();
				
		//查询该影院当天放映的影片列表
		List<Movie> movieList = movieService.getCinemaHotestMoviesByPage(cinemaId, dayStr);
		request.setAttribute("movieList", movieList);		
		
		if(movieList.size() > 0) {
			int movieId = movieList.get(0).getMovieId();
			//查询选定影片在该影院当日所有的排期
			List<Screenings> sList = screeningService.selectScreeningsForMovie(cinemaId, movieId, dayStr);
			request.setAttribute("sList", sList);
			
			request.setAttribute("movieIdSelected", movieList.get(0).getMovieId());//设置默认选中影片
		}		
	}

	//生成日期筛选列表
	private void generateFilterHead(HttpServletRequest request) {
		List<Date> dateList = new ArrayList<Date>();			
		Timestamp now = new Timestamp(System.currentTimeMillis());
		long time = now.getTime();
		//获得当前时间并延后五天的时间 例如：2015-01-19 星期一 2015-01-20 星期二 2015-01-21 星期三……
		for(int i=0;i<=4;i++) {
			long time_i = time+3600*24*i*1000l;
			Date t_i =  new Date(time_i);
			dateList.add(t_i);
		}
		request.setAttribute("weekdays", Arrays.asList("周日", "周一", "周二", "周三", "周四", "周五", "周六"));
		request.setAttribute("dateList", dateList);		
	}

	/**
	 * 将场次列表转化为json数组
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午4:24:12
	 *  Description:
	 *  @return
	 */
	private String screeningsListToJson(List<Screenings> sList) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(Screenings screening: sList) {
			Movie movie = screening.getMovie();
			
			sb.append("{");
			sb.append("\"screeningId\":" + screening.getId() + ",");
			sb.append("\"startTime\":\"" + screening.getStartTime().toString().substring(10,16) + "\",");
			Timestamp end = new Timestamp(screening.getStartTime().getTime() + movie.getDuration()*60*1000);
			String endTime = end.toString().substring(11, 16);
			sb.append("\"endTime\":\"" + endTime + "\",");
			sb.append("\"language\":\"" + movie.getLanguage() + "\",");
			sb.append("\"is3D\":\"" + (movie.isIs3D() ? "3D" : "2D") + "\",");
			sb.append("\"videoHallNo\":" +screening.getVideoHall().getNo() + ",");
			sb.append("\"price\":" + screening.getPrice() + ",");
			sb.append("\"originalPrice\":" + screening.getOriginalPrice());			
			sb.append("},");
		}
		sb.append("]");
		if(sb.length() > 2) {//如果添加过影片
			sb = sb.replace(sb.length()-2, sb.length()-1, "");
		}				
		return sb.toString();
	}

	/**
	 * 将影院评价列表转化为json数组
	 * @param remarkList 影院评价列表
	 * @return
	 */
	private String remarkListToJson(List<CinemaRemark> remarkList) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(CinemaRemark remark : remarkList) {
			UserInfo user = remark.getUser();
			
			sb.append("{");
			sb.append("\"remarkId\":" + remark.getId() + ",");
			sb.append("\"userId\":" + user.getUserId() + ",");
			sb.append("\"userName\":\"" + user.getAccount() + "\",");
			sb.append("\"headPath\":\"" + user.getHeadPath() + "\",");
			sb.append("\"content\":\"" + remark.getContent() + "\",");
			sb.append("\"time\":\"" + remark.getTime().toString().substring(0, 19) + "\",");
			sb.append("\"likeCount\":" + remark.getLikeCount());			
			sb.append("},");
		}
		sb.append("]");
		if(sb.length() > 2) {//如果添加过评论
			sb = sb.replace(sb.length()-2, sb.length()-1, "");
		}				
		return sb.toString();		
	}
}
