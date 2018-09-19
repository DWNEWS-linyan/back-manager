package com.ly.mapper.sys;

import com.ly.po.SysRoleTree;

public interface SysRoleTreeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleTree record);

    int insertSelective(SysRoleTree record);

    SysRoleTree selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleTree record);

    int updateByPrimaryKey(SysRoleTree record);

	/**
	 * @Title: deleteByRoleId
	 * @author: linyan
	 * @Description: 
	 * @param valueOf
	*/
	void deleteByRoleId(Integer roleid);

	/**
	 * @Title: deleteBySysTree
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月17日 下午1:40:42
	 * @param id
	*/
	void deleteBySysTree(Integer id);
}