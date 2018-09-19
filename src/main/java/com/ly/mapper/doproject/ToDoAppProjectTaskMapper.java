package com.ly.mapper.doproject;

import java.util.List;

import com.ly.po.ToDoAppProjectTask;

public interface ToDoAppProjectTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ToDoAppProjectTask record);

    int insertSelective(ToDoAppProjectTask record);

    ToDoAppProjectTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ToDoAppProjectTask record);

    int updateByPrimaryKey(ToDoAppProjectTask record);

	/**
	 * @Title: selectByObject
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午3:58:49
	 * @param task
	 * @return
	*/
    List<ToDoAppProjectTask> selectByObject(ToDoAppProjectTask task);

	/**
	 * @Title: selectByUserInfoId
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午3:59:55
	 * @param id
	 * @return
	*/
	List<ToDoAppProjectTask> selectByUserInfoId(Integer id);
}