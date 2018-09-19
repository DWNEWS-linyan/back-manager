package com.ly.mapper.sys;

import com.ly.po.SysEmailUser;

public interface SysEmailUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysEmailUser record);

    int insertSelective(SysEmailUser record);

    SysEmailUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysEmailUser record);

    int updateByPrimaryKey(SysEmailUser record);
}