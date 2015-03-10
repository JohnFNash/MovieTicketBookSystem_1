/*
 *www.dyr.com
 *Copyright(c) 2014 All Rights Reserved.
 */
package com.team3.mbts.servlet.user;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.mbts.entity.Seller;
import com.team3.mbts.service.SellerService;
import com.team3.mbts.util.PageBean;

public class ShwoSellerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//实例化SellerService
		SellerService sellerService = new SellerService();
		List<Seller> sellerList;
		//获取下拉列表
		  String conultSelect= request.getParameter("consultSelect");
		String consultPut= request.getParameter("consultPut");
		PageBean pageBean =null;
		//如果第一次进来
		if(conultSelect==null){
			int total = sellerService.countSeller();
			pageBean = new PageBean(total, "10", "1");
			sellerList = sellerService.selectSellerList(pageBean);
			pageBean.setDataList(sellerList);
		}
		//按商家编号，名称判断
		 if("按商家编号查询".equals(conultSelect)){
			if(consultPut !=null && consultPut.matches("[0-9]{1,9}")){
				int consulPut1=Integer.parseInt(consultPut);
				int total = sellerService.countSeller(consulPut1);
				 pageBean = new PageBean(total, request.getParameter("pageSize"), request.getParameter("curPage"));
				//Seller byid	=sellerService.getSellerByID(consulPut1);
				 sellerList = sellerService.getSellerSelec(0, consultPut, pageBean.getCurPage(), pageBean.getPageSize());	
				 pageBean.setDataList(sellerList);  		
				/*if(byid==null){
					 pageBean = new PageBean(1, "1", "1");
					  sellerList=sellerService.getSellerSelec(0,consultPut, pageBean.getCurPage(),pageBean.getPageSize());
		    		pageBean.setDataList(sellerList);  		
				}*/
			} else {
				int total = sellerService.countSeller();
		  		pageBean = new PageBean(total, "10", "1");
		  		sellerList = sellerService.getSellerSelec(1, consultPut, pageBean.getCurPage(), pageBean.getPageSize());
		  		pageBean.setDataList(sellerList);
			}
				
		}else if("按商家名查询".equals(conultSelect)){
			//获取总的数目
			int accestRecords =sellerService.countSeller(consultPut);
			if(accestRecords>0){
				//分页包装类
				 pageBean = new PageBean(accestRecords, request.getParameter("pageSize"), request.getParameter("curPage"));
				//设置商家用户列表
			    sellerList=sellerService.getSellerSelec(1,consultPut, pageBean.getCurPage(),pageBean.getPageSize());
				pageBean.setDataList(sellerList);	
			}
		}
		//查询的数据放入request中
		//Seller seller = (Seller) pageBean.getDataList().get(0);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("Consult_lb.jsp").forward(request, response);
	}

}
