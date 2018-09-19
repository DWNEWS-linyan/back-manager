package com.ly.controller.test;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @ClassName: TestFile
* @Description: 
* @author linyan
* @date 2017年8月9日 上午11:08:56
*
*/
@Controller
public class TestFileController {

	
	
	@RequestMapping(value ="testFile")
	public String testFile(){
		return "test/TestFile";
	}
	
	@RequestMapping(value = "fileupdate")
	@ResponseBody
	public Object fileupdate(HttpServletRequest request ,HttpServletResponse response ,String iop){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;           
		Iterator<String> names = multipartRequest.getFileNames();

		while (names.hasNext()) {
            String name = (String) names.next();
            MultipartFile multipartFile = multipartRequest.getFile(name);
            System.out.println(multipartFile.getOriginalFilename());
		}
		return "0";
	}
//	@RequestMapping(value = "fileupdate")
//	@ResponseBody
//	public Object fileupdate(HttpServletRequest request,@RequestParam("file[]") MultipartFile file ,HttpServletResponse response ,String iop){
//		String fName=file.getName();
//		String gglString = file.getOriginalFilename();
//		System.out.println(gglString);
//		String type = file.getContentType();
//		System.out.println(type);	
//		System.out.println(fName+">>>>>>>>>>>>>>>>");
//		System.out.println(iop);
//		
//		return "0";
//	}
	
	/**
	 * json 传输 
	 */
	@RequestMapping(value = "jsonTest" , produces = "application/json;charset=utf8",method = RequestMethod.GET)
	@ResponseBody
	public Object jso(HttpServletRequest request){
		
		 Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()){
			Object sObject = request.getParameter(en.nextElement());
			System.out.println(sObject);
		}
		return "gwe";
	}
	@RequestMapping(value = "jsonTest" , produces = "application/json;charset=utf8",method = RequestMethod.POST)
	@ResponseBody
//	public Object jsonTransmission(@RequestBody JsonTestPr t,  HttpServletRequest request,HttpServletResponse response){
	public Object jsonTransmission(JsonTestPr t,  HttpServletRequest request,HttpServletResponse response){
		try {
//			Enumeration<String> e = request.getAttributeNames();
//			while (e.hasMoreElements()){
//				Object sObject = request.getAttribute(e.nextElement());
//				System.out.println(sObject);
//			}
			
			
			/*
			 * get 方式
			 * Enumeration<String> en = request.getParameterNames();
			while (en.hasMoreElements()){
				Object sObject = request.getParameter(en.nextElement());
				System.out.println(sObject);
			}*/
			System.out.println(request.getMethod());
			ServletInputStream si = request.getInputStream();
			String result = IOUtils.toString(si, StandardCharsets.UTF_8);
			System.out.println(result);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonTestPr jsonTestPr = objectMapper.readValue(result, JsonTestPr.class);
			System.out.println(jsonTestPr.getId());
			
			boolean flage = false;
//			System.out.println(t.getId());
//			System.out.println(t.getName());
//			System.out.println(t.getCar());
//			if (id!=null&&!id.equals("")) {
//				System.out.println("id::"+id);
//				flage = true;
//			}
//			if (name!=null&&!name.equals("")) {
//				System.out.println("name::"+name);
//				flage = true;
//			}
//			if (flage) {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("code", 0);
//				map.put("mes", "ock");
//				map.put("reData", "id::"+id+",name::"+name);
//				String nString = objectMapper.writeValueAsString(map);
//				return nString;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "no";
	}
	
	
	
}
