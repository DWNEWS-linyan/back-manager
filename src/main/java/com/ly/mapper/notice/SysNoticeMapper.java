package com.ly.mapper.notice;

import java.util.List;
import java.util.Map;

import com.ly.po.SysNotice;
import com.ly.po.SysRole;

public interface SysNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNotice record);

    int insertSelective(SysNotice record);

    SysNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNotice record);

    int updateByPrimaryKeyWithBLOBs(SysNotice record);

    int updateByPrimaryKey(SysNotice record);

	/**
	 * @Title: noticeTableAjaxPage
	 * @author: linyan
	 * @Description: 
	 * @date 2017年12月15日 下午3:47:41
	 * @param pare
	*/
    List<SysRole> noticeTableAjaxPage(Map<String, Object> pare);

	/**
	 * @Title: selectByUserInfoId
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午8:50:04
	 * @param userId
	 * @return
	*/
	List<SysNotice> selectByUserInfoId(String userId);
	
	
	/**
	 * @Title: selectByGroupId
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午8:56:36
	 * @param groupId
	 * @return
	 */
	List<SysNotice> selectByGroupId(String groupId);
}