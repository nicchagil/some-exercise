package com.nicchagil.poiexercise;

import java.util.ArrayList;
import java.util.List;

public class ExcelColNameUtil {
	
	// 列名
	private static List<String> colNameList = null;
	
	static {
		/* 初始化列名 */
		colNameList = new ArrayList<String>();
		
		for (char temp = 'A'; temp <= 'Z'; temp++) {
			colNameList.add(String.valueOf(temp));
		}
		
		colNameList.add("AA");
		colNameList.add("AB");
		colNameList.add("AC");
		colNameList.add("AD");
		colNameList.add("AE");
	}
	
	/**
	 * 根据列名获取列序号
	 * @param colName 列名
	 * @return 列序号
	 */
	public static int colName2ColIndex(String colName) {
		if (colName == null || colName.trim().length() == 0) {
			throw new RuntimeException("无此列名");
		}
		
		return colNameList.indexOf(colName);
	}
	
	public static void main(String[] args) {
		System.out.println(ExcelColNameUtil.colName2ColIndex("A"));
		System.out.println(ExcelColNameUtil.colName2ColIndex("Z"));
		System.out.println(ExcelColNameUtil.colName2ColIndex("AA"));
		System.out.println(ExcelColNameUtil.colName2ColIndex("AB"));
		System.out.println(ExcelColNameUtil.colName2ColIndex("AE"));
	}
	
}
