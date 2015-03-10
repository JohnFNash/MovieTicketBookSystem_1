package com.team3.mbts.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.team3.mbts.entity.Director;

/**
 * 导演数据库操作接口
 * @author 徐晓聪
 *
 */
public interface DirectorMapper {
	/**
	 * 插入导演信息(如果已经存在则不插入)
	 * @param director 导演信息
	 * @return 受影响的记录条数
	 */
	/*@Insert(" if not exists (select COUNT(*) from TB_DIRECTOR where DIRECTOR_NAME=#{directorName}) " 
			+ " insert into TB_DIRECTOR values(#{directorName})")
	*/
	@Insert(" insert into TB_DIRECTOR select #{directorName} " +
			"where not exists (select * from TB_DIRECTOR where DIRECTOR_NAME=#{directorName})")
	@Options(useGeneratedKeys=true, keyProperty="directorId")
	public int insertDirector(Director director);
}
