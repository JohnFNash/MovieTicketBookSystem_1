/*
*www.dyr.com
*Copyright (c) 2014 All Rights Revered
*/

/**
 * 
 */
package com.team3.mbts.servlet.movie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Movie;
import com.team3.mbts.entity.VideoHall;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.service.VideoHallService;
import com.team3.mbts.util.PageBean;

/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.movie
 *FileName:MovieServlet.java
 *Comments(意见):
 *JDK Version
 *Author:谢洪章
 *Create Date:2015-1-13下午2:54:17
 * @author Administrator
 *
 */
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 3738156692725428444L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			session = request.getSession();
			session.setMaxInactiveInterval(60*24*7);
			//得到当前时间 并减一个月的时间 作为筛选影片的过期条件
			Date dateTime = new Date();
			long time = dateTime.getTime();
			long time1 = time-3600*24*30*1000l;
			Date time2 = new Date(time1) ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dateTime1 = sdf.format(time2);
			
			
			String op = request.getParameter("op");
			if("mList".equals(op))
			{
				mList(request, response);
			}
			else if("addListing".equals(op))
			{
				addListing(request, response);
			}
			else if("details".equals(op))
			{
				details(request, response);
			}
			else if("search".equals(op))
			{
				search(request, response);
			}
			else if("typeSearch".equals(op))
			{
				typeSearch(request, response);
				
			}
		
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-24 下午1:53:55
	 * Description:按影片类型搜索
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void typeSearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			int typeNum = Integer.parseInt(request.getParameter("typeNum"));
			
			String[] type = {"动作","喜剧","爱情","科幻","奇幻","灾难","恐怖","记录","犯罪","战争","冒险","剧情"}; 
			//System.out.println(type[typeNum]);
			
			int movieCount = movieService.pageCount(type[typeNum], dateTime1);
			//分页包装类
			PageBean pageBean = 
						new PageBean(movieCount, request.getParameter("pageSize"), request.getParameter("curPage"));
			
			List<Movie> movieList = movieService.selectMovieByType(pageBean.getPageSize(), pageBean.getCurPage(), type[typeNum], dateTime1);
			pageBean.setDataList(movieList);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("num", typeNum);
			//进行内部跳转
			request.getRequestDispatcher("business_movieList.jsp")
						.forward(request, response);
 	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午6:55:28
	 * Description:关键字查询影片
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 key = request.getParameter("key");	
			 
			 if(!key.equals(""))
			 {
				 List<Movie> movies = movieService.selectMovieByKey(key,dateTime1);
				 request.setAttribute("key", key);
				 request.setAttribute("movieList", movies);
				//进行内部跳转
				request.getRequestDispatcher("business_movieList.jsp")
							.forward(request, response);
			 }
			 else {
				 mList(request, response);
			}
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 下午4:29:46
	 * Description:影片详情
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void details(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			int movieId = Integer.parseInt(request.getParameter("movieId"));
			Movie movie = movieService.getMovieById(movieId);
			System.out.println("影片类型："+movie.getMovieTypes());
			request.setAttribute("movie", movie);
			request.getRequestDispatcher("business_movieDetails.jsp")
					.forward(request, response);
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午11:26:11
	 * Description:添加排期
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addListing(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Movie> movieList = movieService.selectMovieList(dateTime1);
		Cinema cinema =  (Cinema) session.getAttribute("cinema");
		List<VideoHall> hallList = videoHallService.selectVideoHalls(cinema.getCinemaId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<String> dateList = new ArrayList<String>();
		//当前时间
		Date dateTime = new Date();
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
		request.getRequestDispatcher("business_addListing.jsp")
				.forward(request, response);
		
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-19 上午11:25:45
	 * Description:电影列表 分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void mList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	
				//得到影片总记录数
				int movieCount = movieService.getMovieCount(dateTime1);
				
				//分页包装类
				PageBean pageBean = 
						new PageBean(movieCount, request.getParameter("pageSize"), request.getParameter("curPage"));
				List<Movie> movieList = movieService.selectMovies(pageBean.getCurPage(), dateTime1, pageBean.getPageSize());
				pageBean.setDataList(movieList);
				
				request.setAttribute("pageBean", pageBean);
				
		 
			//进行内部跳转
			request.getRequestDispatcher("business_movieList.jsp")
					.forward(request, response);
	}
	
	private MovieService movieService = new MovieService();
	private VideoHallService videoHallService = new VideoHallService();
	private	HttpSession session;
	private String dateTime1;
	private String key;

}
