package com.ly.service.doproject;

import java.util.Map;

/**
* @ClassName: IDoProjectSerivce
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:03:55
*
*/
public interface IDoProjectService {

	/**
	 * @Title: selectByProject
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午3:17:45
	 * @param user
	 * @return
	*/
	Map<String, Object> selectByProject(String user);

}
