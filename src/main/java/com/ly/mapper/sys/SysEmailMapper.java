package com.ly.mapper.sys;

import java.util.List;

import com.ly.po.SysEmail;

public interface SysEmailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysEmail record);

    int insertSelective(SysEmail record);

    SysEmail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysEmail record);

    int updateByPrimaryKey(SysEmail record);

	/**
	 * @Title: selectByUserInfoId
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午7:29:19
	 * @param id
	 * @return
	*/
	List<SysEmail> selectByUserInfoId(Integer id);
}