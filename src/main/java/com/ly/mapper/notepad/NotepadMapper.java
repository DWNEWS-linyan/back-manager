package com.ly.mapper.notepad;

import java.util.List;
import java.util.Map;

import com.ly.po.Notepad;

public interface NotepadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notepad record);

    int insertSelective(Notepad record);

    Notepad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notepad record);

    int updateByPrimaryKey(Notepad record);

	/**
	 * @Title: selectByNowUserInfoId
	 * @author: linyan
	 * @Description: 
	 * @date 2018年1月21日 下午8:09:27
	 * @param parameter
	 * @return
	*/
	List<Notepad> selectByNowUserInfoId(Map<String, Object> parameter);
}