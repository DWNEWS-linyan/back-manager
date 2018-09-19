package com.ly.controller.notice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.service.notice.INoticeService;
import com.ly.vo.NoticeVo;

/**
* @ClassName: NoticeController
* @Description: 
* @author linyan
* @date 2017年12月14日 下午4:00:26
*
*/
@Controller
@RequestMapping("notice")
public class NoticeController {

	@Autowired
	private INoticeService noticeService;
	
	
	@RequestMapping(value = "goNotice")
	public String goNotice(HttpServletRequest request,HttpServletResponse response){
		return "notice/notice";
	}
	
	@RequestMapping(value = "ajaxTableNotice" , produces = "application/json;charset=utf8")
	@ResponseBody
	public Object ajaxTableNotice(HttpServletRequest request ,HttpServletResponse response){
		Integer noticeId = ServletRequestUtils.getIntParameter(request, "noticeId",0);
		String noticeTarget = ServletRequestUtils.getStringParameter(request, "noticeTarget",null);
		String noticeTitle = ServletRequestUtils.getStringParameter(request, "noticeTitle",null);
		String noticeContent = ServletRequestUtils.getStringParameter(request, "noticeContent",null);
		String publish_date_start = ServletRequestUtils.getStringParameter(request, "publish_date_start",null);
		String publish_date_end = ServletRequestUtils.getStringParameter(request, "publish_date_end",null);
		String draw = ServletRequestUtils.getStringParameter(request,"draw",null);
		String orderDir = ServletRequestUtils.getStringParameter(request,"order[0][dir]",null);
		Integer start = ServletRequestUtils.getIntParameter(request, "start",0);
		Integer length = ServletRequestUtils.getIntParameter(request, "length",0);
		Integer order = ServletRequestUtils.getIntParameter(request, "order[0][column]",0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noticeId",noticeId);
		map.put("noticeTarget",noticeTarget);
		map.put("noticeTitle",noticeTitle);
		map.put("noticeContent",noticeContent);
		map.put("publish_date_start",publish_date_start);
		map.put("publish_date_end",publish_date_end);
		map.put("draw",draw);
		map.put("orderDir",orderDir);
		map.put("start",start);
		map.put("length",length);
		map.put("order",order);
		
		map = noticeService.ajaxTableNotice(map);
		
		return map;
	}
	
	
	@RequestMapping(value = "findOneNotice",produces = "application/json;charset=utf8")
	@ResponseBody
	public Object findOneNotice(Integer id,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map = noticeService.findOneNotice(id);
		return map;
	}
	
	@RequestMapping(value = "addOrEditNotice" ,produces = "application/json;charset=utf8")
	@ResponseBody
	public Object addNotice(NoticeVo noticeVo , HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		ServletRequestUtils.getStringParameter(request, "","");
		map = noticeService.addOrEditNotice(noticeVo);
		return map;
	}
	
	@RequestMapping(value = "deleteNotcie" , produces = "application/json;charset=utf8")
	@ResponseBody
	public Object deleteNotice(Integer id,HttpServletRequest request ,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map = noticeService.deleteNotice(id);
		return map;
	}
	
	
	
}
