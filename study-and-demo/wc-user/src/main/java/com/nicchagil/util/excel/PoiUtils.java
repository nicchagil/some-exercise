package com.nicchagil.util.excel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.CollectionUtils;

import com.nicchagil.util.classpathfile.ClassPathFileUtils;
import com.nicchagil.util.excel.configvo.CellConfigVo;
import com.nicchagil.util.excel.configvo.CellIndex;
import com.nicchagil.util.reflect.ReflectUtils;

public class PoiUtils {
	
	/** 默认的Sheet为0（也就是第一个Sheet） **/
	public static final Integer DEFAULT_SHEET_INDEX = 0; 
	
	/**
	 * 从CLASSPATH加载Workbook
	 */
	public static Workbook loadWorkbookFromClasspath(String classpath) {
		try {
			Workbook wb = WorkbookFactory.create(ClassPathFileUtils.loadFileFromClasspath(classpath));
			return wb;
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将数据写入零散的单元格
	 */
	public static void writeScatteredCell(Sheet sheet, List<CellConfigVo> cellConfigVoList) {
		Assert.notNull(sheet, "Sheet不可为空");
		
		if (CollectionUtils.isEmpty(cellConfigVoList)) {
			return;
		}
		
		cellConfigVoList.forEach(cellConfigVo -> {
			PoiUtils.writeCell(sheet, cellConfigVo);
		});
	}
	
	/**
	 * 写入批量数据
	 */
	public static void writeBatchData(Sheet sheet, CellIndex cellIndex, List<?> batchDataList, String[] columnKeys) {
		Assert.notNull(sheet, "Sheet不可为空");
		Assert.notNull(cellIndex, "批量数据起始单元格坐标不可为空");
		
		if (CollectionUtils.isEmpty(batchDataList)) {
			return;
		}
		
		if (columnKeys == null || columnKeys.length == 0) {
			return;
		}
		
		Integer rowIndex = cellIndex.getRow();
		Integer columnIndex = cellIndex.getColumn();
		
		for (int i = 0; i < batchDataList.size(); i++) {
			for (int j = 0; j < columnKeys.length; j++) {
				Object obj = batchDataList.get(i);
				String key = columnKeys[j];
				
				Object value = PoiUtils.getValueFromObject(obj, key);
				PoiUtils.writeCell(sheet, new CellConfigVo(rowIndex + i, columnIndex + j, value));
			}
		}
	}
	
	public static Object getValueFromObject(Object obj, String key) {
		Assert.hasText(key, "查询对象的属性时，属性不可为空");
		
		if (obj == null) {
			return StringUtils.EMPTY;
		}
		
		if (obj instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) obj;
			return map.get(key);
		}
		
		/* 剩下为obj为普通实体的情况 */
		return ReflectUtils.getFieldValue(obj, key);
	}
	
	/**
	 * 根据单元格坐标写入值
	 */
	public static void writeCell(Sheet sheet, CellConfigVo cellConfigVo) {
		Assert.notNull(sheet, "Sheet不可为空");
		
		Assert.notNull(cellConfigVo, "单元格配置对象不可为空");
		Assert.notNull(cellConfigVo.getRow(), "单元格配置对象的行不可为空");
		Assert.notNull(cellConfigVo.getColumn(), "单元格配置对象的列不可为空");
		Assert.notNull(cellConfigVo.getValue(), "单元格配置对象的值不可为空");
		
		Integer rowIndex = cellConfigVo.getRow();
		Integer columnIndex = cellConfigVo.getColumn();
		Object value = cellConfigVo.getValue();
		
		/* 获取行 */
		Row row = PoiUtils.getRowOrCreateRow(sheet, rowIndex);
		
		/* 获取单元格 */
		Cell cell = PoiUtils.getCellOrCreateCell(row, columnIndex);
		
		cell.setCellValue(value != null ? value.toString() : StringUtils.EMPTY);
	}
	
	/**
	 * 获取行（如果获取行为空则创建行）
	 */
	public static Row getRowOrCreateRow(Sheet sheet, Integer rowIndex) {
		Assert.notNull(sheet, "Sheet不可为空");
		Assert.notNull(sheet, "行数不可为空");
		
		Row row = sheet.getRow(rowIndex);
		
		if (row != null) {
			return row;
		}
		
		Row createRow = sheet.createRow(rowIndex);
		return createRow;
	}
	
	/**
	 * 获取单元格（如果获取单元格为空则创建单元格）
	 */
	public static Cell getCellOrCreateCell(Row row, Integer columnIndex) {
		Assert.notNull(row, "row对象不可为空");
		Assert.notNull(columnIndex, "列数不可为空");
		
		Cell cell = row.getCell(columnIndex);
		
		if (cell != null) {
			return cell;
		}
		
		Cell createCell = row.createCell(columnIndex);
		return createCell;
	}
	
}
