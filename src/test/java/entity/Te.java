package entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ly.utils.EntityToString;

/**
* @ClassName: Te
* @Description: 
* @author linyan
* @date 2017年9月26日 上午10:40:25
*
*/
public class Te {

	
	public static void masin(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "gas;ldkf");
		map.put("ex", 45);
		File file = new File("d:/test.txt");
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		String[] strs = new String[3];
		strs = list.toArray(strs);
		Test1 test1 = new Test1();
		test1.setAbc("agcsgdb");
		test1.setBool(false);
		test1.setCh('o');
		test1.setDou(5465.15);
		test1.setFile(file);
		test1.setInte(123456);
		test1.setList(list);
		test1.setLo(987654L);
		test1.setMap(map);
		test1.setStrs(strs);
		Test2 test2 = new Test2();
		test2.setA("aaaaaaaaaaaa");
		test2.setTest1(test1);
		test1.setTest2(test2);
		Set<Test2> set = new HashSet<Test2>();
		set.add(test2);
		Test2 test22 = new Test2();
		test22.setA("bbbbbbbbbbb");
		test22.setTest1(test1);
		set.add(test22);
		test1.setSet(set);
		String entityToString = EntityToString.getString(test1,2);
		System.out.println(entityToString);
	}
	
	
	
	public static void main(String[] args) {
		
		
	}
	
	
}
