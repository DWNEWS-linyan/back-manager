package com.ly.utils;

/**
* @ClassName: ConstantsEum
* @Description: 
* @author linyan
* @date 2018年1月21日 上午9:37:56
*
*/
public enum ConstantsEnum {

	One("","");
	
	private String tableName ;
	private String typeName ; 
	
	private ConstantsEnum(String tableName,String typeName){
		this.tableName = tableName;
		this.typeName = typeName;
	}
	public String getTableName() {
		return tableName;
	}
	public String getTypeName() {
		return typeName;
	}
	
}
