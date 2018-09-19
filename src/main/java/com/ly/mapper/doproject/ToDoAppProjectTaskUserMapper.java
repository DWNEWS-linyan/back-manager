package com.ly.mapper.doproject;

import com.ly.po.ToDoAppProjectTaskUser;

public interface ToDoAppProjectTaskUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ToDoAppProjectTaskUser record);

    int insertSelective(ToDoAppProjectTaskUser record);

    ToDoAppProjectTaskUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ToDoAppProjectTaskUser record);

    int updateByPrimaryKey(ToDoAppProjectTaskUser record);
}