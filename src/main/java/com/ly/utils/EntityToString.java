package com.ly.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: EntityToString
* @Description: entity  pojo  vo  转换为  string  
* @author linyan
* @date 2017年9月22日 上午11:08:32
*
*/
public class EntityToString {

	//定义基本类型
	static Map<Class<?>, Object> classes = new HashMap<Class<?>, Object>();
	//定义超过此长度截取
	static Integer subStrLength = 200;
	
	static{
		classes.put(byte.class, "");
		classes.put(short.class, "");
		classes.put(int.class, "");
		classes.put(long.class, "");
		classes.put(float.class, "");
		classes.put(double.class, "");
		classes.put(char.class, "");
		classes.put(boolean.class, "");
		classes.put(Byte.class, "");
		classes.put(Short.class, "");
		classes.put(Integer.class, "");
		classes.put(Long.class, "");
		classes.put(Float.class, "");
		classes.put(Double.class, "");
		classes.put(Character.class, "");
		classes.put(Boolean.class, "");
		classes.put(String.class, "");
	}
	
	/**
	 * @Title: getString
	 * @author: linyan
	 * @Description: 
	 * @param o  需要转换的对象
	 * @return
	 */
	public static String getString(Object o) {
		return getString(o,1);
	}
	
	/**
	 * @Title: getString
	 * @author: linyan
	 * @Description: 
	 * @param o  需要转换的对象
	 * @param endLeve  结束 等级 
	 * @return
	 */
	public static String getString(Object o,Integer endLeve ) {
		return getString(o,0,endLeve);
	}
	
