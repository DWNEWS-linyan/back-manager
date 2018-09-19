package com.ly.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.common.LyPage;
import com.ly.common.LyPagePlugin;
import com.ly.mapper.sys.SysUserMapper;
import com.ly.mapper.sys.SysUserRoleMapper;
import com.ly.mapper.sys.UserInfoMapper;
import com.ly.po.SysUser;
import com.ly.po.SysUserRole;
import com.ly.po.UserInfo;
import com.ly.service.sys.ISysUserService;

/**
* @ClassName: SysUserServiceImpl
* @Description: 
* @author linyan
* @date 2017年7月12日 下午5:36:27
*
*/
@Service
public class SysUserServiceImpl implements ISysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#selectPageSysUser(com.ly.common.LyPage)
	 */
	@SuppressWarnings("unchecked")
	public LyPage<SysUser> selectPageSysUser(LyPage<SysUser> page) {
		SysUser sysUser = new SysUser();
		LyPagePlugin.startPage(0, 10);
		sysUserMapper.selectPageSysUser(sysUser);
		page = LyPagePlugin.endPage();
		System.out.println(page);
		return page;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#updateSysUserRole(java.lang.String[], java.lang.String)
	 */
	@Override
	public Map<String, Object> updateSysUserRole(String[] roleIds, String sysuserid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			sysUserRoleMapper.deleteBySysUserId(Integer.valueOf(sysuserid));
			for (int i = 0; i < roleIds.length; i++) {
				SysUserRole sysuserrole = new SysUserRole();
				sysuserrole.setSysRole(Integer.valueOf(roleIds[i]));
				sysuserrole.setSysUserId(Integer.valueOf(sysuserid));
				sysUserRoleMapper.insertSelective(sysuserrole);
			}
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 0);
			map.put("mes", "出错了");
			e.printStackTrace();
		}
		return map;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#resetPass(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> resetPass(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
			sysUser.setUserPass("123456");
			int a = sysUserMapper.updateByPrimaryKeySelective(sysUser);
			if (a==0) {
				map.put("code",1);
				map.put("mes", "修改失败了");
			}else {
				map.put("code", 0);
				map.put("mes","");
			}
		} catch (Exception e) {
			map.put("code", 1);
			map.put("mes","出错了");
			e.printStackTrace();
		}
		return map;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#deleteSysUser(java.lang.Integer)
	 */
	@Transactional
	@Override
	public Map<String, Object> deleteSysUser(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
			int a = sysUserMapper.deleteByPrimaryKey(id);
			if (a==0) {
				map.put("code",1);
				map.put("mes", "您要删除的对象不存在");
			}else {
				sysUserRoleMapper.deleteBySysUserId(id);
				map.put("code", 0);
				map.put("mes", "");
			}
		return map;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#retrievePass(java.lang.String)
	 */
	@Override
	public Map<String, Object> retrievePass(String telPhoneCallBack) {
		List<SysUser> list = sysUserMapper.selectByUserInfoTel(telPhoneCallBack);
		Map<String, Object> map = new HashMap<String, Object>();
		if (list!=null&&list.size()==1) {
			map.put("code", 0);
		}else if (list==null||list.size()==0) {
			map.put("code", 1);
			map.put("mes", "没有与您输入的手机号相关联的用户，请重新输入.");
		}else if (list!=null&&list.size()>1) {
			map.put("code", 2);
			map.put("mes", "您的手机号绑定了多个用户，请联系管理员处理。");
		}
		
		return map;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#saveNewPass(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> saveNewPass(String newPass, String tel) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<SysUser> list = sysUserMapper.selectByUserInfoTel(tel);
			SysUser sysUser = list.get(0);
			sysUser.setUserPass(newPass);
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
			map.put("code", 0);
		} catch (Exception e) {
			map.put("code", 1);
			map.put("mes", "给您跪了，改个密码都能出错！！！");
			e.printStackTrace();
		}
		return map;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#select2JSONData(java.lang.String)
	 */
	@Override
	public List<UserInfo> select2JSONData(String p) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (p!=null&&!p.equals("")) {
				Integer.valueOf(p);
				if (p.length()<3) {
					map.put("age", p);
				}else if (p.length() < 12) {
					map.put("tel", p);
				}
			}
		} catch (Exception e) {
			if (p.length()>14) {
				map.put("idcar", p);
			}else if (p.length()<11&&p.length()>9) {
				map.put("birthday", p);
			}else{
				map.put("name", p);
			}
		}
		map.put("left", 1);
		list = userInfoMapper.selectBySysUser(map);
		return list;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#saveSysUser(com.ly.po.SysUser)
	 */
	@Override
	public Map<String, Object> saveSysUser(SysUser sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int id = sysUserMapper.insertSelective(sysUser);
			if (id!=0) {
				map.put("code", 0);
			}else {
				map.put("code", 1);
				map.put("mes", "额，出了什么事情？");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 1);
			map.put("mes", "保存出现了不可预知的错误。");
		}
		return map;
	}


	/* （非 Javadoc）
	 * @see com.ly.service.sys.ISysUserService#selectByUserName(java.lang.String)
	 */
	@Override
	public boolean selectByUserName(String username) {
		SysUser sysUser = sysUserMapper.selectSysUserByUserName(username);
		if (sysUser!=null) {
			return true ;
		}
		return false;
	}

}
