package com.ly.service.crawler.impl;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ly.service.crawler.ICMBService;

/**
* @ClassName: CMBServiceImpl
* @Description: 
* @author linyan
* @date 2018年1月2日 上午9:49:14
*
*/
@Service
public class CMBServiceImpl implements ICMBService {
	
	/* （非 Javadoc）
	 * @see com.ly.service.crawler.ICMBService#getDollarRate(java.util.Map)
	 */
	@Override
	public Double getDollarRate(Map<String, Object> map) {
		String url = "http://m.cmbchina.com/Rate/FxRealrateDetail.aspx?name=%u7F8E%u5143";
		String divClassName = "box-flex";
		Connection connection = Jsoup.connect(url);
		Double resl = 0.0;
		try {
			Document document = connection.get();
			Elements elements = document.getElementsByClass(divClassName);
			int siz = elements.size();
			for (int i = 0; i < siz; i++) {
				Element element = elements.get(i);
				Elements cnameElements = element.children();
				if("现汇买入价".equals(cnameElements.get(0).html())){
					resl = Double.valueOf(cnameElements.get(1).html());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resl;
	}

	
	public static void main(String[] args) {
		CMBServiceImpl cmbServiceImpl = new CMBServiceImpl();
		cmbServiceImpl.getDollarRate(null);
	}
}
