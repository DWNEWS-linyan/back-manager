package com.ly.service.notice.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.common.LyPage;
import com.ly.common.LyPagePlugin;
import com.ly.mapper.notice.SysNoticeFilesMapper;
import com.ly.mapper.notice.SysNoticeMapper;
import com.ly.mapper.notice.SysNoticeTargetMapper;
import com.ly.mapper.sys.SysUserMapper;
import com.ly.po.SysNotice;
import com.ly.po.SysUser;
import com.ly.service.notice.INoticeService;
import com.ly.vo.NoticeVo;

/**
* @ClassName: NoticeServiceImpl
* @Description: 
* @author linyan
* @date 2017年12月15日 上午11:19:55
*
*/
@Service
public class NoticeServiceImpl implements INoticeService {

	@Autowired
	private SysNoticeMapper noticeMapper;
	
	@Autowired
	private SysNoticeFilesMapper noticeFilesMapper;
	
	@Autowired
	private SysNoticeTargetMapper noticeTargetMapper;

	@Autowired
	private SysUserMapper sysUserMapper;
	
	/* （非 Javadoc）
	 * @see com.ly.service.notice.INoticeService#addOrEditNotice(com.ly.vo.NoticeVo)
	 */
	@Override
	public Map<String, Object> addOrEditNotice(NoticeVo noticeVo) {
		
		return null;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.notice.INoticeService#deleteNotice(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> deleteNotice(Integer id) {
		
		return null;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.notice.INoticeService#findOneNotice(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> findOneNotice(Integer id) {
		
		return null;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.notice.INoticeService#ajaxTableNotice(java.util.Map)
	 */
	@Override
	public Map<String, Object> ajaxTableNotice(Map<String, Object> pare) {
		LyPagePlugin.startPage(Integer.valueOf(pare.get("start").toString()),Integer.valueOf(pare.get("length").toString()) + Integer.valueOf(pare.get("start").toString()) - 1);
		noticeMapper.noticeTableAjaxPage(pare);
		@SuppressWarnings("unchecked")
		LyPage<SysNotice> page = LyPagePlugin.endPage();
		List<List<Object>> list = new ArrayList<List<Object>>();
		for (SysNotice notice : page.getData()) {
			List<Object> row = new ArrayList<Object>();
			row.add(notice.getId());
			row.add(notice.getTitle());
			row.add(notice.getCreateTime());
			row.add(notice.getContent());
			row.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(notice.getPublishTime()));
			row.add("<a href=\"javascript:;\" class=\"btn btn-sm btn-outline grey-salsa edit-notice-class\" data-id=\""+notice.getId()+"\"><i class=\"fa fa-search\"></i>修改</a>"+
					"<a href=\"javascript:;\" class=\"btn btn-sm btn-outline grey-salsa delete-notice-class\" data-id=\""+notice.getId()+"\"><i class=\"fa fa-search\"></i>删除</a>");
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
	 * @see com.ly.service.notice.INoticeService#selectByNotice(java.lang.String)
	 */
	@Override
	public Map<String, Object> selectByNotice(String user) {
		SysUser sysUser = sysUserMapper.selectSysUserByUserName(user);
		List<SysNotice> list = noticeMapper.selectByUserInfoId(sysUser.getUserId());
//		List<SysNotice> list2 = noticeMapper.selectByGroupId(5);
		Set<SysNotice> set = new HashSet<SysNotice>();
		set.addAll(list);
//		set.addAll(list2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notice", set);
		map.put("size", set.size());
		return map;
	}

	
	
	
	
	
}
