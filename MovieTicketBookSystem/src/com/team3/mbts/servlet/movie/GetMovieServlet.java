package com.team3.mbts.servlet.movie;

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

import com.team3.mbts.entity.*;
import com.team3.mbts.service.*;

public class GetMovieServlet extends HttpServlet {
	private static final long serialVersionUID = -490290216290136796L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String movieIdStr = request.getParameter("movieId");
		if(!movieIdStr.matches("[0-9]+")) {//传入的影片编号非法
			response.sendRedirect("IndexServlet");
			return;
		}
		String op = request.getParameter("op");
		if("switchCinema".equals(op)) {//切换影片
			switchCinema(request, response, movieIdStr);
		} else if("loadRemarks".equals(op)) {//加载评论
			loadRemarks(request, response, movieIdStr);
		} else {
			normalHandling(request, movieIdStr);
			request.getRequestDispatcher("movie.jsp").forward(request, response);			
		}					
	}
	
	//加载影评
	private void loadRemarks(HttpServletRequest request,
			HttpServletResponse response, String movieIdStr) throws IOException {
		int movieId = Integer.parseInt(movieIdStr);
		//获取之前已经加载的评论数目
		int prevRecords = 0;
		try {
			prevRecords = Integer.parseInt(request.getParameter("prevRecords"));
		} catch(NumberFormatException e) {
			prevRecords = 0;
		}
		//加载5条评论
		MovieRemarkService service = new MovieRemarkService();
		List<MovieRemark> movieRemarks = service.selectSpecifiedNumMovieRemarks(movieId, 5, prevRecords);
		//将评论列表转换为json数组，并写入输出流
		String remarksStr = remarkListToJson(movieRemarks);
		PrintWriter out = response.getWriter();
		out.write(remarksStr);
		out.flush();
		out.close();
	}

	//根据影院切换场次
	private void switchCinema(HttpServletRequest request,
			HttpServletResponse response, String movieIdStr) throws IOException {
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
	
		String cinemaIdStr = request.getParameter("cinemaId");
		if(cinemaIdStr!=null && cinemaIdStr.matches("[0-9]+")) {
			int cinemaId = Integer.parseInt(cinemaIdStr);
			int movieId = Integer.parseInt(movieIdStr);
			//查询选定影片在该影院当日所有的排期
			List<Screenings> sList = screeningService.selectScreeningsForMovie(cinemaId, movieId, dayStr);
			PrintWriter out = response.getWriter();
			String screeningsStr = screeningsListToJson(sList);
			out.write(screeningsStr);
			out.flush();
			out.close();
		}
	}

	private void normalHandling(HttpServletRequest request, String movieIdStr) {
		//查找影片信息
		MovieService service = new MovieService();
		Movie movie = service.getMovieById(Integer.parseInt(movieIdStr));
		request.setAttribute("movie", movie);
		
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
		
		//查询当天放映该影片的影院
		CinemaService cinemaService = new CinemaService();
		List<Cinema> cinemaList =
				cinemaService.selectCinemasForMovieOnShow(movie.getMovieId(), dayStr, 10);
		request.setAttribute("cinemaList", cinemaList);
		
		ScreeningService screeningService = new ScreeningService();		
		if(cinemaList.size() > 0) {
			int cinemaId = cinemaList.get(0).getCinemaId();
			//查询选定影片在该影院当日所有的排期
			List<Screenings> sList = screeningService.selectScreeningsForMovie(cinemaId, movie.getMovieId(), dayStr);
			request.setAttribute("sList", sList);
			
			request.setAttribute("cinemaSelected", cinemaList.get(0).getCinemaId());//设置默认选中影院
		}
		
		return;
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
	 *  Create Time: 2015-1-26 上午1:46:12
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
	 * @param remarkList 影片评价列表
	 * @return
	 */
	private String remarkListToJson(List<MovieRemark> remarkList) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(MovieRemark remark : remarkList) {
			UserInfo user = remark.getUser();
			
			sb.append("{");
			sb.append("\"remarkId\":" + remark.getId() + ",");
			sb.append("\"userId\":" + user.getUserId() + ",");
			sb.append("\"userName\":\"" + user.getAccount() + "\",");
			sb.append("\"headPath\":\"" + user.getHeadPath() + "\",");
			sb.append("\"title\":\"" + remark.getTitle() + "\",");
			sb.append("\"content\":\"" + stringToJson(remark.getContent()) + "\",");
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
	
	//处理json中的特殊字符
	private String stringToJson(String s){    
        StringBuffer sb = new StringBuffer();     
        for(int i=0; i<s.length(); i++){     
      
            char c =s.charAt(i);     
            switch(c){     
            case'\"':     
                sb.append("\\\"");     
                break;     
            case'\\':   //如果不处理单引号，可以释放此段代码，若结合下面的方法处理单引号就必须注释掉该段代码
                sb.append("\\\\");     
                break;     
            case'/':     
                sb.append("\\/");     
                break;     
            case'\b':      //退格
                sb.append("\\b");     
                break;     
            case'\f':      //走纸换页
                sb.append("\\f");     
                break;     
            case'\n':     
                sb.append("\\n");//换行    
                break;     
            case'\r':      //回车
                sb.append("\\r");     
                break;     
            case'\t':      //横向跳格
                sb.append("\\t");     
                break;     
            default:     
                sb.append(c);    
            }}
        return sb.toString();     
     }
}
