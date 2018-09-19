package com.ly.controller.site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.SSCellRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.service.crawler.ISiteSerivce;

/**
* @ClassName: SiteController
* @Description: 
* @author linyan
* @date 2018年1月30日 下午4:51:43
*
*/
@Controller
@RequestMapping(value = "site")
public class SiteController {

	
	@Autowired
	private ISiteSerivce siteSerivce ; 
	
	@RequestMapping(value = "goToSite")
	public String goToSite(HttpServletResponse response ,HttpServletRequest request , Model model){
		List<String> list = siteSerivce.getSiteLine();
		model.addAttribute("siteLine",list);
		System.out.println(list.size());
		return "site/site";
	}
	
	@RequestMapping(value = "getLineDir" , produces = "application/json;charset=utf8")
	@ResponseBody
	public Object getLineDir(String line , HttpServletRequest request , HttpServletResponse response){
		List<Map<String, Object>> list = siteSerivce.getLineDir(line);
		return list;
	}
	
	@RequestMapping(value = "getLineSite" , produces = "application/json;charset=utf8")
	@ResponseBody
	public Object getLineSite(String line,String lineDir , HttpServletRequest request ,HttpServletResponse response){
		List<Map<String, Object>> list = siteSerivce.getSites(line, lineDir);
		return list;
	}
	
	@RequestMapping(value = "refresh" ,produces = "application/json;charset=utf8")
	@ResponseBody
	public Object refresh(String line,String lineDir , String site ,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = siteSerivce.getTimeSiteLine(line, lineDir, site);
		return map;
	}
	
	
}
