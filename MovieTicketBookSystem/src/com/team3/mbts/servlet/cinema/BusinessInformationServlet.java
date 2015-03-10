/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.servlet.cinema;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.team3.mbts.entity.Area;
import com.team3.mbts.entity.Cinema;
import com.team3.mbts.entity.Seller;
import com.team3.mbts.service.CinemaService;
import com.team3.mbts.service.SellerService;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.servlet.cinema
 * FileName:BusinessInformationServlet.java
 * Comments:商家信息管理Servlet
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-21 下午2:58:22
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class BusinessInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 2103670400858276608L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			session = request.getSession();
			String op = request.getParameter("op");
			
			if ("oldpassCheck".equals(op)) {
				oldpassCheck(request, response);
			}
			else if ("pwdModify".equals(op)) {
				pwdModify(request, response);
			}
			else if ("cinemaInfo".equals(op)) {//加载当前影院信息
				cinemaInfo(request, response);
			}
			else if("changeFirstLevel".equals(op)) {//改变第一级行政区域
				changeFirstLevel(request, response);
			}
			else if("changeSecondLevel".equals(op)) {//改变第二级行政区域
				changeSecondLevel(request, response);
			}		
			else if("beforeAddCinema".equals(op)) {//添加影院前加载行政区信息
				beforeAddCinema(request, response);
			}
			else if("loadAllCinemas".equals(op)) {//添加当前商家的所有影院
				loadAllCinemas(request, response);
			}
			else if("switchCinema".equals(op)) {//切换影院
				switchCinema(request, response);
			}
			else if("addCinema".equals(op)) {//添加新的影院
				try {
					addCinema(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					modify(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
	}

	/**
	 * 切换影院
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void switchCinema(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
		Cinema cinema = cinemaService.selectCinemaById(cinemaId);
		request.getSession(false).setAttribute("cinema", cinema);
		
		request.getRequestDispatcher("business_index.jsp").forward(request, response);
	}

	/**
	 * 查询商家的所有影院信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void loadAllCinemas(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Seller seller = (Seller) request.getSession(false).getAttribute("seller");
		if(seller == null) {//尚未登录
			response.sendRedirect("business/business_login.html");
			return;
		}
		
		//查询当前登录的商家的所有影院信息
		List<Cinema> cinemaList =
				cinemaService.selectAllCinemaForSeller(seller.getSellerId());
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(Cinema cinema : cinemaList) {
			sb.append("{");
			sb.append("\"cinemaId\":" + cinema.getCinemaId() + ",");
			sb.append("\"cinemaName\":\"" + cinema.getCinemaName() + "\"");
			sb.append("},");
		}
		sb.append("]");
		sb = sb.replace(sb.length()-2, sb.length()-1, "");
		
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
	}
	

	/**
	 * 改变第一级行政区域时同步更新第二级、第三级行政区域
	 * @throws IOException 
	 */
	private void changeFirstLevel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取选择的第一级行政区域,并获取其下的第二级行政区域
		int firstLevelAreaId = Integer.parseInt(request.getParameter("firstLevelArea"));
		List<Area> secondLevelAreaList = 
				cinemaService.selectSecondLevelAreas(firstLevelAreaId);
		//request.setAttribute("secondLevelAreaList", secondLevelAreaList);
		
		Area defaultSecondLevelArea = secondLevelAreaList.get(0);
		List<Area> thirdLevelAreaList = 
				cinemaService.selectThirdLevelAreas(defaultSecondLevelArea.getAreaId());
		//request.setAttribute("thirdLevelAreaList", thirdLevelAreaList);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		//第二级行政区域
		sb.append("\"secondLevelAreaList\":");
		sb.append("[");
		for(Area area : secondLevelAreaList) {
			sb.append("{");
			sb.append("\"areaId\":" + area.getAreaId() + ",");
			sb.append("\"areaName\":\"" + area.getAreaName() + "\"");
			sb.append("},");
		}
		sb = sb.replace(sb.length()-1, sb.length(), "");
		sb.append("],");
		
		//第三级行政区域
		sb.append("\"thirdLevelAreaList\":");
		sb.append("[");
		for(Area area : thirdLevelAreaList) {
			sb.append("{");
			sb.append("\"areaId\":" + area.getAreaId() + ",");
			sb.append("\"areaName\":\"" + area.getAreaName() + "\"");
			sb.append("},");
		}
		sb = sb.replace(sb.length()-1, sb.length(), "");
		sb.append("]");
		sb.append("}");
						
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();		
	}
	
	/**
	 * 改变第二级行政区域时同步更新第三级行政区域
	 * @throws IOException 
	 */
	private void changeSecondLevel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取选择的第二级行政区域,并获取其下的第三级行政区域
		int secondLevelAreaId = Integer.parseInt(request.getParameter("secondLevelArea"));		
		List<Area> thirdLevelAreaList = 
				cinemaService.selectThirdLevelAreas(secondLevelAreaId);
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(Area area : thirdLevelAreaList) {
			sb.append("{");
			sb.append("\"areaId\":" + area.getAreaId() + ",");
			sb.append("\"areaName\":\"" + area.getAreaName() + "\"");
			sb.append("},");
		}
		sb.append("]");
		sb = sb.replace(sb.length()-2, sb.length()-1, "");
		
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		out.flush();
		out.close();
		//request.setAttribute("thirdLevelAreaList", thirdLevelAreaList);
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws Exception {						
			//文件上传操作
			// 检查这是不是一个文件上传的请求
			Boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			
			if (!isMultipart) {
				
				cinemaInfo(request,response);
			}
			
			// 创建一个基于磁盘文件的工厂对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			// 设置上传文件的临时保存位置
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			
			// 创建一个文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);						
			try {
				// 解析请求
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				//遍历所有的表单字段和文件上传
				for (FileItem item : items) {
					if(item.isFormField())//如果是表单文件
					{
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						if(name.equals("cinemaName"))
						{
							cinemaName = value; 
						}
						else if (name.equals("thirdLevelArea")) {
							thirdLevelArea = value;							 
						}
						else if (name.equals("address")) {
							 address = value;
						}
						else if (name.equals("tel")) {
							 tel = value;
						}
						else if (name.equals("special")) {
							 special = value;
						}
						else if (name.equals("description")) {
							 description = value;
						}
					}
					else if(!item.isFormField())
					{
						String contentType = item.getContentType();
						if(contentType.equals("image/jpeg"))
						{
							//上传文件的表单字段名
							String fieldName = item.getFieldName();							
							
							//这里是得到上传路径的文件夹名称，这是一个物理路径名
							String uploadPath = this.getServletContext().getRealPath("/images/cinema");
							
							//创建目的地文件
							//产生一个随机的文件名，这个可以有^o^
							String saveFileName = new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() ) + (new Random().nextInt(90000)+10000);
							File destFile = new File(uploadPath + "/" + saveFileName+".jpg");
							//System.out.println("destFile"+destFile);
							//重新上传了logo 使用新路径
							if(fieldName.equals("logoPhoto"))
							{
								logoPhoto = destFile.toString().substring(destFile.toString().length()-37, destFile.toString().length()).replace("\\", "//");
							}
							//重新上传了scene 使用新路径
							if(fieldName.equals("cinemaPhoto"))
							{
								scenePhoto = destFile.toString().substring(destFile.toString().length()-37, destFile.toString().length()).replace("\\", "//");
							}
							//将上传文件写到目的地
							item.write(destFile);
							
						}						
					}
				}
				//修改 或者初始插入新影院信息
				Cinema cinema = (Cinema) session.getAttribute("cinema");				
				if(cinema==null) //新注册商家 还没有商家信息
				{
					Area area = new Area();
					area.setAreaId(Integer.parseInt(thirdLevelArea));
					Cinema cinema2 = 
						new Cinema(cinemaName, area, address, logoPhoto, scenePhoto, tel, special, description);
					int row = cinemaService.insertCinema(cinema2);					
					if(row==1)
					{
						cinema2 = cinemaService.selectCinemaById(cinema2.getCinemaId());	//查询area详细信息
						session.setAttribute("cinema", cinema2);
						if(cinema2!=null) {//影院信息添加成功,则添加商家-影院关联信息
							Seller seller = (Seller) session.getAttribute("seller");
							cinemaService.insertSellerCinema(seller.getSellerId(), cinema2.getCinemaId());
							
							request.getRequestDispatcher("business_index.jsp").forward(request, response);
						}
					}
				}
				else {	//修改商家信息
					
						if(logoPhoto!=null)
						{
							logo1 = logoPhoto;
						}
						else {
							logo1 = cinema.getLogo();
						}
						if(scenePhoto!=null)
						{
							scene1 = scenePhoto;
						}
						else {
							scene1 = cinema.getScene();
						}
						
						Area area = new Area();
						area.setAreaId(Integer.parseInt(thirdLevelArea));
						Cinema cinema3 = 
							new Cinema(cinema.getCinemaId(), cinemaName, area, address, logo1, scene1, tel, special, description);
						int row3 = 
							cinemaService.updateCinema(cinema3);
						if(row3==1)
						{								
							cinema3 = cinemaService.selectCinemaById(cinema3.getCinemaId());//查询area的详细信息
							session.setAttribute("cinema", cinema3);
							request.getRequestDispatcher("business_modifySuccess.jsp")
									.forward(request, response);
							return;
						}
				}
				
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}						
	}

	/**
	 * 添加新的影院
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void addCinema(HttpServletRequest request, HttpServletResponse response) throws Exception {						
		//文件上传操作
		// 检查这是不是一个文件上传的请求
		Boolean isMultipart = ServletFileUpload.isMultipartContent(request);		
		if (!isMultipart) {			
			cinemaInfo(request,response);
		}
		
		// 创建一个基于磁盘文件的工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 设置上传文件的临时保存位置
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		
		// 创建一个文件上传处理器
		ServletFileUpload upload = new ServletFileUpload(factory);						
		try {
			// 解析请求
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			//遍历所有的表单字段和文件上传
			for (FileItem item : items) {
				if(item.isFormField())//如果是表单文件
				{
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					if(name.equals("cinemaName"))
					{
						cinemaName = value; 
					}
					else if (name.equals("thirdLevelArea")) {
						thirdLevelArea = value;							 
					}
					else if (name.equals("address")) {
						 address = value;
					}
					else if (name.equals("tel")) {
						 tel = value;
					}
					else if (name.equals("special")) {
						 special = value;
					}
					else if (name.equals("description")) {
						 description = value;
					}
				}
				else if(!item.isFormField())
				{
					String contentType = item.getContentType();
					if(contentType.equals("image/jpeg")) {
						//上传文件的表单字段名
						String fieldName = item.getFieldName();							
						
						//这里是得到上传路径的文件夹名称，这是一个物理路径名
						String uploadPath = this.getServletContext().getRealPath("/images/cinema");
						
						//创建目的地文件
						//产生一个随机的文件名，这个可以有^o^
						String saveFileName = new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() ) + (new Random().nextInt(90000)+10000);
						File destFile = new File(uploadPath + "/" + saveFileName+".jpg");
						//System.out.println("destFile"+destFile);
						//重新上传了logo 使用新路径
						if(fieldName.equals("logoPhoto"))
						{
							logoPhoto = destFile.toString().substring(destFile.toString().length()-37, destFile.toString().length()).replace("\\", "/");
						}
						//重新上传了scene 使用新路径
						if(fieldName.equals("cinemaPhoto"))
						{
							scenePhoto = destFile.toString().substring(destFile.toString().length()-37, destFile.toString().length()).replace("\\", "/");
						}
						//将上传文件写到目的地
						item.write(destFile);						
					}						
				}
			}
			
			Area area = new Area();
			area.setAreaId(Integer.parseInt(thirdLevelArea));
			Cinema cinema2 = 
				new Cinema(cinemaName, area, address, logoPhoto, scenePhoto, tel, special, description);
			int row = cinemaService.insertCinema(cinema2);					
			if(row==1) {//影院信息添加成功,则添加商家-影院关联信息
				Seller seller = (Seller) session.getAttribute("seller");
				cinemaService.insertSellerCinema(seller.getSellerId(), cinema2.getCinemaId());
				
				request.getRequestDispatcher("business_index.jsp").forward(request, response);				
			}			
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}						
	}
	
	/**
	 * 加载当前影院信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void cinemaInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cinema cinema1 = (Cinema) session.getAttribute("cinema");			
		if(cinema1 != null) {//获取当前影院所在行政区的信息
			request.setAttribute("cinema", cinema1);				
			request.setAttribute("thirdLevelAreaId", cinema1.getArea().getAreaId());
			
			/* 获取行政区选择下拉框的数据 */
			List<Area> firstLevelAreaList = cinemaService.selectFirstLevelAreas();
			request.setAttribute("firstLevelAreaList", firstLevelAreaList);			
			
			int firstLevelAreaId = cinema1.getArea().getParentId();			
			if(firstLevelAreaId != 0) {//不是省、直辖市级
				request.setAttribute("secondLevelAreaId", firstLevelAreaId);
				Area area = cinemaService.selectParentLevelAreas(firstLevelAreaId);
				if(area != null) {
					firstLevelAreaId = area.getAreaId();
					request.setAttribute("firstLevelAreaId", firstLevelAreaId);
				}
			}
						
			List<Area> secondLevelAreaList = 
					cinemaService.selectSecondLevelAreas(firstLevelAreaId);
			request.setAttribute("secondLevelAreaList", secondLevelAreaList);
			
			Area defaultSecondLevelArea = secondLevelAreaList.get(0);
			List<Area> thirdLevelAreaList = 
					cinemaService.selectThirdLevelAreas(defaultSecondLevelArea.getAreaId());
			request.setAttribute("thirdLevelAreaList", thirdLevelAreaList);
		} else {//加载默认的行政区的信息
			/* 获取行政区选择下拉框的数据 */
			List<Area> firstLevelAreaList = cinemaService.selectFirstLevelAreas();
			request.setAttribute("firstLevelAreaList", firstLevelAreaList);									
						
			List<Area> secondLevelAreaList = 
					cinemaService.selectSecondLevelAreas(firstLevelAreaList.get(0).getAreaId());
			request.setAttribute("secondLevelAreaList", secondLevelAreaList);
			
			Area defaultSecondLevelArea = secondLevelAreaList.get(0);
			List<Area> thirdLevelAreaList = 
					cinemaService.selectThirdLevelAreas(defaultSecondLevelArea.getAreaId());
			request.setAttribute("thirdLevelAreaList", thirdLevelAreaList);
		}
		request.getRequestDispatcher("business_information.jsp").forward(request, response);
	}

	/**
	 * 添加影院前加载行政区信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void beforeAddCinema(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//加载默认的行政区的信息
		/* 获取行政区选择下拉框的数据 */
		List<Area> firstLevelAreaList = cinemaService.selectFirstLevelAreas();
		request.setAttribute("firstLevelAreaList", firstLevelAreaList);									
					
		List<Area> secondLevelAreaList = 
				cinemaService.selectSecondLevelAreas(firstLevelAreaList.get(0).getAreaId());
		request.setAttribute("secondLevelAreaList", secondLevelAreaList);
		
		Area defaultSecondLevelArea = secondLevelAreaList.get(0);
		List<Area> thirdLevelAreaList = 
				cinemaService.selectThirdLevelAreas(defaultSecondLevelArea.getAreaId());
		request.setAttribute("thirdLevelAreaList", thirdLevelAreaList);
		
		request.getRequestDispatcher("business_addInformation.jsp").forward(request, response);
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-21 下午4:13:33
	 * Description:修改密码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void pwdModify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			String username = request.getParameter("username");
			String newpass = request.getParameter("newpass");
			int row = sellerService.updatePwd(newpass, username);
			PrintWriter out = response.getWriter();
			if(row==1)
			{
				out.write("success");
			}
			out.flush();
			out.close();
	}

	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-21 下午4:13:07
	 * Description:验证旧密码是否正确
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void oldpassCheck(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			String username = request.getParameter("username");
			String oldpass = request.getParameter("oldpass");
			
			int row = sellerService.selectSeller(username, oldpass);
			
			PrintWriter out = response.getWriter();
			if(row==0)
			{
				out.write("error");
			}
			out.flush();
			out.close();
	}


	
	private SellerService sellerService = new SellerService();
	private CinemaService cinemaService = new CinemaService();
	private String logoPhoto;
	private String scenePhoto;
	private HttpSession session;
	private String cinemaName = null;
	private String thirdLevelArea = null;
	private String address = null;
	private String tel = null;
	private String special = null;
	private String description = null;
	
	private String scene1;
	private String logo1;
}
