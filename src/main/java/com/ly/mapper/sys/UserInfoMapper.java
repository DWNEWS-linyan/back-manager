package com.ly.mapper.sys;

import java.util.List;
import java.util.Map;

import com.ly.po.SysRole;
import com.ly.po.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	/**
	 * @Title: userInfoTableAjaxPage
	 * @author: linyan
	 * @Description: 
	 * @param pare
	*/
    
    List<SysRole> userInfoTableAjaxPage(Map<String, Object> pare);
}