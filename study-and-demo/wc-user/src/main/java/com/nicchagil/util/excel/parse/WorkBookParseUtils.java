package com.nicchagil.util.excel.parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.util.Assert;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.util.excel.PoiUtils;

public class WorkBookParseUtils {
	
	private static Logger logger = LoggerFactory.getLogger(WorkBookParseUtils.class);
	
	/**
	 * 读取Excel批量数据
	 */
	public static List<Map<String, String>> getBatchData(Workbook wb, ExcelParseConfigVo configVo) {
		Assert.notNull(configVo, "Excel读数配置对象为空");
		
		return WorkBookParseUtils.getBatchData(wb, PoiUtils.DEFAULT_SHEET_INDEX, configVo);
	}
	
	/**
	 * 读取Excel批量数据
	 */
	public static List<Map<String, String>> getBatchData(Workbook wb, Integer sheetIndex, ExcelParseConfigVo configVo) {
		Assert.notNull(configVo, "Excel写数配置对象为空");
		int startRowNum = configVo.getBatchDataListStartCellIndex().getRow();
		int startColNum = configVo.getBatchDataListStartCellIndex().getColumn();
		String[] conlumnKeys = configVo.getBatchDataColumnKey();
		
		Sheet sheet = wb.getSheetAt(sheetIndex);

		List<Map<String, String>> dataList = Lists.newArrayList();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = startRowNum; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			
			// 无行截断
			if (row == null) {
				break;
			}
			
			Map<String, String> dataMap = new HashMap<>();
			int lastCellNum = conlumnKeys.length;
			for (int j = startColNum; j < lastCellNum; j++) {
				Cell cell = row.getCell(j);
				String cellValue = WorkBookParseUtils.getCellValueOnString(cell);
				
				dataMap.put(conlumnKeys[j - startColNum], cellValue);
			}
			dataList.add(dataMap);
		}
		
		return dataList;
	}
	
	/**
     * 将Excel以输出流形式导出到浏览器
     * @param workbook Excel信息
     * @param fileName 文件名
     * @param response HTTP J2EE响应
     */
    public static String getValue(Sheet sheet, int rowNum, Integer columnNum) {
        if (sheet == null) {
        	return StringUtils.EMPTY;
        }
        
        Row row = sheet.getRow(rowNum);
        if (row == null) {
        	return StringUtils.EMPTY;
        }
        
        Cell cell = row.getCell(columnNum);
        if (cell == null) {
        	return StringUtils.EMPTY;
        }
        
        return WorkBookParseUtils.getCellValueOnString(cell);
    }
    
    /**
	 * 获取单元格的字符串值
	 * @param c 单元格
	 * @return 字符串值
	 */
	public static String getCellValueOnString(Cell c) {
		if (c == null) {
			return StringUtils.EMPTY;
		}
		
		CellType cellType = c.getCellTypeEnum();
		
		String value = null;
		if (cellType == CellType._NONE) {
			value = StringUtils.EMPTY;
		} else if (cellType == CellType.NUMERIC) {
			value = String.valueOf(c.getNumericCellValue());
		} else if (cellType == CellType.STRING) {
			value = c.getStringCellValue();
		} else if (cellType == CellType.FORMULA) {
			value = c.getCellFormula();
		} else if (cellType == CellType.BLANK) {
			value = StringUtils.EMPTY;
		} else if (cellType == CellType.BOOLEAN) {
			value = String.valueOf(c.getBooleanCellValue());
		} else if (cellType == CellType.ERROR) {
			value = String.valueOf(c.getErrorCellValue());
		}
		
		logger.info("{} - {}", cellType, value);
		
		return value;
	}

}
