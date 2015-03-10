package com.team3.mbts.servlet.movie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.Movie;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.util.PageBean;

public class MovieStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 6936680543274877241L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MovieService service = new MovieService();
		filter(request, service);
		//设置热映影片
		List<Movie> hotMovieList = 
				service.getHotestMoviesByPage(1, 8);
		request.setAttribute("hotMovieList", hotMovieList);
		request.getRequestDispatcher("movieStore.jsp").forward(request, response);
	}
	
	@SuppressWarnings("deprecation")
	private void filter(HttpServletRequest request, MovieService service) throws UnsupportedEncodingException {
		String area = request.getParameter("area");
		if(area != null && !"".equals(area)) {
			request.setAttribute("area", area);
		}
		String typeName = request.getParameter("typeName");
		if(typeName != null && !"".equals(typeName)) {
			request.setAttribute("typeName", typeName);
		}
		String timespan = request.getParameter("timespan");
		String startTime = null, endTime = null;
		if(timespan!=null && !"".equals(timespan)) {
			String[] timeArr = timespan.split("_");
			if(timeArr.length == 2) {
				startTime = timeArr[0];
				endTime = timeArr[1];			
			}
			request.setAttribute("timespan", timespan);
		} else {//如果尚未选择年份，则选中当前年份
			Calendar calendar = new GregorianCalendar(Locale.CHINA);
			int year = calendar.get(Calendar.YEAR);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date low = new Date(year-1900, 0, 1);			
			Date high = new Date(year-1900, 11, 31);
			startTime = sf.format(low);
			endTime = sf.format(high);
			timespan = startTime + "_" + endTime;
			request.setAttribute("timespan", timespan);
		}
		String key = request.getParameter("key");
		if(key != null && !"".equals(key)) {
			request.setAttribute("key", key);
		}
		
		//获取筛选得到的影片的总数
		int totalRecords = service.countFilterMoviesInMovieStore(area, typeName, startTime, endTime, key);
		//设置分页包装类
		final int movieNumPerPage = 6;	//默认的每页要获取的影片的数目
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? 
						movieNumPerPage + "" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置的影片
		RowBounds rowBounds = new RowBounds(pageBean.getPageSize()*(pageBean.getCurPage()-1), pageBean.getPageSize());
		List<Movie> movieList = 
				service.filterMoviesInMovieStore(area, typeName, startTime, endTime, key, rowBounds);
		pageBean.setDataList(movieList);		
		//将pageBean放入request
		request.setAttribute("pageBean", pageBean);
	}
	
}
