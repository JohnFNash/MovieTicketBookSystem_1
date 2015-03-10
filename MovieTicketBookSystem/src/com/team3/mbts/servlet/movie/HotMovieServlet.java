package com.team3.mbts.servlet.movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.Movie;
import com.team3.mbts.service.MovieService;
import com.team3.mbts.util.PageBean;

public class HotMovieServlet extends HttpServlet {
	private static final long serialVersionUID = -6732921885968643036L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {			
		String typeName = request.getParameter("typeName");
		String key = request.getParameter("key");
		String order = request.getParameter("order");
		
		MovieService service = new MovieService();
		//获取正在热映的影片的数目
		int totalRecords = service.selectHotMoviesCountByDynamicParameter(typeName, key, order);
		//设置分页包装类
		final int hotMovieNum = 6;	//默认的最热影片的数目
		PageBean pageBean = 
				new PageBean(totalRecords, request.getParameter("pageSize")==null ? 
						hotMovieNum + "" : request.getParameter("pageSize"), request.getParameter("curPage"));
		//设置最热影片
		int cutPage = pageBean.getCurPage();
		int pageSize = pageBean.getPageSize();
		RowBounds rowBounds = new RowBounds(pageSize*(cutPage-1), pageBean.getPageSize());
		List<Movie> movieList = 
				service.getHotMoviesByDynamicParameter(typeName, key, order, rowBounds);
		pageBean.setDataList(movieList);
				
		//将pageBean放入request
		request.setAttribute("pageBean", pageBean);
		
		if(typeName != null && !"".equals(typeName)) {
			request.setAttribute("typeName", typeName);
		}
		if(key != null && !"".equals(key)) {
			request.setAttribute("key", key);
		}
		if(order != null && !"".equals(order)) {
			request.setAttribute("order", order);
		}
		
		request.getRequestDispatcher("hot.jsp").forward(request, response);		
	}

}
