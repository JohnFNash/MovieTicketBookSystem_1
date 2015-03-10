/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.servlet.cinema;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Movie;
import com.team3.mbts.entity.Screenings;
import com.team3.mbts.entity.VideoHall;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.service.ScreeningService;
import com.team3.mbts.service.VideoHallService;

public class ScreeningServlet extends HttpServlet {
	private static final long serialVersionUID = -5568117005852241332L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		session.setMaxInactiveInterval(60*24*7);
		
		 cinema =  (Cinema) session.getAttribute("cinema");
		//得到当前时间 并减一个月的时间 作为筛选影片的过期条件
		 
		Date dateTime = new Date(System.currentTimeMillis()); //当前时间
		long time = dateTime.getTime();
		long time1 = time-3600*24*30*1000l; //当前时间减去一个月时间 作为过期时间
		long time5 = time+3600*24*6*1000l; //当前时间加6天（最高排期时间） 作为筛选影片排期时间
		Date time2 = new Date(time1) ;
		Date time6 = new Date(time5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateTime1 = sdf.format(time2);
		dateTime2 = sdf.format(time6);
		String op = request.getParameter("op");
		if("listing".equals(op)){
			listing(request, response);
		}
		else if("addListing".equals(op))
		{
			
			addListing(request, response);
		}
		else if("screening".equals(op))
		{
			Screening(request, response);
		}
		else if("delete".equals(op))
		{
			delete(request, response);
		}
		else if("validate".equals(op))
		{
			try {
				validate(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午1:06:48
	 * Description:删除排期
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			screeningService.deleteScreening(id);
			listing(request, response);
			
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-21 上午11:50:33
	 * Description:添加排期时间冲突验证
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	private void validate(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			PrintWriter out  = response.getWriter();
			//获得需要验证数据
			int movieId = Integer.parseInt(request.getParameter("movieId"));
			
			int hallId = Integer.parseInt(request.getParameter("hallId"));
			String date  = request.getParameter("date");
			String hour = request.getParameter("hour");
			String minute = request .getParameter("minute");
			//获得我们添加排期场次的时间
			String dateTime = date+" "+hour+":"+minute+":00";
			//System.out.println(dateTime);
			java.util.Date date2 = sdf.parse(dateTime);
			java.util.Date nowDate = new java.util.Date();
			//不能对当前时间以前的时间排期
			if(date2.getTime()<nowDate.getTime())
			{
				out.write("不能添加过期时间!");
				out.flush();
				out.close();
				return;
			}
			
			//查询当前添加排期影片的时长，并计算预计结束时间
			Movie movie = movieService.getMovieById(movieId);
			//不能对还未上映的影片选择比其上映时间小的影片排期
			if(movie.getPublish().getTime()>date2.getTime())
			{
				out.write("影评还未上映!");
				out.flush();
				out.close();
				return;
			}
			
			//System.out.println(movie.getName()+movie.getDuration());
			
			java.util.Date date3 = new java.util.Date((date2.getTime()+(movie.getDuration()+20)*60*1000l));
			
			//String dateTime2 = sdf.format(date3); 
			//System.out.println(dateTime2);
			
			List<Screenings> screeningPre = screeningService.selectPre(hallId, dateTime);
			//System.out.println(screeningPre.get(screeningPre.size()-1).getStartTime());
			
			List<Screenings> screeningNext = screeningService.selectNext(hallId, dateTime);
			//System.out.println(screeningNext.get(0).getStartTime());
			
			if(screeningPre.size()!=0 && screeningNext.size()!=0)
			{
				//前后都没有空
				//该厅上一场结束时间 转 为毫秒值比较
				long timePre = screeningPre.get(0).getStartTime().getTime();
				Movie movie2 = movieService.getMovieById(screeningPre.get(0).getMovie().getMovieId());
				java.util.Date movieEndTime = new java.util.Date(timePre+(movie2.getDuration()+20)*60*1000l);
				//该厅下一场开始时间 转 为毫秒值比较
				long timeNext = screeningNext.get(0).getStartTime().getTime();
				//System.out.println(screeningNext.get(0).getStartTime());
				//System.out.println(screeningPre.get(0).getStartTime());
				if(date2.getTime()<=movieEndTime.getTime() || date3.getTime()>=timeNext)
				{
					out.write("时间冲突!");
				}
			}
			else if(screeningPre.size()==0&&screeningNext.size()!=0){
				//前有空
				//该厅下一场开始时间 转 为毫秒值比较
				long timeNext = screeningNext.get(0).getStartTime().getTime();
				System.out.println(screeningNext.get(0).getStartTime());
				
				if(date3.getTime()>=timeNext)
				{
					out.write("时间冲突!");
				}
			}
			else if (screeningNext.size()==0&&screeningPre.size()!=0) {
				//后有空
				//该厅上一场开始时间 转 为毫秒值比较
				long timePre = screeningPre.get(0).getStartTime().getTime();
				//当前影厅播放电影的结束时间
				Movie movie2 = movieService.getMovieById(screeningPre.get(0).getMovie().getMovieId());
				java.util.Date movieEndTime = new java.util.Date(timePre+(movie2.getDuration()+20)*60*1000l);
				//System.out.println("当前影厅播放电影的结束时间"+movieEndTime);
				//System.out.println(screeningPre.get(0).getStartTime());
				if(date2.getTime()<=movieEndTime.getTime())
				{
					out.write("时间冲突!");
				}
			}
			out.flush();
			out.close();
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-20 上午9:11:29
	 * Description:查看排期列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void listing(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {						
			List<Date> dateList = new ArrayList<Date>();		
			//当前时间
			String dayStr = request.getParameter("day");
			Date dateTime;
			if(dayStr == null || "".equals(dayStr)) {
				dateTime = new Date(System.currentTimeMillis());				
			} else {
				dateTime = Date.valueOf(dayStr);
			}
			request.setAttribute("daySelected", dateTime.toString());
			Timestamp now = new Timestamp(System.currentTimeMillis());
			long time = now.getTime();
			//获得当前时间并延后五天的时间 例如：2015-01-19 星期一 2015-01-20 星期二 2015-01-21 星期三……
			for(int i=0;i<=4;i++)
			{
				long time_i = time+3600*24*i*1000l;
				Date t_i =  new Date(time_i);
				//String dateTime_i =sdf.format(t_i);
				dateList.add(t_i);
			}
			request.setAttribute("weekdays", Arrays.asList("周日", "周一", "周二", "周三", "周四", "周五", "周六"));
			request.setAttribute("dateList", dateList);
			request.setAttribute("datStr", dayStr);
			/*if(cinema!=null)
			{*/
			
				//查询当日所有的排期
				List<Screenings> sList = screeningService.selectScreenings(cinema.getCinemaId(), dateTime.toString()+" 00:00:00");
				request.setAttribute("sList", sList);
			/*}*/
			
			request.getRequestDispatcher("business_listing.jsp")
					.forward(request, response);
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午2:58:26
	 * Description:添加排期数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void Screening(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		 String movieid = request.getParameter("movieId");
		 String  hallid = request.getParameter("hallId");
		 String date = request.getParameter("date");
		 String hour = request.getParameter("hour");
		 String minute = request.getParameter("minute");
		 String oriprice = request.getParameter("oprice");
		 String curprice = request.getParameter("cprice");
 		 if(movieid.equals("")||hallid.equals("")||date.equals("")||hour.equals("")||minute.equals("")||oriprice.equals("")||curprice.equals(""))
		 {
			 //addListing(request, response);
 			 out.write("input");
 			 out.flush();
 			 out.close();
 			 return;
		 }
		 else{
			 String dateTime = date+" "+hour+":"+minute+":00";
			 int movieId = Integer.parseInt(movieid);
			 int hallId = Integer.parseInt(hallid);
			 int oprice = Integer.parseInt(oriprice);
			 int cprice = Integer.parseInt(curprice);
			 Timestamp startTime = Timestamp.valueOf(dateTime);
			 Movie movie = new Movie();
			 movie.setMovieId(movieId);
			 VideoHall videoHall = videoHallService.selectById(hallId);
			 Screenings screenings = new Screenings(movie, videoHall, startTime, oprice, cprice);		
			 Cinema cinema = new Cinema();
			 cinema.setCinemaId(videoHall.getCinemaId());
			 screenings.setCinema(cinema);
			 int row = screeningService.insertScreening(screenings);
			 if(row==1)
			 {
				 out.write("success");	 			 
				/* request.getRequestDispatcher("business_addScreeningSuccess.jsp")
				 		.forward(request, response);*/
			 }else{
				 out.write("input");	 			
				 //addListing(request, response);
			 }
			 out.flush();
 			 out.close();
		}
		 
		 
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午11:26:11
	 * Description:添加排期列表数据填充
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addListing(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<Movie> movieList = movieService.selectAddListing(dateTime1,dateTime2);
		if(cinema!=null)
		{
		List<VideoHall> hallList = videoHallService.selectVideoHalls(cinema.getCinemaId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<String> dateList = new ArrayList<String>();
		//当前时间
		Date dateTime = new Date(System.currentTimeMillis());
		long time = dateTime.getTime();
		//获得当前时间并延后一周的时间 例如：2015-01-19 2015-01-20 2015-01-21 ……
		for(int i=0;i<=6;i++)
		{
			long time_i = time+3600*24*i*1000l;
			Date t_i =  new Date(time_i);
			String dateTime_i =sdf.format(t_i);
			dateList.add(dateTime_i);
		}		
		request.setAttribute("movieList", movieList);
		request.setAttribute("hallList", hallList);
		request.setAttribute("dateList", dateList);
		}
		request.getRequestDispatcher("business_addListing.jsp")
				.forward(request, response);
		
	}
	
	private MovieService movieService = new MovieService();
	private VideoHallService videoHallService = new VideoHallService();
	private ScreeningService screeningService = new ScreeningService();
	private	HttpSession session;
	private String dateTime1;
	private String dateTime2;
	private Cinema cinema;
}
