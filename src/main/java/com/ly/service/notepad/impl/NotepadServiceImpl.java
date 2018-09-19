package com.ly.service.notepad.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.mapper.notepad.NotepadMapper;
import com.ly.mapper.sys.SysUserMapper;
import com.ly.po.Notepad;
import com.ly.po.SysUser;
import com.ly.service.notepad.INotepadService;

/**
* @ClassName: NotepadServiceImpl
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:07:24
*
*/
@Service
public class NotepadServiceImpl implements INotepadService {

	
	@Autowired
	private NotepadMapper notepadMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	/* （非 Javadoc）
	 * @see com.ly.service.notepad.INotepadService#selectByUser(java.lang.String)
	 */
	@Override
	public Map<String, Object> selectByUser(String user) {
		SysUser sysUser = sysUserMapper.selectSysUserByUserName(user);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
//		int hour = calendar.get(Calendar.HOUR_OF_DAY);
//		int minute = calendar.get(Calendar.MINUTE);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userInfoId", sysUser.getUserId());
		parameter.put("year", year);
		parameter.put("month", month);
		parameter.put("day", day);
		List<Notepad> list = notepadMapper.selectByNowUserInfoId(parameter);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notepad", list);
		map.put("size", list!=null?list.size():0);
		return map;
	}

}
