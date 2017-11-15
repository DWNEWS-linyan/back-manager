package com.ly.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @ClassName: MyProperties
* @Description: 
* @author linyan
* @date 2017年10月19日 下午3:57:14
*
*/
public class MyProperties {

	static MyProperties myProperties = new MyProperties();
	private static final Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");
	public static String get(String name){
		InputStream inputStream = myProperties.getClass().getClassLoader().getResourceAsStream("my.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String v =  p.getProperty(name,null);
		if (v!=null) {
			return loop(v,p);
		}
		return null;
	}
	
	private static String loop(String key,Properties props){
        //定义matcher匹配其中的路径变量
        Matcher matcher = PATTERN.matcher(key);
        StringBuffer buffer = new StringBuffer();
        boolean flag = false;
        while (matcher.find()) {
            String matcherKey = matcher.group(1);//依次替换匹配到的路径变量
            String matchervalue = props.getProperty(matcherKey);
            if (matchervalue != null) {
                matcher.appendReplacement(buffer, Matcher.quoteReplacement(matchervalue));//quoteReplacement方法对字符串中特殊字符进行转化
                flag = true;
            }
        }
        matcher.appendTail(buffer);
        //flag为false时说明已经匹配不到路径变量，则不需要再递归查找
        return flag?loop(buffer.toString(),props):key;
    }
	
}
