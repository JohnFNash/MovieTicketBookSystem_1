/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.servlet.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Movie;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.util.PageBean;

public class LoadAllMoviesServlet extends HttpServlet {
	private static final long serialVersionUID = -4403119179884941827L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String op = request.getParameter("op");
		if("mList".equals(op))
		{
			mList(request, response);
		}	
		else if("search".equals(op))
		{
			search(request, response);
		}
		else if ("details".equals(op)) {
			details(request,response);
		}
		else if("typeSearch".equals(op))
		{
			typeSearch(request, response);
			
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
			request.setAttribute("movie", movie);
			request.getRequestDispatcher("movieDetails.jsp")
					.forward(request, response);
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
			
			int movieCount = movieService.getPageCount(type[typeNum]);
			//分页包装类
			PageBean pageBean = 
						new PageBean(movieCount, request.getParameter("pageSize"), request.getParameter("curPage"));
			
			List<Movie> movieList = movieService.getMovieByType(pageBean.getPageSize(), pageBean.getCurPage(), type[typeNum]);
			pageBean.setDataList(movieList);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("num", typeNum);
			//进行内部跳转
			request.getRequestDispatcher("movieList.jsp")
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
				int movieCount = movieService.selectAllMovieCount();
				
				//分页包装类
				PageBean pageBean = 
						new PageBean(movieCount, request.getParameter("pageSize"), request.getParameter("curPage"));
				List<Movie> movieList = movieService.selectAllMovie(pageBean.getPageSize(), pageBean.getCurPage());
				pageBean.setDataList(movieList);
				
				request.setAttribute("pageBean", pageBean);
				
		 
			//进行内部跳转
			request.getRequestDispatcher("movieList.jsp")
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
			 System.out.println(key);
			 if(!key.equals(""))
			 {
				 List<Movie> movies = movieService.getMovieByKey(key);
				 request.setAttribute("key", key);
				 request.setAttribute("movieList", movies);
				//进行内部跳转
				request.getRequestDispatcher("movieList.jsp")
							.forward(request, response);
			 }
			 else {
				 mList(request, response);
			}
	}
	
	private MovieService movieService = new MovieService();	
	private String key;

}
