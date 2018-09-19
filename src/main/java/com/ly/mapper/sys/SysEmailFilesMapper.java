package com.ly.mapper.sys;

import com.ly.po.SysEmailFiles;

public interface SysEmailFilesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysEmailFiles record);

    int insertSelective(SysEmailFiles record);

    SysEmailFiles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysEmailFiles record);

    int updateByPrimaryKey(SysEmailFiles record);
}