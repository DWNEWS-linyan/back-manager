package com.ly.service.sys;

import java.util.Map;

import com.ly.vo.AddOrEditUserInfoVo;

/**
* @ClassName: IUserInfoService
* @Description: 
* @author linyan
* @date 2017年7月12日 下午5:33:22
*
*/
public interface IUserInfoService {

	/**
	 * @Title: userInfoTableAjax
	 * @author: linyan
	 * @Description: 
	 * @param map
	 * @return
	*/
	Map<String, Object> userInfoTableAjax(Map<String, Object> map);

	/**
	 * @Title: addOrEditUserInfo
	 * @author: linyan
	 * @Description: 
	 * @param userInfoVo
	 * @return
	*/
	Map<String, Object> addOrEditUserInfo(AddOrEditUserInfoVo userInfoVo);

	/**
	 * @Title: editFind
	 * @author: linyan
	 * @Description: 
	 * @param id
	 * @return
	*/
	AddOrEditUserInfoVo editFind(Integer id);

	/**
	 * @Title: deleteUserInfo
	 * @author: linyan
	 * @Description: 
	 * @param id
	 * @return
	*/
	Map<String, Object> deleteUserInfo(Integer id);

}
