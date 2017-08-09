package com.nicchagil.poiexercise;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	/**
	 * 根据文件路径加载Workbook
	 * @param filePath 文件路径
	 * @return Workbook 工作簿
	 */
	public static Workbook getWorkbookByFilePath(String filePath) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new File(filePath));
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new RuntimeException(e);
		}
		return wb;
	}
	
	/**
	 * 获取Sheet页的数据集合
	 * @param sheet 页签
	 * @return Sheet页的数据集合
	 */
	public static List<ArrayList<String>> getSimpleDataList(Sheet sheet) {
		if (sheet == null) {
			return null;
		}
		
		List<ArrayList<String>> list = new LinkedList<ArrayList<String>>();
		int lastRowNum = sheet.getLastRowNum();
		Row row = null;
		for (int i = 0; i <= lastRowNum; i++) {
			row = sheet.getRow(i);
			list.add(ExcelUtil.getRowData(row));
		}
		
		return list;
	}
	
	/**
	 * 获取行的值的集合
	 * @param row 行
	 * @return 行的值的集合
	 */
	public static ArrayList<String> getRowData(Row row) {
		if (row == null) {
			return null;
		}
		
		int lastCellNum = row.getLastCellNum();
		ArrayList<String> list = new ArrayList<String>();
		
		for (int i = 0; i <= lastCellNum; i++) {
			list.add(ExcelUtil.getCellValueOnString(row.getCell(i)));
		}
		
		return list;
	}
	
	/**
	 * 获取单元格的字符串值
	 * @param c 单元格
	 * @return 字符串值
	 */
	public static String getCellValueOnString(Cell c) {
		if (c == null) {
			return "";
		}
		
		int cellType = c.getCellType();
		if (cellType == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(c.getNumericCellValue());
		} else if (cellType == Cell.CELL_TYPE_STRING) {
			return c.getStringCellValue();
		} else if (cellType == Cell.CELL_TYPE_FORMULA) {
			return c.getCellFormula();
		} else if (cellType == Cell.CELL_TYPE_BLANK) {
			return "";
		} else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(c.getBooleanCellValue());
		} else if (cellType == Cell.CELL_TYPE_ERROR) {
			return String.valueOf(c.getErrorCellValue());
		}
		
		throw new RuntimeException("无此单元格类型");
	}
	
	public static void main(String[] args) {
		Workbook wb = ExcelUtil.getWorkbookByFilePath("d:/123.xls");
		Sheet sheet = wb.getSheetAt(0);
		List<ArrayList<String>> list = ExcelUtil.getSimpleDataList(sheet);
		for (ArrayList<String> tempList : list) {
			System.out.println(tempList);
		}
	}

}
