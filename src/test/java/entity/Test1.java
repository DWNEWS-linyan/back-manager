package entity;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @ClassName: Test1
* @Description: 
* @author linyan
* @date 2017年9月26日 上午10:40:46
*
*/
public class Test1 {

	private  String  abc;
	
	private  Integer inte;
	
	private  boolean bool;
	
	private  char  ch;
	
	private  double dou;
	
	private  long lo;

	private  Map<String,Object> map;
	
	private  List<String>  list;
	
	private  Set<Test2> set;
	
	private  String[] strs;
	
	private  File file;
	
	private  Test2 test2;

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	public Integer getInte() {
		return inte;
	}

	public void setInte(Integer inte) {
		this.inte = inte;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public double getDou() {
		return dou;
	}

	public void setDou(double dou) {
		this.dou = dou;
	}

	public long getLo() {
		return lo;
	}

	public void setLo(long lo) {
		this.lo = lo;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Set<Test2> getSet() {
		return set;
	}

	public void setSet(Set<Test2> set) {
		this.set = set;
	}

	public String[] getStrs() {
		return strs;
	}

	public void setStrs(String[] strs) {
		this.strs = strs;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Test2 getTest2() {
		return test2;
	}

	public void setTest2(Test2 test2) {
		this.test2 = test2;
	}
	
}
