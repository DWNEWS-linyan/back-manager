package com.ly.service.notice;

import java.util.Map;

import com.ly.vo.NoticeVo;

/**
* @ClassName: INoticeService
* @Description: 
* @author linyan
* @date 2017年12月15日 上午11:19:16
*
*/
public interface INoticeService {

	/**
	 * @Title: addOrEditNotice
	 * @author: linyan
	 * @Description: 
	 * @date 2017年12月15日 上午11:27:02
	 * @param noticeVo
	 * @return
	*/
	Map<String, Object> addOrEditNotice(NoticeVo noticeVo);

	/**
	 * @Title: deleteNotice
	 * @author: linyan
	 * @Description: 
	 * @date 2017年12月15日 上午11:28:48
	 * @param id
	 * @return
	*/
	Map<String, Object> deleteNotice(Integer id);

	/**
	 * @Title: findOneNotice
	 * @author: linyan
	 * @Description: 
	 * @date 2017年12月15日 上午11:30:46
	 * @param id
	 * @return
	*/
	Map<String, Object> findOneNotice(Integer id);

	/**
	 * @Title: ajaxTableNotice
	 * @author: linyan
	 * @Description: 
	 * @date 2017年12月15日 下午3:44:53
	 * @param map
	 * @return
	*/
	Map<String, Object> ajaxTableNotice(Map<String, Object> map);

	/**
	 * @Title: selectByNotice
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午3:17:42
	 * @param user
	 * @return
	*/
	Map<String, Object> selectByNotice(String user);

}
