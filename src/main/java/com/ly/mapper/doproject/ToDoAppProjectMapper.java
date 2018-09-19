package com.ly.mapper.doproject;

import com.ly.po.ToDoAppProject;

public interface ToDoAppProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ToDoAppProject record);

    int insertSelective(ToDoAppProject record);

    ToDoAppProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ToDoAppProject record);

    int updateByPrimaryKey(ToDoAppProject record);
}