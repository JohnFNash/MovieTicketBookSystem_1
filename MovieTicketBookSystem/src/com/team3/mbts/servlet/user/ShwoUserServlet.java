/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.service.UserInfoService;
import com.team3.mbts.util.PageBean;
/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.servlet.common
 *FileName:CreateCodeServlet.java
 *Comments
 *JDK Version
 *Author:XiongWei
 *Create Date:2015-01-14 下午2:23:34
 *Modified By:
 *Modified Time:
 *What is Modified:
 *Version:
 */
public class ShwoUserServlet extends HttpServlet {
	private static final long serialVersionUID = -2225466138794786944L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<UserInfo> userinfoList = null;
		//实例化UserInfoService
		UserInfoService userInfoService = new UserInfoService();
		//获取下拉列表
	    String select = request.getParameter("select");
	    String key  = request.getParameter("key");
	    PageBean pageBean = null;
	  //如果第一次进来
	  	if(select==null){
	  		int total = userInfoService.countUserInfo();
	  		pageBean = new PageBean(total, "10", "1");
	  		userinfoList = userInfoService.selectUserList(pageBean);
	  		pageBean.setDataList(userinfoList);
	  	}
	  	else if("按编号查询".equals(select)){
	  		if(key !=null && key.matches("[0-9]{1,9}")){	  			  		
		  		//获取总的数目
		  		int key1=Integer.parseInt(key);
				int accestRecords =userInfoService.countUserInfo(key1);
				if(accestRecords>0){
					//分页包装类			
					pageBean = 	new PageBean(accestRecords, request.getParameter("pageSize"), request.getParameter("curPage"));
					userinfoList=userInfoService.getUserSelec(0,key, pageBean.getCurPage(),pageBean.getPageSize() );
					pageBean.setDataList(userinfoList);
				}
	  		} else {
	  			int total = userInfoService.countUserInfo();
		  		pageBean = new PageBean(total, "10", "1");
		  		userinfoList = userInfoService.selectUserList(pageBean);
		  		pageBean.setDataList(userinfoList);
	  		}

	    } else if("按用户名查询".equals(select)){
	    	//获取总的数目
			int accestRecords =userInfoService.countUserInfo(key);
			if(accestRecords>0){
				//分页包装类			
				pageBean = 	new PageBean(accestRecords, request.getParameter("pageSize"), request.getParameter("curPage"));
				//设置用户列表
				userinfoList=userInfoService.getUserSelec(1,key, pageBean.getCurPage(),pageBean.getPageSize() );
				pageBean.setDataList(userinfoList);
			}
			
	    }
		//查询的数据放入request中
		//UserInfo userinfo = (UserInfo) pageBean.getDataList().get(0);
		request.setAttribute("pageBean",pageBean);
		request.getRequestDispatcher("User_lb.jsp").forward(request, response);
		
		
	}

}
	
				

