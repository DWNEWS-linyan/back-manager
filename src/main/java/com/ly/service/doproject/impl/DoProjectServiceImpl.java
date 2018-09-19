package com.ly.service.doproject.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.mapper.doproject.ToDoAppFilesMapper;
import com.ly.mapper.doproject.ToDoAppProjectMapper;
import com.ly.mapper.doproject.ToDoAppProjectTaskMapper;
import com.ly.mapper.doproject.ToDoAppProjectTaskTalkMapper;
import com.ly.mapper.doproject.ToDoAppProjectTaskUserMapper;
import com.ly.mapper.sys.SysUserMapper;
import com.ly.mapper.sys.UserInfoMapper;
import com.ly.po.SysUser;
import com.ly.po.ToDoAppProject;
import com.ly.po.ToDoAppProjectTask;
import com.ly.po.UserInfo;
import com.ly.service.doproject.IDoProjectService;

/**
* @ClassName: DoProjectSerivceImpl
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:04:23
*
*/
@Service
public class DoProjectServiceImpl implements IDoProjectService {

	@Autowired
	private ToDoAppProjectMapper projectMapper;
	
	@Autowired
	private ToDoAppProjectTaskUserMapper projectTaskUserMapper;
	
	@Autowired
	private ToDoAppProjectTaskMapper projectTaskMapper;

	@Autowired
	private ToDoAppFilesMapper filesMapper;
	
	@Autowired
	private ToDoAppProjectTaskTalkMapper projectTaskTalkMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/* （非 Javadoc）
	 * @see com.ly.service.doproject.IDoProjectService#selectByProject(java.lang.String)
	 */
	@Override
	public Map<String, Object> selectByProject(String user) {
		SysUser sysUser = sysUserMapper.selectSysUserByUserName(user);
		List<ToDoAppProjectTask> tasks = projectTaskMapper.selectByUserInfoId(Integer.valueOf(sysUser.getUserId()));
		Map<Integer, List<ToDoAppProjectTask>> mapListTasks = new HashMap<Integer, List<ToDoAppProjectTask>>();
		Map<Integer, ToDoAppProject> mapProject = new HashMap<Integer, ToDoAppProject>();
		for (ToDoAppProjectTask toDoAppProjectTask : tasks) {
			ToDoAppProject project = projectMapper.selectByPrimaryKey(toDoAppProjectTask.getProjectId());
			List<ToDoAppProjectTask> list = new ArrayList<ToDoAppProjectTask>();
			list.add(toDoAppProjectTask);
			if (mapListTasks.containsKey(project.getId())) {
				list.addAll(mapListTasks.get(project.getId()));
			}
			mapListTasks.put(project.getId(), list);
			mapProject.put(project.getId(), project);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Integer zsise = 0 ;
		for (Map.Entry<Integer, ToDoAppProject> projectEntry : mapProject.entrySet()) {
			ToDoAppProject toDoAppProject = projectEntry.getValue();
			List<ToDoAppProjectTask> list2 = mapListTasks.get(toDoAppProject.getId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", toDoAppProject.getId());
			map.put("title", toDoAppProject.getTitile());
			map.put("items", list2);
			map.put("size", list2.size());
			list.add(map);
			zsise+=list2.size();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project", list);
		map.put("size", zsise);
		return map;
	}

}
