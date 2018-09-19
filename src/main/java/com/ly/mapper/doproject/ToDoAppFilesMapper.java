package com.ly.mapper.doproject;

import com.ly.po.ToDoAppFiles;

public interface ToDoAppFilesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ToDoAppFiles record);

    int insertSelective(ToDoAppFiles record);

    ToDoAppFiles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ToDoAppFiles record);

    int updateByPrimaryKey(ToDoAppFiles record);
}