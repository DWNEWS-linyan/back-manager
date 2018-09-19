package com.ly.controller.doproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.service.doproject.IDoProjectService;

/**
* @ClassName: DoProjectController
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:11:30
*
*/
@Controller
@RequestMapping("doproject")
public class DoProjectController {

	@Autowired
	private IDoProjectService doProjectService;
	
}
