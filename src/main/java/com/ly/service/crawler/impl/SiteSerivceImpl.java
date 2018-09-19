package com.ly.service.crawler.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ly.service.crawler.ISiteSerivce;
import com.ly.utils.HttpUtils;

import net.sf.json.JSONObject;

/**
* @ClassName: SiteSerivceImpl
* @Description: 
* @author linyan
* @date 2018年1月30日 下午4:52:40
*
*/
@Service
public class SiteSerivceImpl implements ISiteSerivce {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	static List<String> listLine = new ArrayList<String>(); 
	
	static Map<String, List<Map<String, Object>>>  lineDirs = new HashMap<String, List<Map<String,Object>>>();
	
	static Map<String, List<Map<String, Object>>>  sites = new HashMap<String, List<Map<String,Object>>>();
	
	/* （非 Javadoc）
	 * @see com.ly.service.crawler.ISiteSerivce#getSiteLine()
	 */
	@Override
	public List<String> getSiteLine() {
		if (listLine.size()>0) {
			return listLine;
		}
		String url = "http://www.bjbus.com/home/fun_rtbus.php?uSec=00000160&uSub=00000162";
		Connection connection = Jsoup.connect(url);
		try {
			Document document = connection.get();
			Element element = document.getElementById("selBLine");
			Elements elements = element.children();
			for (Element option : elements) {
				String line = option.text();
				listLine.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listLine;
	}

	public static void main(String[] args) {
		SiteSerivceImpl serivceImpl = new SiteSerivceImpl();
//		List<Map<String, Object>> list = serivceImpl.getSites("29","4642189870853450898");
//		for (Map<String, Object> string : list) {
//			System.out.println(string.get("name")+"======"+string.get("value"));
//		}
		Map<String, Object> map = serivceImpl.getTimeSiteLine("29","4642189870853450898","3");
		for (Map.Entry<String, Object> mEntry : map.entrySet()) {
			System.out.println(mEntry.getKey()+">>"+mEntry.getValue());
		}
		
	}
	
	/* （非 Javadoc）
	 * @see com.ly.service.crawler.ISiteSerivce#getLineDir(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getLineDir(String line) {
		if (lineDirs.containsKey(line)) {
			return lineDirs.get(line);
		}
		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=getLineDirOption&selBLine="+line;
		Connection connection = Jsoup.connect(url);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			Document document = connection.get();
			Elements elements = document.getElementsByTag("option");
			for (Element option : elements) {
				Map<String, Object> map = new HashMap<String, Object>();
				String dirStr = option.text();
				String dirval = option.attr("value");
				if (dirval!=null&&!dirval.equals("")) {
					map.put("name", dirStr);
					map.put("value", dirval);
					list.add(map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (list.size()>0) {
			lineDirs.put(line, list);
		}
		return list;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.crawler.ISiteSerivce#getSites(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getSites(String line , String lineDir) {
		if (sites.containsKey(line+"_"+lineDir)) {
			return sites.get(line+"_"+lineDir);
		}
		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=getDirStationOption&selBLine="+line+"&selBDir="+lineDir;
		Connection connection = Jsoup.connect(url);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			Document document = connection.get();
			Elements elements = document.getElementsByTag("option");
			for (Element option : elements) {
				Map<String, Object> map = new HashMap<String, Object>();
				String dirStr = option.text();
				String dirval = option.attr("value");
				if (dirval!=null&&!dirval.equals("")) {
					map.put("name", dirStr);
					map.put("value", dirval);
					list.add(map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (list.size()>0) {
			sites.put(line+"_"+lineDir, list);
		}
		return list;
	}

	/* （非 Javadoc）
	 * @see com.ly.service.crawler.ISiteSerivce#getTimeSiteLine(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getTimeSiteLine(String line, String lineDir, String site) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		System.out.println(System.currentTimeMillis()+"====");
		System.out.println(new Date());
		String url = "http://www.bjbus.com/home/ajax_rtbus_data.php?act=busTime&selBLine="+line+"&selBDir="+lineDir+"&selBStop="+site;
		try {
			String re = HttpUtils.get(url);
			String reStr = unicodetoString(re);
			JSONObject jsonObject = JSONObject.fromObject(reStr);
			String html = jsonObject.getString("html");
			Document document = Jsoup.parse(html);
			//		System.out.println(document.html());
			System.out.println(System.currentTimeMillis());
			Element elementLine = document.getElementById("lh");
			String lienStr = elementLine.text();
			reMap.put("lineStr", lienStr);
			Element elementLineDir = document.getElementById("lm");
			String lineDirStr = elementLineDir.text();
			reMap.put("lineDirStr", lineDirStr);
			Elements elementsArticles = document.getElementsByTag("article");
			String strP1 = "";
			String strP2 = "";
			for (Element element : elementsArticles) {
				Elements elementsPs = element.children();
				Element elementP1 = elementsPs.get(0);
				Element elementP2 = elementsPs.get(1);
				strP1 = elementP1.text(); //  小武基桥北 5:30-22:20 分段计价 所属客二分公司
				strP2 = elementP2.text(); // 车辆均已过站       最近一辆车距离此还有 1 站， 1.90 公里，预计到站时间 3 分钟
				if (strP2 != null && !strP2.equals("") && !strP2.equals("车辆均已过站")) {
					strP2 = strP2.replace("最近一辆车距离此还有", "最近").replace("预计到站时间", "预计").replace(" ", "");
				} else {
					strP2 = "无车";
				}
				if (strP1 != null && !strP1.equals("")) {
					strP1 = strP1.replace("所属", "");
				}
			}
			reMap.put("strP1", strP1);
			reMap.put("strP2", strP2);
			Elements elementsBusc = document.getElementsByClass("busc");
			List<String> buscIdMList = new ArrayList<String>();
			for (Element element : elementsBusc) {
				Element prentElement = element.parent();
				String idm = prentElement.attr("id");
				if (idm != null && !idm.equals("")) {
					buscIdMList.add(idm);
				}
			}
			reMap.put("buscIdMList", buscIdMList);
			Elements elementsBuss = document.getElementsByClass("buss");
			List<String> bussIdList = new ArrayList<String>();
			for (Element element : elementsBuss) {
				Element prentElement = element.parent();
				String id = prentElement.attr("id");
				if (id != null && !id.equals("")) {
					bussIdList.add(id);
				}
			}
			reMap.put("bussIdList", bussIdList);
			reMap.put("code", 0);
		} catch (Exception e) {
			reMap.put("code", 1);
			reMap.put("mes", e);
			logger.error(" 刷新 出错 了     ：",e);
			e.printStackTrace();
		}
		return reMap;
	}

	
	public static String unicodetoString(String unicode){    
        if(unicode==null||"".equals(unicode)){  
            return null;  
        }  
        StringBuilder sb = new StringBuilder();    
        int i = -1;    
        int pos = 0;    
        while((i=unicode.indexOf("\\u", pos)) != -1){    
            sb.append(unicode.substring(pos, i));    
            if(i+5 < unicode.length()){    
                pos = i+6;    
                sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));    
            }    
        }    
        if (pos<unicode.length()) {
			sb.append(unicode.substring(pos));
		}
        return sb.toString();    
    }   
	
	
}
