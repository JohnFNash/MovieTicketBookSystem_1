package com.team3.mbts.servlet.other;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Movie;
import com.team3.mbts.entity.MovieRemark;
import com.team3.mbts.entity.Screenings;
import com.team3.mbts.service.CinemaService;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.service.ScreeningService;
import com.team3.mbts.util.PageBean;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 4142703305074996752L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		MovieService service = new MovieService();
		
		loadHotestMovies(request, service); //加载最热的影片
		loadHotCinemas(request, service);	//加载热门影院
		loadWonderfulMovieRemarks(request, service);//加载精彩评论
		loadScreenings(request);	//加载影片场次
		
		//请求分派
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * 加载影片场次
	 * @param request
	 */
	private void loadScreenings(HttpServletRequest request) {
		//从request取出存入的最热的影片
		@SuppressWarnings("unchecked")
		List<Movie> hotMovies = ((List<Movie>)request.getAttribute("hotMovieList"));
		if(hotMovies.size() == 0) {
			return;
		}		
		Movie hotestMovie = hotMovies.get(0);		
		int movieId = hotestMovie.getMovieId();
		String day = new Date(System.currentTimeMillis()).toString();
		//加载有放映该影片的影院
		CinemaService service = new CinemaService();
		List<Cinema> hotestCinema =
				service.selectCinemasForMovieOnShow(movieId, day, 2);
		if(hotestCinema.size() == 0) {
			return;
		}
		//查询该影片在选出来的影院中的场次
		ScreeningService screeningService = new ScreeningService();
		List<Screenings> screenings_1 =
				screeningService.selectScreeningsForMovie(hotestCinema.get(0).getCinemaId(), movieId, day);
		if(hotestCinema.size() > 1) {
			List<Screenings> screenings_2 =
					screeningService.selectScreeningsForMovie(hotestCinema.get(1).getCinemaId(), movieId, day);
			request.setAttribute("screenings_2", screenings_2);
		}		
		//放入request
		request.setAttribute("screenings_1", screenings_1);		
	}

	/**
	 * 加载特定数目的最热电影
	 * @param request
	 * @param service
	 */
	private void loadHotestMovies(HttpServletRequest request, MovieService service) {		
		//获取正在热映的影片的数目
		int totalRecords = service.countHotestMovies();
		//设置分页包装类
		final int hotMovieNum = 13;	//默认的最热影片的数目
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? 
						hotMovieNum + "" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置最热影片
		List<Movie> movieList = 
				service.getHotestMoviesByPage(pageBean.getCurPage(), pageBean.getPageSize());
		pageBean.setDataList(movieList);
		//将最热的影片列表放入request
		request.setAttribute("hotMovieList", movieList);		
	}
	
	/**
	 * 加载特定数目的热门影院
	 * @param request
	 * @param service
	 */
	private void loadHotCinemas(HttpServletRequest request, MovieService service) {					
		//获取影院的数目
		int totalRecords = service.countHotCinemas();
		//设置分页包装类
		final int hotCinemaNum = 5;	//默认的热门影院的数目
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? 
						hotCinemaNum + "" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置热门影院
		List<Cinema> cinemaList = 
				service.getHotCinemasByPage(pageBean.getCurPage(), pageBean.getPageSize());
		pageBean.setDataList(cinemaList);
		//将热门影院列表放入request
		request.setAttribute("hotCinemaList", cinemaList);		
	}
	
	/**
	 *  查询精彩评论
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-17 下午2:12:33
	 *  Description:
	 *  @param request
	 *  @param service
	 */
	private void loadWonderfulMovieRemarks(HttpServletRequest request, MovieService service) {		
		//查询指定数目的电影评论
		int numberToGet = 4;
		List<MovieRemark> movieRemarkList = service.getTopWonderfulMoiveRemarks(numberToGet);
		//将热门影院列表放入request
		request.setAttribute("movieRemarkList", movieRemarkList);		
	}
	
	
}
