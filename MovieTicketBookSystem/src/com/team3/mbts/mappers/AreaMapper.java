/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.team3.mbts.entity.Area;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.mappers
 * FileName:AreaMapper.java
 * Comments:地区操作DAO
 * JDK Version:
 * Author : 徐晓聪
 * Create Date:2015-2-28 下午3:00:12
 * Modified By : 
 * Modified Time:
 * What is Modified:
 * Version:
 */
public interface AreaMapper {
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-28 下午3:03:23
	 * Description:查询省、直辖市级行政区域
	 * @return
	 */
	@Select("select * from TB_AREA WHERE AREA_LEVEL=1")	
	@ResultMap("com.team3.mbts.mappers.AreaMapper.AreaResult")
	public List<Area> selectFirstLevelAreas();
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-28 下午3:03:23
	 * Description:查询省、直辖市级行政区域下的第二级划分
	 * @param firstLevelId 省、直辖市级行政区域的编号
	 * @return
	 */
	@Select("select * from TB_AREA WHERE PARENT_ID=#{firstLevelId}")
	@ResultMap("com.team3.mbts.mappers.AreaMapper.AreaResult")
	public List<Area> selectSecondLevelAreas(int firstLevelId);
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-28 下午3:03:23
	 * Description:查询市辖区、县下的第三级划分
	 * @param secondLevelId 市辖区、县级行政区域的编号
	 * @return
	 */
	@Select("select * from TB_AREA WHERE PARENT_ID=#{secondLevelId}")
	@ResultMap("com.team3.mbts.mappers.AreaMapper.AreaResult")
	public List<Area> selectThirdLevelAreas(int secondLevelId);
	
	/**
	 * 
	 * @author 徐晓聪
	 * Create Time : 2015-2-28 下午7:18:23
	 * Description:查询给定行政区域的父级行政区域
	 * @param areaId 政区域编号
	 * @return
	 */
	@Select("select * from TB_AREA WHERE AREA_ID= (select PARENT_ID from TB_AREA where AREA_ID=#{areaId})")
	@ResultMap("com.team3.mbts.mappers.AreaMapper.AreaResult")
	public Area selectParentLevelAreas(int areaId);
}
