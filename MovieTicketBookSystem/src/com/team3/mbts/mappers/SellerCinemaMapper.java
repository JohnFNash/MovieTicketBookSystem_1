package com.team3.mbts.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:SellerCinemaMapper.java
 * Comments:商家-影院关联信息操作DAO
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-28 下午8:04:32
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface SellerCinemaMapper {
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-28 下午8:07:51
	 * Description:添加商家-影院关联信息
	 * @param sellerId 商家编号
	 * @param cinemaId 影院编号
	 * @return
	 */
	@Insert("insert into TB_SELLER_CINEMA values(#{sellerId}, #{cinemaId})")
	public int insertSellerCinema(@Param("sellerId")int sellerId, @Param("cinemaId")int cinemaId);
}
