package com.ly.service.sys.impl;

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
		
		return null;
	}

}
