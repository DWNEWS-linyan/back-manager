package com.ly.service.sys;

import java.util.List;
import java.util.Map;

import com.ly.common.LyPage;
import com.ly.po.SysUser;
import com.ly.po.UserInfo;

/**
* @ClassName: ISysUserService
* @Description: 
* @author linyan
* @date 2017年7月12日 下午5:31:44
*
*/
public interface ISysUserService {

	LyPage<SysUser> selectPageSysUser(LyPage<SysUser> page);

	/**
	 * @Title: updateSysUserRole
	 * @author: linyan
	 * @Description: 
	 * @param roleIds
	 * @param sysuserid
	 * @return
	*/
	Map<String, Object> updateSysUserRole(String[] roleIds, String sysuserid);

	/**
	 * @Title: resetPass
	 * @author: linyan
	 * @Description: 
	 * @param id
	 * @return
	*/
	Map<String, Object> resetPass(Integer id);

	/**
	 * @Title: deleteSysUser
	 * @author: linyan
	 * @Description: 
	 * @param id
	 * @return
	*/
	Map<String, Object> deleteSysUser(Integer id);

	/**
	 * @Title: retrievePass
	 * @author: linyan
	 * @Description: 
	 * @param telPhoneCallBack
	 * @return
	*/
	Map<String, Object> retrievePass(String telPhoneCallBack);

	/**
	 * @Title: saveNewPass
	 * @author: linyan
	 * @Description: 
	 * @param newPass
	 * @param tel
	 * @return
	*/
	Map<String, Object> saveNewPass(String newPass, String tel);

	/**
	 * @Title: select2JSONData
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月11日 上午10:43:32
	 * @param p
	 * @return
	*/
	List<UserInfo> select2JSONData(String p);

	/**
	 * @Title: saveSysUser
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月11日 下午4:02:13
	 * @param sysUser
	 * @return
	*/
	Map<String, Object> saveSysUser(SysUser sysUser);

	/**
	 * @Title: selectByUserName
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月11日 下午4:41:38
	 * @param username
	 * @return
	*/
	boolean selectByUserName(String username);
	
}
