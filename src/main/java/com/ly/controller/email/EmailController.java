package com.ly.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.service.email.IEmailService;

/**
* @ClassName: EmailController
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:11:50
*
*/
@Controller
@RequestMapping("email")
public class EmailController {

	@Autowired
	private IEmailService emailService;
	
}
