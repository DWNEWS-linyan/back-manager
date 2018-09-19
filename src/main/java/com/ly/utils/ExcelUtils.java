package com.ly.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @ClassName: ExcelUtils
* @Description: 
* @author linyan
* @date 2017年8月16日 下午4:17:28
*
*/
public class ExcelUtils {

	public static void main(String[] args) {
//		ExcelUtils excelUtils = new ExcelUtils();
		try {
//			excelUtils.writer();
//			
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			File file = new File("C:\\Users\\linyan\\Desktop\\7-9\\数据统计201707.xlsx");
//			excelUtils.r(file);
			
			
			String e = convertMD5(convertMD5("200820e3227815ed1756a6b531e7e0d2"));
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String convertMD5(String inStr){  
		  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
	
	
	@SuppressWarnings("resource")
	public Map<String, Object> read() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Workbook wb = new HSSFWorkbook(new FileInputStream(file));
		wb.getNumberOfSheets();
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		CellStyle style = row.getRowStyle();
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(objectMapper.writeValueAsString(style));
		row.setRowStyle(style);
		Cell cell = row.getCell(0);
		CellStyle cellStyle = cell.getCellStyle();
		System.out.println(objectMapper.writeValueAsString(cellStyle));
		String g = cell.getStringCellValue();
		System.out.println(cell.getCellTypeEnum() == CellType.STRING);
		try {
			System.out.println(Boolean.valueOf(g));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(g);
		}
		
		return map;
	}
	
	File file = new File("D:\\linyananzhuang\\gggal.xls");
	public Map<String, Object> writer() throws Exception{
		Map<String, Object> map  = new HashMap<String, Object>();
		Workbook wb = new HSSFWorkbook();
		Sheet s = wb.createSheet("测试一个");
		Row r = s.createRow(0);
		Cell c = r.createCell(0,CellType.BOOLEAN);
		c.setCellValue("trued");
		if (!file.exists()) {
			file.createNewFile();
		}
		wb.write(new FileOutputStream(file));
		wb.close();
//		Workbook wb = new XSSFWorkbook();
		return map;
	}
	
	
	
	
	
	
	
	@SuppressWarnings({ "deprecation", "resource" })
	public Object r(File file){
		Sheet hssfSheet = null;
		
		try {
			String fileName = file.getName();
			if (fileName.indexOf(".xlsx") > 0) {
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
				hssfSheet = xssfWorkbook.getSheetAt(0);
			}else {
				POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new       FileInputStream(file));
				HSSFWorkbook hssfWorkbook =  new HSSFWorkbook(poifsFileSystem);
				hssfSheet = hssfWorkbook.getSheetAt(0);
			}
			
			

			int rowstart = hssfSheet.getFirstRowNum();
			int rowEnd = hssfSheet.getLastRowNum();
			
			Row row = hssfSheet.getRow(0);
			int cellStart = row.getFirstCellNum();
		    int cellEnd = row.getLastCellNum();
		    System.out.println(cellEnd);
		    List<String> listKey = new ArrayList<String>();
		    for(int k=cellStart;k<=cellEnd;k++)
		    {
		        Cell cell = row.getCell(k);
		        if(null==cell) continue;
//		        System.out.print("" + k + "  ");
		        //System.out.print("type:"+cell.getCellType());
		       String string = cell.getStringCellValue();
		       listKey.add(string);
		    }
		    System.out.println("1行>"+listKey.size());
		    List<Map<String, Object>> listYue = new ArrayList<Map<String,Object>>();
			for(int i=rowstart+1;i<=rowEnd;i++)
			{
				
				Map<String, Object> mapInfo = new HashMap<String, Object>();
				
			    row = hssfSheet.getRow(i);
			    if(null == row) continue;
			    cellStart = row.getFirstCellNum();
			    cellEnd = row.getLastCellNum();
			    if (cellEnd < 1 ) {
					continue ;
				}
			    System.out.println(i+"行>"+cellEnd);
			    for(int k=cellStart;k<=listKey.size()-1;k++)
			    {
			        Cell cell = row.getCell(k);
			        if(null==cell) {
			        	mapInfo.put(listKey.get(k), " ");
			        	continue;
			        }
			        Object object = new Object();
//			        cell.
			        switch (cell.getCellType())
		            {
		                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
		                	object = cell.getNumericCellValue();
		                    break;
		                case HSSFCell.CELL_TYPE_STRING: // 字符串
		                	object = cell.getStringCellValue();
		                    break;
		                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
		                	object = cell.getBooleanCellValue();
		                    break;
		                case HSSFCell.CELL_TYPE_FORMULA: // 公式
		                	object = cell.getCellFormula();
		                    break;
		                case HSSFCell.CELL_TYPE_BLANK: // 空值
		                	object = " ";
		                    break;
		                case HSSFCell.CELL_TYPE_ERROR: // 故障
		                	object = " ";
		                    break;
		                default:
		                	object = " ";
		                    break;
		            }
			       
			       mapInfo.put(listKey.get(k), object);
			    }
			    System.out.print("\n");
			    listYue.add(mapInfo);
			}
			System.out.println(listYue.size()+"===");
			
			for (Map<String, Object> map : listYue) {
				String sget = "{";
				for (Map.Entry<String, Object> masp : map.entrySet()) {
					sget  += " "+ masp.getKey()+":"+masp.getValue()+",";
				}
				System.out.println(sget+"}");
				System.out.println("==");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		return "";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
