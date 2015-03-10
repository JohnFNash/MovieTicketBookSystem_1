/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.team3.mbts.entity.Order;
import com.team3.mbts.entity.OrderTicket;
import com.team3.mbts.entity.Ticket;
import com.team3.mbts.mappers.OrderMapper;
import com.team3.mbts.mappers.OrderTicketMapper;
import com.team3.mbts.mappers.TicketMapper;
import com.team3.mbts.util.SqlSessionUtil;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.service
 * FileName:OrderService.java
 * Comments:订单信息服务类
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-23 下午4:24:23
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public class OrderService {		 		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-23 下午4:28:52
		 * Description:根据商家影院id查询 订单列表并分页
		 * @param pageSize
		 * @param curPage
		 * @param cinemaId
		 * @param time1
		 * @param time2
		 * @return orderList
		 */
		public List<Order> selectOrderList(int pageSize,int curPage,int cinemaId,String time1,String time2)
		{
			SqlSession sqlSession =  SqlSessionUtil.getSqlSession();
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
			List<Order> orderList = orderMapper.selectOrderList(rowBounds, cinemaId, time1, time2);
			sqlSession.close();
			return orderList;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-23 下午4:30:22
		 * Description:根据商家影院id查询 订单列表 分页总记录数
		 * @param cinemaId
		 * @param time1
		 * @param time2
		 * @return int
		 */
		public int orderCount(int cinemaId,String time1,String time2){
			SqlSession sqlSession =  SqlSessionUtil.getSqlSession();
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			int count  = orderMapper.selectCount(cinemaId, time1, time2);
			sqlSession.close();
			return count;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-23 下午5:35:02
		 * Description:根据订单号查询订单
		 * @param orderId
		 * @return order
		 */
		public Order selectOrderById(int orderId)
		{
			SqlSession sqlSession =  SqlSessionUtil.getSqlSession();
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			Order order = orderMapper.selectOrderById(orderId);
			sqlSession .close();
			return order;
		}
		
		/**
		 * 
		 * @author LongJun
		 * Create Time : 2015-1-23 下午6:08:59
		 * Description:按影片名关键字模糊查询订单
		 * @param key
		 * @return 
		 */
		public List<Order> selectOrListByMKey(String key,int cinemaId,String time1,String time2){
			SqlSession sqlSession =  SqlSessionUtil.getSqlSession();
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			List<Order> oList = orderMapper.selectOrListByMKey(key,cinemaId,time1,time2);
			sqlSession.close();
			return oList;
		}
		
		/**
		 * 生成订单
		 * @author 徐晓聪
		 * Create Time : 2015-1-26 下午12:58:13
		 * Description:
		 * @param order
		 * @return
		 */
		public boolean insertOrder(Order order, List<Ticket> ticketList, List<OrderTicket> orderTicketList) {
			TransactionFactory factory = new JdbcTransactionFactory();			
			SqlSession sqlSession =  SqlSessionUtil.getSqlSession();
			Transaction transaction = factory.newTransaction(sqlSession.getConnection());			
			
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			TicketMapper ticketMapper = sqlSession.getMapper(TicketMapper.class);
			OrderTicketMapper orderTicketMapper = sqlSession.getMapper(OrderTicketMapper.class);
			
			try {
				//插入订单			
				int insertFlag = orderMapper.insertOrder(order);
				if(insertFlag > 0) {//插入成功				
					//插入票信息
					boolean flag2 = true;
					for(Ticket ticket: ticketList) {//循环插入票信息						
						int tmpFlag =  ticketMapper.insertTicket(ticket);
						if(tmpFlag == 0) {//插入失败，回滚
							flag2 = false;
							throw new Exception();					
						}
					}
					
					if(flag2) {//如果订单、票均插入成功，则插入它们的关联信息
						long orderId = order.getId();//获取订单编号
						for(int i=0; i<orderTicketList.size(); i++) {//为每个座位设置orderId,ticketId
							OrderTicket orderTicket = orderTicketList.get(i);
							orderTicket.setOrderId(orderId);
							orderTicket.setTicketId(ticketList.get(i).getId());
						}
						
						//插入订单-票关联信息
						for(OrderTicket orderTicket : orderTicketList) {
							int flag3 = orderTicketMapper.insertOrderTicket(orderTicket);
							if(flag3 == 0) {//插入失败
								throw new Exception();
							}
						}
						
					} else {//订单插入失败,事务回滚
						throw new Exception();
					}
					
				} else {//订单插入失败,事务回滚
					throw new Exception();

				}
			} catch (Exception ex) {
			    try {
				   transaction.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			    ex.printStackTrace();
			} finally {
			   try {
				   transaction.commit();
				   
				   transaction.close();
			   } catch (SQLException e) {
				   e.printStackTrace();
			   }
			}			
			
			return false;
		}
		
		/**
		 * 获取给定用户的所有订单
		 *  @author 徐晓聪
		 *  Create Time: 2015-1-27 下午6:26:34
		 *  Description:
		 *  @param userId 用户编号
		 *  @param curPage 当前页
		 *  @param pageSize 页大小
		 *  @return
		 */
		public List<Order> selectOrdersForUser(int userId, int curPage, int pageSize) {
			SqlSession sqlSession =  SqlSessionUtil.getSqlSession();
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
			List<Order> orderList = orderMapper.selectOrdersForUser(userId, rowBounds);
			sqlSession.close();
			return orderList;
		}
		
		
		/**
		 * 统计用户的订单的数目
		 * @param userId 用户编号
		 * @return
		 */
		public int countOrdersForUser(int userId) {
			SqlSession sqlSession =  SqlSessionUtil.getSqlSession();
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			int orderCount = orderMapper.countOrdersForUser(userId);
			sqlSession.close();
			return orderCount;
		}
}
