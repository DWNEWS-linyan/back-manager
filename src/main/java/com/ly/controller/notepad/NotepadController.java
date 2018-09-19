package com.ly.controller.notepad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.service.notepad.INotepadService;

/**
* @ClassName: NotepadController
* @Description: 
* @author linyan
* @date 2018年1月21日 上午11:12:54
*
*/
@Controller
@RequestMapping("notepad")
public class NotepadController {

	@Autowired
	private INotepadService notepadService;
	
}
