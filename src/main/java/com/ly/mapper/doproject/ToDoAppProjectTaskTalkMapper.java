package com.ly.mapper.doproject;

import com.ly.po.ToDoAppProjectTaskTalk;

public interface ToDoAppProjectTaskTalkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ToDoAppProjectTaskTalk record);

    int insertSelective(ToDoAppProjectTaskTalk record);

    ToDoAppProjectTaskTalk selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ToDoAppProjectTaskTalk record);

    int updateByPrimaryKey(ToDoAppProjectTaskTalk record);
}