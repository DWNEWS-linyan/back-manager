package com.ly.service.notepad;

import java.util.Map;

/**
* @ClassName: INotepadService
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:06:45
*
*/
public interface INotepadService {

	/**
	 * @Title: selectByUser
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午3:17:23
	 * @param user
	 * @return
	*/
	Map<String, Object> selectByUser(String user);

}
