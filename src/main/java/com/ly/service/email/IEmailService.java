package com.ly.service.email;

import java.util.Map;

/**
* @ClassName: IEmailService
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:05:32
*
*/
public interface IEmailService {

	/**
	 * @Title: selectByEmail
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午3:17:39
	 * @param user
	 * @return
	*/
	Map<String, Object> selectByEmail(String user);

}
