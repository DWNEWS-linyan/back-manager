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

	/**
	 * @Title: selectByObject
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月11日 上午10:59:04
	 * @param userInfo
	 * @return
	*/
	List<UserInfo> selectByObject(UserInfo userInfo);

	
	/**
	 * @Title: selectLikeByObject
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月11日 下午2:47:21
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> selectLikeByObject(UserInfo userInfo);
	
	List<UserInfo> selectBySysUser(Map<String, Object> map);
}