/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.Seller;

import com.team3.mbts.mappers.SellerMapper;
import com.team3.mbts.util.PageBean;
import com.team3.mbts.util.SqlSessionUtil;

public class SellerService {	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-13 下午12:04:55
	 * Description: 验证商家登录信息
	 * @param account
	 * @param password
	 * @return
	 */
	public int selectSeller(String account,String password)
	{
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int row = sellerMapper.seletSeller(account, password);
		sqlSession.close();
		return row;
	}
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-13 下午6:48:24
	 * Description: 注册商家信息
	 * @param seller
	 * @return
	 */
	public int insertSeller(Seller seller)
	{
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int row = sellerMapper.insertSeller(seller);
		sqlSession.commit();
		sqlSession.close();
		return row;
		
	}
	
	public Seller selectSellerByAccount(String account)
	{
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		Seller seller = sellerMapper.selectSellerByAccount(account);
		sqlSession.close();		
		return seller;
	}
	/**
	 * 
	 * @author 
	 * Create Time : 2015-1-15 下午6:48:24
	 * Description: 商家信息显示
	 * @param seller
	 * @return
	 */
	public List<Seller> selectSellerList (PageBean p) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int curPage = p.getCurPage();
		int pageSize = p.getPageSize();
		RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		List<Seller> sellers = sellerMapper.selectSeller(rowBounds);
		sqlSession.close();
		return sellers;
	}
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	public int countSeller(){
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int count = sellerMapper.countSeller();
		sqlSession.close();
		return count;
	}
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	public int countSeller(String account){
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int count  = sellerMapper.countSellerct(account);
		sqlSession.close();
		return count;
	}
	
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	public int countSeller(int sellerId ) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int count  = sellerMapper.countSeller2(sellerId);
		sqlSession.close();
		return count;
	}
	
	/**
	 *Create Time:2015-1-15 下午5:15:51
	 *Description:删除商家
	 *@param userId 商家的编号
	 *@return 删除成功返回1，否者返回0
	 */
	public int removeSeller(int SellerId) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int deleteFlag = sellerMapper.deleteSeller(SellerId);
		sqlSession.commit();
		sqlSession.close();
		return deleteFlag ;
	}
	/**
	 *
	 *Create Time:2015-1-20 上午11:03:03
	 *Description:根据商家编号查询
	 *@param 商家编号
	 *@return
	 */
	public Seller getSellerByID(int sellerId) {
		return null;
		
	}
	/**
	 * 显示商家列表,分页显示
	 * @param 显示当前页，下一页
	 * @return 商家列表
	 */
	public List<Seller> getSellerSelec(int type,String consultPut, int page, int pageSize ) {
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		RowBounds rowBounds = new RowBounds((page-1)*pageSize, pageSize);
		List<Seller> sellers = sellerMapper.getsellerSelec(type,consultPut, rowBounds);
		sqlSession.close();
		return sellers;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-21 下午4:19:19
	 * Description:修改密码
	 * @param password
	 * @param account
	 * @return int
	 */
	public int updatePwd(String password,String account)
	{
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int row = sellerMapper.updatePwd(password, account);
		sqlSession.commit();
		sqlSession.close();
		return row;
	}
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-22 下午3:47:21
	 * Description:为新注册的商家添加影院
	 * @param cinemaId
	 * @param sellerId
	 * @return int
	 */
	/*public int updateCinemaId(int cinemaId,int sellerId)
	{
		//获取SqlSession对象
		SqlSession	sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		SellerMapper sellerMapper = sqlSession.getMapper(SellerMapper.class);
		int row = sellerMapper.updateCinemaId(cinemaId, sellerId);
		sqlSession.commit();
		sqlSession.close();
		return row;
	}*/
}