	/**
	 * @Title: getString
	 * @author: linyan
	 * @Description: 
	 * @param o		需要转换的对象
	 * @param leve	等级
	 * @param endLeve  结束 等级 
	 * @return
	 */
	public static String getString(Object o,Integer leve,Integer endLeve )    
    {    
		Integer lev = leve + 1;
		Class<?> c = o.getClass();
        String result = "";
        if (leve==0) {
			result+=c.getSimpleName();
		}
		result+= "{";
        // 获取类中的所有定义字段
        Field[] fields = c.getDeclaredFields();    
        //循环遍历字段，获取字段对应的值
        for (Field field : fields)
        {    
            // 如果不为空，设置可见性(也就是设置私有变量可以访问)，然后返回    
            field.setAccessible(true);    
            try
            {
            	//有值
            	if(field.get(o) != null){
            		Class<?> fieldClass = field.get(o).getClass();
            		//如果是基本类型 直接 取值   用get直接取
            		if(classes.containsKey(fieldClass)){
            			result += field.getName() + "=" + subStr(field.get(o)) +",";   
            		}else if(field.get( o ) instanceof Iterable ){  // 迭代器  类型的 
            			result += field.getName()+ " = ";
            			result += iterableClass(field.get( o ),leve,endLeve);
            		}else if(field.get( o ) instanceof Map){ //  Map  类型的 
            			result += field.getName( )+ " =  ";
            			result += mapClass(field.get(o), leve, endLeve);
					}else if(fieldClass.isArray()){			// 数组类型
						Object object = field.get(o);
						result += field.getName()+ " = ";
						result += arrayClass(object, leve, endLeve);
					}else { // 其他  类型的 
						/*这个判断是我 自己加的  只对  我现在的项目   有用 
							判断 字段值 的   包名  中 是否 有 entity   并且    现在的层级 不能  大于 结束 层级
							如果有  就是 自己写的类  那么字段的 值 也 要 生成 为 string 类型
						*/
            			if (fieldClass.getCanonicalName().indexOf("entity")>-1&&leve!=endLeve) {
        	                result += field.getName() + "=" + getString(field.get(o),lev,endLeve) +",";    
        				}else { // 这个就是真正的 其他类型了   如  file  等
        	                result += field.getName() + "=" + field.get(o) +",";    
        				}
					}
            	}else {
            		//没有值
            		result += field.getName() + "=" + field.get(o) +",";
				}
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        //去除 最后一个 ， 
        if(result.indexOf(",")>=0) result = result.substring(0 , result.length()-1);
        
        return result+"}";    
    }
	
	/**
	 * @Title: iterableClass
	 * @author: linyan
	 * @Description: 迭代器 类型
	 * @param obj
	 * @param leve
	 * @param endLeve
	 * @return
	 */
	public static String iterableClass(Object obj,Integer leve,Integer endLeve){
		String result = "[";
		Iterable<?> ite = (Iterable<?>)obj;
		for (Object object : ite) {
			if(object != null){
				if (classes.containsKey(object.getClass())) {
					result += subStr(object) +",";
				}else if(object instanceof Iterable){
					result += iterableClass(object,leve,endLeve);
				}else if(object instanceof Map){
					result += mapClass(object, leve, endLeve);
				}else if(object.getClass().isArray()){
					result += arrayClass(object, leve, endLeve);
				}else {
					result += elseClass(object,leve,endLeve);
				}
			}else{
				result += null;
			}
		}
		if(result.indexOf( "," )>=0) result = result.substring( 0 , result.length()-1 );
		result += "],";
		return result;
	}
	
	/**
	 * @Title: mapClass
	 * @author: linyan
	 * @Description:  Map 类型
	 * @param obj
	 * @param leve
	 * @param endLeve
	 * @return
	 */
	public static String mapClass(Object obj,Integer leve,Integer endLeve){
		String result = "{";
		Map<?,?> map1 = (Map<?,?>)obj;
		for (Map.Entry<?, ?> entry1 : map1.entrySet()) {
			if (entry1.getValue() != null) {
				if (classes.containsKey(entry1.getValue().getClass())) {
					result += entry1.getKey()+"="+  subStr(entry1.getValue())  +",";    
				}else if(entry1.getValue() instanceof Iterable){
					result += entry1.getKey()+ "=" + iterableClass(entry1.getValue(),leve,endLeve);
				}else if(entry1.getValue() instanceof Map){
					result +=  entry1.getKey()+ "=" + mapClass(entry1.getValue(),leve,endLeve);
				}else if(obj.getClass().isArray()){
					result +=  entry1.getKey()+ "=" + arrayClass(entry1.getValue(), leve, endLeve);
				}else {
					result += entry1.getKey()+ "=" + elseClass(entry1.getValue(),leve,endLeve);
				}
			}else{
				result += null;
			}
		}
		if(result.indexOf( "," )>=0) result = result.substring( 0 , result.length()-1 );
		result += "},";
		return result;
	}
	
	/**
	 * @Title: arrayClass
	 * @author: linyan
	 * @Description:  数组 类型
	 * @param object
	 * @param leve
	 * @param endLeve
	 * @return
	 */
	public static String arrayClass(Object object,Integer leve,Integer endLeve){
		String result = "[";
		int length = Array.getLength(object) ;
		for (int i = 0; i < length; i++) {
			Object object2 = Array.get(object,i);
			if (object2!=null) {
				if (classes.containsKey(object2.getClass())) {
					result += subStr(object2)+",";
				}else if(object2 instanceof Iterable){
					result += iterableClass(object2,leve,endLeve);
				}else if (object2 instanceof Map) {
					result += mapClass(object2, leve, endLeve);
				}else if (object2.getClass().isArray()) {
					result += arrayClass(object2, leve, endLeve);
				}else {
					result += elseClass(object2,leve,endLeve);
				}
			}else{
				result += null;
			}
		}
		if(result.indexOf( "," )>=0) result = result.substring( 0 , result.length()-1 );
		result += "],";
		return result;
	}
	
	/**
	 * @Title: elseClass
	 * @author: linyan
	 * @Description: 其他类型
	 * @param object
	 * @param leve
	 * @param endLeve
	 * @return
	 */
	public static String elseClass(Object object,Integer leve,Integer endLeve){
		String result = "";
		Integer le = leve + 1;
		if (object.getClass().getCanonicalName().indexOf("entity")>-1&&leve!=endLeve) {
    		// 设置字段可见，即可用get方法获取属性值。    
            result += getString(object,le,endLeve) +",";    
		}else {
			// 设置字段可见，即可用get方法获取属性值。    
			result += object+",";
		}
		return result;
	}
	
	
	/**
	 * @Title: subStr
	 * @author: linyan
	 * @Description: 截取字符串
	 * @param object
	 * @return
	 */
	public static String subStr(Object object){
		if (object!=null) {
			if (object.toString().length()>subStrLength) {
				return object.toString().substring(0, subStrLength);
			}else{
				return object.toString();
			}
		}else{
			return "";
		}
	}
	
	
	
}
