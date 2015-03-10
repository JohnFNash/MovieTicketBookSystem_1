/*
*	www.dyr.com
*   Copyright (c) 2014 All Rights Reserved.
*/
package com.team3.mbts.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.team3.mbts.entity.UserInfo;
import com.team3.mbts.mappers.UserInfoMapper;
import com.team3.mbts.util.PageBean;
import com.team3.mbts.util.SqlSessionUtil;

public class UserInfoService {
	
	/**
	 * 验证用户登录信息
	 *  @param userInfo 用户信息
	 *  @return 用户登录验证通过，返回1；否则返回0
	 */
	public int loginValidate(UserInfo userInfo){
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int row = userInfoMapper.isUserExists(userInfo);
		sqlSession.close();
		return row;
	}
	/**
	 * 用户注册
	 *  @param userInfo 用户信息
	 *  @return 用户注册成功返回1；否则返回0
	 */
	public int register(UserInfo userInfo){
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int updateFlag = userInfoMapper.addUser(userInfo);//添加用户
		sqlSession.commit();
		sqlSession.close();
		return updateFlag;
	}
	
	/**
	 * 用户更新密码
	 *  @param userInfo 用户信息
	 *  @return 密码修改成功返回；否则返回0
	 */
	public int update(UserInfo userInfo){
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int updateSuccess = userInfoMapper.updatePassWord(userInfo);
		sqlSession.commit();
		sqlSession.close();
		return updateSuccess;
	}
	
	/**
	 * 查看注册的用户名是否已经被占用
	 * @param account 用户
	 * @return 如果用户名已经存在，返回1；否则，返回0
	 */
	public int isUserNameExists(String account) {
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int row = userInfoMapper.isUserNameExists(account);
		sqlSession.close();
		return row;
	}
	/**
	 * 显示用户列表,分页显示
	 * @param 显示当前页，下一页
	 * @return 用户列表
	 */
	public List<UserInfo> selectUserList(PageBean p){
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int curPage = p.getCurPage();
		int pageSize = p.getPageSize();
		RowBounds rowBounds = new RowBounds((curPage-1)*pageSize, pageSize);
		List<UserInfo> userInfos = userInfoMapper.selectUser(rowBounds);
		sqlSession.close();
		return userInfos;
	}
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	public int countUserInfo(){
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int count = userInfoMapper.countUserInfo();
		sqlSession.close();
		return count;
	}
	
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	public int countUserInfo(int key1){
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int count = userInfoMapper.countinfo(key1);
		sqlSession.close();
		return count;
	}
	/**
	 * 显示记录总数
	 * @param 显示当前页，下一页
	 * 
	 */
	public int countUserInfo(String account){
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int count = userInfoMapper.countUseron(account);
		sqlSession.close();
		return count;
	}
	
	/**
	 *Create Time:2015-1-15 下午5:15:51
	 *Description:删除用户
	 *@param userId 用户的编号
	 *@return 删除成功返回1，否者返回0
	 */
	public int removeUser(int userId) {
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		int deleteFlag = userInfoMapper.deleteUser(userId);
		sqlSession.commit();
		sqlSession.close();
		return deleteFlag ;
	}

    /**
     *
     *Create Time:2015-1-19 下午3:01:58
     *Description:根据用户编号查找用户的信息
     *@param userID 用户编号
     *@return 
     */
	public UserInfo getUserByID(int userId) {
		return null;		
	}
	
	/**
	 * 根据用户账号查找用户的信息
    * Create Time:2015-1-26 上午0:31:58
    * Description:
	 * @param account 用户账号
	 * @return
	 */
	public UserInfo getUserByAccount(String account) {
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		UserInfo userInfo = userInfoMapper.getUserByAccount(account);
		sqlSession.close();
		return userInfo;
	}
	
	/**
	 * 显示用户列表,分页显示
	 * @param 显示当前页，下一页
	 * @return 用户列表
	 */
	public List<UserInfo> getUserSelec(int type,String key, int page, int pageSize ) {
		//获取SqlSession对象
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		//获取UserInfoDAO对象
		UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
		RowBounds rowBounds = new RowBounds(pageSize*(page-1), pageSize);
		List<UserInfo> userInfos = userInfoMapper.getUserSelec(type,key, rowBounds);
		sqlSession.close();
		return userInfos;
	}
	
}
