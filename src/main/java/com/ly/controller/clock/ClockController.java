package com.ly.controller.clock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.service.crawler.IClockService;

/**
* @ClassName: ClockController
* @Description: 
* @author linyan
* @date 2018年2月2日 下午3:49:21
*
*/
@Controller
@RequestMapping(value ="clock")
public class ClockController {

	@Autowired
	private IClockService clockService;
	
	@RequestMapping(value = "getClock")
	public String getClock(@RequestParam("s")String star ,@RequestParam("e")String end , HttpServletRequest request,HttpServletResponse response,Model model) {
		model.addAttribute("list", clockService.getClock(star, end));
		return "clock/clock";
	}
	
	
}
