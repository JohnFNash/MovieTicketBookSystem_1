/*
*www.dyr.com
*Copyright (c) 2014 All Rights Revered
*/

/**
 * 
 */
package com.team3.mbts.servlet.movie;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.team3.mbts.entity.Movie;

import com.team3.mbts.service.MovieService;

/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.movie
 *FileName:AddMovieServlet.java
 *Comments(意见):
 *JDK Version
 *Author:谢洪章
 *Create Date:2015-1-14下午2:48:15
 * @author Administrator
 *
 */
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = -8086330434794807957L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> movieTypeList = new ArrayList<String>();
		String movieName = "";
		String movieTitle = "";
		String is3D = "";
		String language = "";
		String area = "";
		String time = "";
		String directors = "";
		String actor = "";
		String publish = "";
		String description = "";
		String post = "";
		
		//文件上传操作
		// 检查这是不是一个文件上传的请求
		Boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			
			request.getRequestDispatcher("addMovie.jsp")
					.forward(request, response);
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
					if(name.equals("movieName"))
					{
						movieName = value; 
					}
					else if (name.equals("movieTitle")) {
						movieTitle = value;
					}
					else if (name.equals("type")) {
						 movieTypeList.add(value);
					}
					else if (name.equals("is3D")) {
						 is3D = value;
					}
					else if (name.equals("language")) {
						 language = value;
					}
					else if (name.equals("area")) {
						 area = value;
					}
					else if (name.equals("time")) {
						 time = value;
					}
					else if (name.equals("directors")) {
						 directors = value;
					}
					else if (name.equals("actor")) {
						 actor  = value;
					}
					else if (name.equals("publish")) {
						 publish = value;
					}
					else if(name.equals("description")) {
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
						String uploadPath = this.getServletContext().getRealPath("/images/movie");
						
						//创建目的地文件
						//产生一个随机的文件名，这个可以有^o^
						String saveFileName = new SimpleDateFormat("yyyyMMddHHmmss").format( new Date() ) + (new Random().nextInt(90000)+10000);
						File destFile = new File(uploadPath + "/" + saveFileName+".jpg");
						String path = destFile.toString().substring(destFile.toString().length()-36, destFile.toString().length()).replace("\\", "/");
						//System.out.println("destFile"+destFile);
						//System.out.println("path"+path);
						//上传了post 使用新路径
						if(fieldName.equals("file"))
						{
							post = path;
							
						}
						
						//将上传文件写到目的地
						item.write(destFile);
						
				    }else {
						
					}
			}
										
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//将数据添加到数据库
		//添加影片数据
		boolean IS3D = true;
		int duration = Integer.parseInt(time);
		if(is3D.equals("2D")) {
			IS3D = false;
		}
		
		//导演数据
		String[] directorArr = directors.split(" ");		
		//演员数据
		String[] actorString = actor.split(" ");				 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date publishTime = null;
		try {
			publishTime = sdf.parse(publish);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Movie movieGot = movieService.selectMovieByName(movieName);
		if(movieGot != null) {//影片名已经存在，不能添加影片
			response.sendRedirect("addMovie.jsp");
			return;
		}
		Movie movie = 
			new Movie(movieName, post, movieTitle, IS3D, area, duration, language, publishTime, description);
		boolean insertFlag = movieService.insertMovie(movie, directorArr, actorString, movieTypeList);
		if(insertFlag) {//影片添加成功
			request.getRequestDispatcher("admin_addMovieSuccess.jsp").forward(request, response);
		}		
	}
	
	private MovieService movieService = new MovieService();	
}
