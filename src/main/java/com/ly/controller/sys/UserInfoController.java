package com.ly.controller.sys;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ly.po.SysUser;
import com.ly.service.sys.IUserInfoService;
import com.ly.utils.EntityToString;
import com.ly.utils.MyProperties;
import com.ly.vo.AddOrEditUserInfoVo;

/**
* @ClassName: UserInfoController
* @Description: 
* @author linyan
* @date 2017年7月14日 下午4:48:20
*
*/
@Controller
public class UserInfoController {

	
	@Autowired
	private IUserInfoService userInfoService;
	
	@RequestMapping(value = "goUserInfo")
	public String goUserInfo(){
		return "userInfo/userList";
	}
	
	@RequestMapping(value = "userInfo/userInfoTableAjax" ,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object userInfoTableAjax(HttpServletRequest request,HttpServletResponse response){
		Integer userInfoId = ServletRequestUtils.getIntParameter(request, "userInfoId",0);
		Integer userAge = ServletRequestUtils.getIntParameter(request, "userAge",0);
		Integer userHight = ServletRequestUtils.getIntParameter(request, "userHight",0);
		Integer userWeight = ServletRequestUtils.getIntParameter(request, "userWeight",0);
		String userName = ServletRequestUtils.getStringParameter(request, "userName",null);
		String userSex = ServletRequestUtils.getStringParameter(request,"userSex",null);
		String userBirthday = ServletRequestUtils.getStringParameter(request,"userBirthday",null);
		String userNations = ServletRequestUtils.getStringParameter(request,"userNations",null);
		String userEducation = ServletRequestUtils.getStringParameter(request,"userEducation",null);
		String draw = ServletRequestUtils.getStringParameter(request,"draw",null);
		String orderDir = ServletRequestUtils.getStringParameter(request,"order[0][dir]",null);
		Integer start = ServletRequestUtils.getIntParameter(request, "start",0);
		Integer length = ServletRequestUtils.getIntParameter(request, "length",0);
		Integer order = ServletRequestUtils.getIntParameter(request, "order[0][column]",0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userInfoId",userInfoId);
		map.put("userAge",userAge);
		map.put("userHight",userHight);
		map.put("userWeight",userWeight);
		map.put("userName",userName);
		map.put("userSex",userSex);
		map.put("userBirthday",userBirthday);
		map.put("userNations",userNations);
		map.put("userEducation",userEducation);
		map.put("draw",draw);
		map.put("orderDir",orderDir);
		map.put("start",start);
		map.put("length",length);
		map.put("order",order);
		
		map = userInfoService.userInfoTableAjax(map);
		Enumeration<String> dEnumeration = request.getSession().getAttributeNames();
		while (dEnumeration.hasMoreElements()) {
			String string = (String) dEnumeration.nextElement();
			System.out.println("========================");
			System.out.println(string);
			System.out.println("========5555555555555=================");
			System.out.println(request.getSession().getAttribute(string));
		}
		return map;
	}
	
	@RequestMapping(value = "userInfo/addOrEditUserInfo" ,produces = "application/json;charset=utf8")
	@ResponseBody
	public Object addOrEditUserInfo(AddOrEditUserInfoVo userInfoVo,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println(EntityToString.getString(userInfoVo));
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;           
			Iterator<String> names = multipartRequest.getFileNames();
			String fileHostPort = MyProperties.get("fileHostPort");
			String filedic = MyProperties.get("filedic");
			String fileWebApp = MyProperties.get("fileWebApp");
			String fileHanderImage = MyProperties.get("fileHanderImage");
			while (names.hasNext()) {
	            String name = (String) names.next();
	            MultipartFile multipartFile = multipartRequest.getFile(name);
	            if (!multipartFile.isEmpty()) {
	            	String fileName = UUID.randomUUID().toString().replace("-", "")+File.separator+multipartFile.getOriginalFilename();
	            	File file = new File(filedic+File.separator+fileHanderImage+File.separator+fileName);
	            	// 复制文件 到 某处
	            	multipartFile.transferTo(file);
	            	String user_hander_image_url = fileHostPort+"\\"+fileWebApp+"\\"+fileHanderImage+"\\"+fileName;
	            	userInfoVo.setPicIcon(user_hander_image_url);
				}
//	            System.out.println(multipartFile.isEmpty());//判断是否为空    为空 返回true
//	            System.out.println(multipartFile.getOriginalFilename());  //文件名
			}
			map = userInfoService.addOrEditUserInfo(userInfoVo);
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("code", 1);
			map.put("mes", "出错了");
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping(value = "userInfo/editFind",produces = "application/json;charset=utf8")
	@ResponseBody
	public Object editFind(Integer id , HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AddOrEditUserInfoVo userInfo = new AddOrEditUserInfoVo();
			userInfo = userInfoService.editFind(id);
			if (userInfo==null) {
				map.put("code", 2);
				map.put("mes","没有查到您想要的对象，请刷新重试。");
			}else {
				map.put("code", 0);
				map.put("reData", userInfo);
			}
		} catch (Exception e) {
			map.put("code", 1);
			map.put("mes","查询中出了错误，请稍后重试。谢谢 您的 理解");
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping(value = "userInfo/deleteUserInfo",produces = "application/json;charset=utf8")
	@ResponseBody
	public Object deleteUserInfo(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map = userInfoService.deleteUserInfo(id);
		return map;
	}
	
	@RequestMapping(value = "userInfo/personInfo" ,produces = "application/json;charset=utf8")
	public String personInfo(HttpServletRequest request , HttpServletResponse response,Model model){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(MyProperties.get("SESSION_USER_KEY"));
		String userInfoId = sysUser.getUserId();
		AddOrEditUserInfoVo userInfoVo = userInfoService.editFind(Integer.valueOf(userInfoId));
		model.addAttribute("userInfo", userInfoVo);
		return "userInfo/personInfo";
	}
	
	
	@RequestMapping(value = "ceshiObject")
	@ResponseBody
	public Object ceshiObject(@RequestBody String g ,String info,HttpServletRequest request,HttpServletResponse response){
		Enumeration<String> e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			String string = (String) e.nextElement();
			Object gg = request.getAttribute(string);
			System.out.println(string+"<<<<>>>>>>>>>>>>>"+gg);
		}
		Enumeration<String>  wEnumeration= request.getHeaderNames();
		while (wEnumeration.hasMoreElements()) {
	        String key = (String) wEnumeration.nextElement();
	        String value = request.getHeader(key);
	        System.out.println(key+"======="+value);
	    }
		System.out.println(g);
		System.out.println("============");
		Map<String, String[]> map = request.getParameterMap();
		
		Set<Map.Entry<String, String[]>> aMaps = map.entrySet();
		for (Entry<String, String[]> entry : aMaps) {
			String af =  entry.getKey();
			String ag = request.getParameter(af);
			System.out.println(af+"<---===--->"+ag);
		}
		System.out.println("----------------");
//		try {
//			InputStreamReader aInputStreamReader = new InputStreamReader(request.getInputStream());
//			StringBufferInputStream nBufferInputStream = new st
//			byte[] b = new byte[request.getContentLength()];
//			request.getInputStream().read(b);
//			String gagggString = new String(b);
//			System.out.println(gagggString+"<<<<");
//			ServletInputStream ris = request.getInputStream();  
			
//			BufferedReader br = request.getReader();
//			String str, wholeStr = "";
//			while((str = br.readLine()) != null){
//			wholeStr += str;
//			}
//			System.out.println(wholeStr+">>>>>");

//			int len = request.getContentLength();
//			ServletInputStream iii = request.getInputStream();
//			byte[] buffer = new byte[len];
//			iii.read(buffer, 0, len);
//			String pwim = new String(buffer);
//			System.out.println(pwim+"<<<<");
			
//		} catch (IOException e1) {
//			
//			e1.printStackTrace();
//		}
		
		return "5";
	}
	
}
