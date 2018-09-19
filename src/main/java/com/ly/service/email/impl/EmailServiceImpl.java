package com.ly.service.email.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.mapper.sys.SysEmailFilesMapper;
import com.ly.mapper.sys.SysEmailMapper;
import com.ly.mapper.sys.SysEmailUserMapper;
import com.ly.mapper.sys.SysUserMapper;
import com.ly.po.SysEmail;
import com.ly.po.SysUser;
import com.ly.service.email.IEmailService;

/**
* @ClassName: EmailServiceImpl
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:05:52
*
*/
@Service
public class EmailServiceImpl implements IEmailService{

	
	@Autowired
	private SysEmailMapper emailMapper;
	
	@Autowired
	private SysEmailFilesMapper emailFilesMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysEmailUserMapper sysEmailUserMapper;
	
	/* （非 Javadoc）
	 * @see com.ly.service.email.IEmailService#selectByEmail(java.lang.String)
	 */
	@Override
	public Map<String, Object> selectByEmail(String user) {
		SysUser sysUser = sysUserMapper.selectSysUserByUserName(user);
		List<SysEmail> list = emailMapper.selectByUserInfoId(Integer.valueOf(sysUser.getUserId()));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email",list);
		map.put("size", list==null?0:list.size());
		return map;
	}

}
