package com.ly.service.crawler;

import java.util.List;
import java.util.Map;

/**
* @ClassName: ISiteSerivce
* @Description: 
* @author linyan
* @date 2018年1月30日 下午4:52:23
*
*/
public interface ISiteSerivce {

	
	public List<String> getSiteLine();
	
	public List<Map<String, Object>> getLineDir(String line);
	
	public List<Map<String, Object>> getSites(String line , String lineDir);
	
	public Map<String, Object> getTimeSiteLine(String line,String lineDir , String site);
	
}
