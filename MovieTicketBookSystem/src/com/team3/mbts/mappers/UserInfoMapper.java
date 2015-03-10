/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.team3.mbts.entity.UserInfo;

/**
 * 
 *project:moviebookticketsystem
 *Package:com.team3.mbts.mappers	
 *FileName:UserInfoDAO.java	
 *Comments:	
 *JDK Version:
 *Author:XiongWei
 *Create Date:2015-1-13 上午8:57:14
 *Modified By:Casper
 *Modified Time:
 *What is Modified:
 *Version:
 */
public interface UserInfoMapper {
	/**
	 * 验证用户登录信息
	 *  @param userInfo 用户信息
	 *  @return 用户登录验证通过，返回1；否则返回0
	 */
	@Select("select count(*) from TB_USERINFO where IS_ADMIN=#{isAdmin} and ACCOUNT=#{account} and PASSWORD=#{password} and IS_EXIST=1")
	public int isUserExists(UserInfo userInfo);
	
	/**
	 * 管理员密码修改
	 *@param userInfo 用户信息
	 *@return 密码更新成功返回1；否则返回0
	 */
	@Update("update  TB_USERINFO set PASSWORD=#{password} where  ACCOUNT=#{account} ")
	public int updatePassWord(UserInfo userInfo);
	
	/**
	 * 添加普通用户
	 * @param userInfo 用户信息
	 * @return 用户添加成功，返回1；否则返回0
	 */
	@Insert("insert into TB_USERINFO (ACCOUNT, PASSWORD) values(#{account}, #{password})")
	public int addUser(UserInfo userInfo);
	
	/**
	 * 查看注册的用户名是否已经被占用
	 * @param account 用户名
	 * @return 如果用户名已经存在，返回1；否则，返回0
	 */
	@Select("select count(*) from TB_USERINFO where IS_ADMIN=0 and ACCOUNT=#{account}")
	public int isUserNameExists(String account);
	
	/**
	 * 显示用户列表,分页显示
	 * @param rowBounds
	 * @return 用户列表
	 */
	@Select("select * from TB_USERINFO where IS_ADMIN=0 and IS_EXIST=1 order by USER_ID")
	@ResultMap("com.team3.mbts.mappers.UserInfoMapper.UserInfoResult")
	public  List<UserInfo> selectUser(RowBounds rowBounds);
	
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	@Select("select count(*) from TB_USERINFO where IS_ADMIN=0 and IS_EXIST=1 and ACCOUNT like '%${account}%' ")
	public int countUseron(@Param("account")String account);
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	//@SelectProvider(type=UserInfoDAOSqlprovider.class,method="countUseronSql")
	@Select("select count(*) from TB_USERINFO where IS_ADMIN=0 and IS_EXIST=1 and USER_ID like '%${USER_ID}%' ")
	public int countinfo(@Param("USER_ID") int userId );
	
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	@Select("select count(*) from TB_USERINFO where IS_ADMIN=0 and IS_EXIST=1")
	public int countUserInfo();

	/**
	 *Create Time:2015-1-15 下午5:15:51
	 *Description:删除用户
	 *@param userId 用户的编号
	 *@return 删除成功返回1，否者返回0
	 */
	@Update("update TB_USERINFO set IS_EXIST=0 where USER_ID=#{userId}")
	public int deleteUser(int userId);
	
	/**
    *
    *Create Time:2015-1-19 下午3:01:58
    *Description:根据用户编号查找用户的信息
    *@param userID 用户编号
    *@return 
    */
	@Select("select * from TB_USERINFO  where USER_ID=#{userId}")	
	@ResultMap("com.team3.mbts.mappers.UserInfoMapper.UserInfoResult")
	public int getUserByID(int userId);
	
	/**
    * 根据用户账号查找用户的信息
    * Create Time:2015-1-26 上午0:31:58
    * Description:
    * @param userID 用户账号
    * @return 
    */
	@Select("select * from TB_USERINFO  where ACCOUNT=#{account}")
	@ResultMap("com.team3.mbts.mappers.UserInfoMapper.UserInfoResult")
	public UserInfo getUserByAccount(String account);
	
	/**
	 * 显示用户列表,分页显示
	 * @param type 
	 * @param key 搜索关键字
	 * @param rowBounds
	 * @return 用户列表
	 */
	//@SelectProvider(type=UserInfoDAOSqlprovider.class,method="getUserSelec")
	//@ResultMap("com.team3.mbts.mappers.UserInfoMapper.UserInfoResult")
	/*public  List<UserInfo> getUserSelec(@Param("type")int type, @Param("account")String key, @Param("page")int page, @Param("pageSize")int pageSize );*/
	public List<UserInfo> getUserSelec(@Param("type")int type, @Param("account")String key,RowBounds rowBounds);
	
}

