package com.ly.service.crawler;

import java.util.List;
import java.util.Map;

/**
* @ClassName: IClockService
* @Description: 
* @author linyan
* @date 2018年2月2日 下午2:14:14
*
*/
public interface IClockService {

	
	List<Map<String, Object>> getClock(String statr,String end);
	
}
