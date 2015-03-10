/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.Seller;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers 商家信息数据库操作类
 * FileName:SellerDAO.java
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-13 上午11:47:45
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface SellerMapper {
	/**
	 *  查询商家是否存在
	 * @author LongJun
	 * Create Time : 2015-1-13 上午11:56:34
	 * Description:查询商家是否存在
	 * @param account
	 * @param password
	 * @return 1存在  0不存在
	 */
	@Select("select count(*) from TB_SELLER where ACCOUNT=#{account} and PASSWORD=#{password} and IS_EXIST=1")
	public int seletSeller(@Param("account")String account,@Param("password")String password);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-13 下午3:08:15
	 * Description:	注册商家
	 * @param seller
	 * @return	1注册成功
	 */
	@Insert("insert into TB_SELLER (ACCOUNT,PASSWORD) values(#{account},#{password})")
	@Options(useGeneratedKeys=true,keyProperty="sellerId")
	public int insertSeller(Seller seller);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-14 上午10:00:52
	 * Description:查找商家信息
	 * @param account
	 * @return seller
	 */
	@Select("select * from TB_SELLER where ACCOUNT=#{account}")
	@ResultMap("com.team3.mbts.mappers.SellerMapper.SellerResult")
	public Seller selectSellerByAccount(String account);
	/**
	 * 
	 * @author X
	 * Create Time : 2015-1-15 上午15:00:52
	 * Description:商家信息显示
	 * @param rowBounds
	 * @return seller
	 */
	@Select("select * from TB_SELLER where IS_EXIST=1")
	@ResultMap("com.team3.mbts.mappers.SellerMapper.SellerResult")
	public  List<Seller> selectSeller(RowBounds rowBounds);
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	@Select("select count(*) from TB_SELLER where  IS_EXIST=1 and ACCOUNT like '%${account}%' ")
	public int countSellerct(@Param("account")String account);
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	@Select("select count(*) from TB_SELLER where IS_EXIST=1")
	public int countSeller();
	
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	@Select("select count(*) from TB_SELLER where IS_EXIST=1 and SELLER_ID like '%${sellerId}%' ")
	public int countSeller2(@Param("sellerId")int sellerId );
	
	/**
	 *Create Time:2015-1-15 下午5:15:51
	 *Description:删除商家
	 *@param userId 商家的编号
	 *@return 删除成功返回1，否者返回0
	 */
	@Update("update TB_SELLER set IS_EXIST=0 where SELLER_ID=#{sellerId}")
	public  int deleteSeller(int sellerId);
	/**
    *
    *Create Time:2015-1-20 上午10:50:58
    *Description:根据商家编号查找用户的信息
    *@param userID 商家编号
    *@return 
    */
	@Select("select * from TB_SELLER where SELLER_ID=#{sellerId}")
	public int getSellerByID(int sellerId);
	
	/**
	 * 显示商家列表,分页显示
	 * @param type 0表示模糊匹配商家账号，1表示模糊匹配商家用户名
	 * @param consultPut 输入的筛选关键字 
	 * @param rowBounds
	 * @return 商家列表
	 */	
	public List<Seller> getsellerSelec(@Param("type") int type,@Param("account")String consultPut, RowBounds rowBounds);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-21 下午4:17:32
	 * Description:修改用户密码
	 * @param password
	 * @param account
	 * @return int
	 */
	@Update("update TB_SELLER set PASSWORD=#{password} where ACCOUNT=#{account}")
	public int updatePwd(@Param("password")String password,@Param("account")String account);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-22 下午3:45:36
	 * Description:为新注册的商家添加影院
	 * @param cinemaId
	 * @param sellerId
	 * @return int 
	 */
	@Update("update TB_SELLER set CINEMA_ID=#{cinemaId} where SELLER_ID=#{sellerId}")
	public int updateCinemaId(@Param("cinemaId")int cinemaId,@Param("sellerId")int sellerId);
}
