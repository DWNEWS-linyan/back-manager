package com.ly.mapper.notice;

import com.ly.po.SysNoticeTarget;

public interface SysNoticeTargetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNoticeTarget record);

    int insertSelective(SysNoticeTarget record);

    SysNoticeTarget selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNoticeTarget record);

    int updateByPrimaryKey(SysNoticeTarget record);
}