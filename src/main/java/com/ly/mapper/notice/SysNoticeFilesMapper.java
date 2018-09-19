package com.ly.mapper.notice;

import com.ly.po.SysNoticeFiles;

public interface SysNoticeFilesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNoticeFiles record);

    int insertSelective(SysNoticeFiles record);

    SysNoticeFiles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNoticeFiles record);

    int updateByPrimaryKey(SysNoticeFiles record);
}