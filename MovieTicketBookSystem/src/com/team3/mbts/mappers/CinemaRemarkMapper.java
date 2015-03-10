/*
*www.dyr.com
*Copyright (c) 2014 All Rights Revered
*/

/**
 * 影院评论刀类
 */
package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.CinemaRemark;

/**
 *Project:moviebookticketsystem
 *Package:com.team3.mbts.mappers
 *FileName:CineMaRemarkDAO.java
 *Comments(意见):
 *JDK Version
 *Author:谢洪章
 *Create Date:2015-1-14下午3:50:41
 * @author Administrator
 *
 */
public interface CinemaRemarkMapper {
	/**
	 * 根据商家id查询影院评论的方法
	 * @author 谢洪章
	 * Create Time:2015-1-14下午6:05:28
	 * returnType:List<CinemaRemark>
	 * @param id
	 * @param rowBounds
	 * @return List<CinemaRemark>
	 */
	@Select("select * from TB_CINEMA_REMARK and STATUS=2 order by time desc")
	public List<CinemaRemark> selectCinemaRemarksById(int id, RowBounds rowBounds);

	/**
	 * 查询指定数目的对给定影院的评价
	 *  @author 徐晓聪
	 *  Create Time: 2015-1-23 下午6:36:26
	 *  Description:
	 *  @param cinemaId 影院编号
	 *  @param size 一次加载的评论的数目
	 *  @param prevRecordsCount 已经加载的评论的数目
	 *  @return
	 */
	@Select("select top(#{size}) * from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and ID not in (select top(#{prevRecordsCount}) ID from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and STATUS=2 order by TIME desc) and STATUS=2 order by TIME desc")
	@ResultMap("com.team3.mbts.mappers.CinemaRemarkMapper.CinemaRemarkResult")
	public List<CinemaRemark> selectCinemaRemarksByCinemaId(@Param("cinemaId")int cinemaId, @Param("size")int size, @Param("prevRecordsCount")int prevRecordsCount);

	/**
	 * 
	 * @author LongJun 
	 * Create Time : 2015-1-23 上午10:05:37
	 * Description:根据影院id 查询影院评价列表 并分页
	 * @param rowBounds
	 * @param cinemaId
	 * @param time
	 * @return List<CinemaRemark>
	 */
	@Select("select * from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and TIME >=#{time1} and TIME <=#{time2} and STATUS=2 order by TIME desc")
	@ResultMap("com.team3.mbts.mappers.CinemaRemarkMapper.CinemaRemarkResult")
	public List<CinemaRemark> selectCinemaRemarks(RowBounds rowBounds, @Param("cinemaId")int cinemaId,@Param("time1")String time1,@Param("time2")String time2);
	
	/**
	 * 
	 * @author LongJun
	 * Create Time : 2015-1-23 上午11:43:31
	 * Description:查询分页的总记录数
	 * @param cinemaId
	 * @param time1
	 * @param time2
	 * @return int 
	 */
	@Select("select count(*) from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and TIME >=#{time1} and TIME <=#{time2} and STATUS=2")
	public int selectCount(@Param("cinemaId")int cinemaId,@Param("time1")String time1,@Param("time2")String time2);
	
	/**
	 * 加载对指定影院的指定数目的评论
	 * @author 徐晓聪
	 * Create Time : 2015-1-25 下午7:41:31
	 * Description:
	 * @param cinemaId 影院编号
	 * @param numToGet 要获取的评论的数目
	 * @param prevRecords 之前已经加载的评论数
	 * @return
	 */
	@Select("select top(#{numToGet}) * from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and ID not in (select top(#{prevRecords}) ID from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and STATUS=2 order by time desc)  and STATUS=2 order by time desc")
	@ResultMap("com.team3.mbts.mappers.CinemaRemarkMapper.CinemaRemarkResult")
	public List<CinemaRemark> selectSpecifiedNumCinemaRemarks(@Param("cinemaId")int cinemaId, @Param("numToGet")int numToGet, @Param("prevRecords")int prevRecords);
	
	/**
	 * 加载未过滤的对指定影院的指定数目的评论
	 * @author 徐晓聪
	 * Create Time : 2015-3-2 下午5:22:31
	 * Description:
	 * @param cinemaId 影院编号
	 * @param numToGet 要获取的评论的数目
	 * @param prevRecords 之前已经加载的评论数
	 * @return
	 */
	@Select("select top(#{numToGet}) * from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} and ID not in (select top(#{prevRecords}) ID from TB_CINEMA_REMARK where CINEMA_ID=#{cinemaId} order by time desc) order by time desc")
	@ResultMap("com.team3.mbts.mappers.CinemaRemarkMapper.CinemaRemarkResult")
	public List<CinemaRemark> selectSpecifiedNumNoFilterCinemaRemarks(@Param("cinemaId")int cinemaId, @Param("numToGet")int numToGet, @Param("prevRecords")int prevRecords);
	
	/**
	 * 发表对影院的评论
	 * @author 徐晓聪
	 * Create Time : 2015-1-25 下午11:46:31
	 * Description:
	 * @param cinemaRemark
	 * @return
	 */
	@Insert("insert into TB_CINEMA_REMARK (CINEMA_ID, USER_ID, [CONTENT], GRADE, TIME) values(#{cinema.cinemaId}, #{user.userId}, #{content}, #{grade}, #{time}) ")
	public int insertCinemaRemark(CinemaRemark cinemaRemark);
	
	/**
	 * 统计用户发表的影院评价的数目
	 * @author 徐晓聪
	 * Create Time : 2015-2-17 下午5:16:32
	 * Description:
	 * @param userId 用户编号
	 * @return
	 */
	@Select("select count(*) from TB_CINEMA_REMARK where USER_ID=#{userId}")
	public int selectUserCinemaRemarkCount(int userId);
	
	/**
	 * 分页查询用户发表的影院评价
	 * @author 徐晓聪
	 * Create Time : 2015-2-17 下午5:17:23
	 * Description:
	 * @param userId 用户编号
	 * @param rowBounds
	 * @return
	 */
	@Select("select ID, CINEMA_ID, SUBSTRING([CONTENT], 0, 50) as [CONTENT], GRADE, TIME, STATUS from TB_CINEMA_REMARK where USER_ID=#{userId}")
	@ResultMap("com.team3.mbts.mappers.CinemaRemarkMapper.CinemaRemarkResult")
	public List<CinemaRemark> selectUserCinemaRemark(int userId, RowBounds rowBounds);
	
	/**
	 * 审核影院评论
	 * @author 徐晓聪
	 * Create Time : 2015-3-2 下午3:41:31
	 * Description:
	 * @param cinemaRemarkId 影院评论编号
	 * @param type 2表示审核通过，3表示审核不通过
	 * @return
	 */
	@Update("update TB_CINEMA_REMARK set status=#{status} where ID=#{cinemaRemarkId} ")
	public int updateCinemaRemark(@Param("cinemaRemarkId")int cinemaRemarkId, @Param("status")byte type);
}
