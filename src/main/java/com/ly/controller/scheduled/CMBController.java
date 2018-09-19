package com.ly.controller.scheduled;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.scheduled.CMB;
import com.ly.service.crawler.ICMBService;

/**
* @ClassName: CMBController
* @Description: 
* @author linyan
* @date 2018年1月10日 上午9:45:07
*
*/
@Controller
@RequestMapping("cmbc")
public class CMBController {

	
	@Autowired
	private ICMBService cmbService;
	
	@RequestMapping("goToCMBViwe")
	public String goToCMBViwe(HttpServletRequest request,HttpServletResponse response ,Model model) {
		model.addAttribute("falge" , CMB.falge);
		model.addAttribute("mn" , CMB.mn);
		model.addAttribute("moble" , CMB.moble);
		return "scheduled/cmbv";
	}
	
	
	@RequestMapping(value = "editCMB" ,produces = "application/json;charset=utf8")
	@ResponseBody
	public Object editCMB(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer falge = ServletRequestUtils.getIntParameter(request, "falge",0);
		Integer num = ServletRequestUtils.getIntParameter(request, "num",0);
		Double mn = ServletRequestUtils.getDoubleParameter(request, "mn",0.0);
		String moble = ServletRequestUtils.getStringParameter(request, "moble",null);
		if (num!=0) {
			CMB.num = num;
		}
		if (falge!=0) {
			CMB.falge = falge;
		}
		if (mn!=0.0) {
			CMB.mn = mn;
		}
		if (moble!=null&&!moble.equals("")) {
			CMB.moble = moble;
		}
		map.put("code", 0);
		map.put("mes", "成功了");
		return map;
	}
	
	
	
	@RequestMapping(value = "getCMB" ,produces = "application/json;charset=utf8")
	@ResponseBody
	public Object getCMBInterface(HttpServletRequest request ,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("code", 0);
			map.put("mes","成功");
			Map<String, Object> redata = new HashMap<String, Object>();
			Double cmb = cmbService.getDollarRate(null);
			redata.put("falge", CMB.falge);
			redata.put("mn", CMB.mn);
			redata.put("num", CMB.num);
			redata.put("moble",CMB.moble);
			redata.put("cmb", cmb);
			map.put("re_data", redata);
		} catch (Exception e) {
			map.put("code", 1);
			map.put("mes", "位置错误："+e.getLocalizedMessage());
		}
		return map ; 
	}
	
}
