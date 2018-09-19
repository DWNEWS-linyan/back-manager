package com.ly.service.sys.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.common.LyPage;
import com.ly.common.LyPagePlugin;
import com.ly.mapper.sys.UserInfoMapper;
import com.ly.po.UserInfo;
import com.ly.service.sys.IUserInfoService;
import com.ly.utils.MyProperties;
import com.ly.vo.AddOrEditUserInfoVo;

/**
* @ClassName: UserInfoServiceImpl
* @Description: 
* @author linyan
* @date 2017年7月12日 下午5:36:55
*
*/
@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/* （非 Javadoc）
	 * @see com.ly.service.sys.IUserInfoService#userInfoTableAjax(java.util.Map)
	 */
	@Override
	public Map<String, Object> userInfoTableAjax(Map<String, Object> pare) {
		LyPagePlugin.startPage(Integer.valueOf(pare.get("start").toString()),Integer.valueOf(pare.get("length").toString()) + Integer.valueOf(pare.get("start").toString()) - 1);
		userInfoMapper.userInfoTableAjaxPage(pare);
		@SuppressWarnings("unchecked")
		LyPage<UserInfo> page = LyPagePlugin.endPage();
		List<List<Object>> list = new ArrayList<List<Object>>();
		for (UserInfo userInfo : page.getData()) {
			List<Object> row = new ArrayList<Object>();
			row.add(userInfo.getId());
			row.add(userInfo.getName());
			row.add(userInfo.getAge());
			row.add(userInfo.getSex());
			row.add(userInfo.getBirthday());
			row.add(userInfo.getNations());
			row.add(userInfo.getHeight());
			row.add(userInfo.getWeight());
			row.add(userInfo.getEducation());
			row.add("<a href=\"javascript:;\" class=\"btn btn-sm btn-outline grey-salsa edit-user-info-class\" data-id=\""+userInfo.getId()+"\"><i class=\"fa fa-search\"></i>修改</a>"+
					"<a href=\"javascript:;\" class=\"btn btn-sm btn-outline grey-salsa delete-user-info-class\" data-id=\""+userInfo.getId()+"\"><i class=\"fa fa-search\"></i>删除</a>");
			list.add(row);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", pare.get("draw"));
		map.put("recordsFiltered", page.getTotal());
		map.put("recordsTotal", page.getTotal());
		map.put("data", list);
		return map;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.sys.IUserInfoService#addOrEditUserInfo(com.ly.vo.AddOrEditUserInfoVo)
	 */
	@Override
	public Map<String, Object> addOrEditUserInfo(AddOrEditUserInfoVo userInfoVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer id = userInfoVo.getId();
			UserInfo userInfo = new UserInfo();
			userInfo.setAge(userInfoVo.getAge());
			userInfo.setBirthday(userInfoVo.getBirthday());
			userInfo.setEducation(userInfoVo.getEducation());
			userInfo.setGraduate(userInfoVo.getGraduate());
			userInfo.setGraduation(userInfoVo.getGraduation());
			userInfo.setHeight(userInfoVo.getHeight());
			userInfo.setIdCar(userInfoVo.getIdCar());
			userInfo.setName(userInfoVo.getName());
			userInfo.setNations(userInfoVo.getNations());
			userInfo.setPicIcon(userInfoVo.getPicIcon());
			userInfo.setSex(userInfoVo.getSex());
			userInfo.setSpecialty(userInfoVo.getSpecialty());
			userInfo.setStatus(userInfoVo.getStatus());
			userInfo.setTel(userInfoVo.getTel());
			userInfo.setWeight(userInfoVo.getWeight());
			if (id==null||id==0) {
				userInfoMapper.insertSelective(userInfo);
			}else {
				if (userInfo.getPicIcon()!=null&&!userInfo.getPicIcon().equals("")) {
					AddOrEditUserInfoVo userInfoOne = editFind(id);
					if (userInfoOne.getPicIcon()!=null&&!userInfoOne.getPicIcon().equals("")) {
						String filedic = MyProperties.get("filedic");
						String fileHanderImage = MyProperties.get("fileHanderImage");
						String filed = filedic+File.separator+fileHanderImage+File.separator+userInfoOne.getPicIcon().substring(userInfoOne.getPicIcon().lastIndexOf("\\")+1);
						File file = new File(filed);
						if (file.exists()) {
							file.delete();
						}
					}
				}
				userInfo.setId(id);
				userInfoMapper.updateByPrimaryKeySelective(userInfo);
			}
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 1);
			map.put("mes", "添加或修改失败");
			e.printStackTrace();
		}
		
		return map;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.sys.IUserInfoService#editFind(java.lang.Integer)
	 */
	@Override
	public AddOrEditUserInfoVo editFind(Integer id) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		AddOrEditUserInfoVo userInfoVo = new AddOrEditUserInfoVo();
		userInfoVo.setAge(userInfo.getAge());
		userInfoVo.setBirthday(userInfo.getBirthday());
		userInfoVo.setEducation(userInfo.getEducation());
		userInfoVo.setGraduate(userInfo.getGraduate());
		userInfoVo.setGraduation(userInfo.getGraduation());
		userInfoVo.setHeight(userInfo.getHeight());
		userInfoVo.setId(userInfo.getId());
		userInfoVo.setIdCar(userInfo.getIdCar());
		userInfoVo.setName(userInfo.getName());
		userInfoVo.setNations(userInfo.getNations());
		userInfoVo.setPicIcon(userInfo.getPicIcon());
		userInfoVo.setSex(userInfo.getSex());
		userInfoVo.setSpecialty(userInfo.getSpecialty());
		userInfoVo.setStatus(userInfo.getStatus());
		userInfoVo.setTel(userInfo.getTel());
		userInfoVo.setWeight(userInfo.getWeight());
		return userInfoVo;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.sys.IUserInfoService#deleteUserInfo(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> deleteUserInfo(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userInfoMapper.deleteByPrimaryKey(id);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 1);
			map.put("mes", "删除失败");
			e.printStackTrace();
		}
		return map;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.sys.IUserInfoService#selectById(java.lang.Integer)
	 */
	@Override
	public UserInfo selectById(Integer id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

}
