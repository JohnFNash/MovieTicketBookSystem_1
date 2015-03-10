/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.CinemaRemark;
import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.CinemaRemarkService;
import com.team3.mbts.service.CinemaService;

/**
 * 
 *project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.user	
 *FileName:ShwoCinemaServlet.java	
 *Comments:	
 *JDK Version:
 *Author:XiongWei
 *Create Date:2015-1-21上午11:37:13
 *Modified By:Casper
 *Modified Time:
 *What is Modified:
 *Version:
 * @param <cinemaService>
 */
public class ShwoCinemaServlet extends HttpServlet {
	private static final long serialVersionUID = -1455118740348359474L;
	
	//实例化CinemaService
	private CinemaService cinemaService = new CinemaService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", -10);
		
		String op = request.getParameter("op");
		if("loadMoreRemarks".equals(op)) {//加载更多评论
			loadMoreRemarks(request, response);
		} else {//加载影院信息及最近几条评论
			loadCinema(request, response);
		}
			
	}

	/**
	 * 加载影院信息及最近几条评论
	 *  @author 徐晓聪
	 *  Create Time: 2015-3-2 下午5:13:28
	 *  Description:
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	private void loadCinema(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//根据影院ID查询评论
		List<Cinema> cas =cinemaService.getAllCinemas();
		
		//获取影院ID值
		int cinemaId=0;
		try {
			cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
		} catch (NumberFormatException e) {
			cinemaId=cas.get(0).getCinemaId();
		}
		request.setAttribute("cinemaId", cinemaId);
		
		Cinema cinema=cinemaService.selectCinemaById(cinemaId);
		
		//放入值
		request.setAttribute("cas", cas);
		List<CinemaRemark> pry= cinemaService.getcinemaRemark(cinemaId,0);
		request.setAttribute("pry", pry);
		request.setAttribute("cinema", cinema);
		request.getRequestDispatcher("CinemaRemark.jsp").forward(request, response);
	}		

	//加载影影院评论
	private void loadMoreRemarks(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));		
		//获取之前已经加载的评论数目
		int prevRecords = 0;
		try {
			prevRecords = Integer.parseInt(request.getParameter("prevRecords"));
		} catch(NumberFormatException e) {
			prevRecords = 0;
		}
		//加载5条评论
		CinemaRemarkService service = new CinemaRemarkService();
		List<CinemaRemark> cinemaRemarks = service.selectSpecifiedNumNoFilterCinemaRemarks(cinemaId, 5, prevRecords);
		//将评论列表转换为json数组，并写入输出流
		String remarksStr = remarkListToJson(cinemaRemarks);
		PrintWriter out = response.getWriter();
		out.write(remarksStr);
		out.flush();
		out.close();
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
