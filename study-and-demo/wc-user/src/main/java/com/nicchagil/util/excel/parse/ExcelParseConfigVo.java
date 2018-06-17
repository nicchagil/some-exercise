package com.nicchagil.util.excel.parse;

import com.nicchagil.util.excel.configvo.CellIndex;

public class ExcelParseConfigVo {

	/** 批量导出的数据的单元格坐标 **/
	private CellIndex batchDataListStartCellIndex;
	
	/** 批量读取的数据的键，顺序应按导入Excel的列的顺序，键的值应该是实体的属性名称，到时可很方便地使用BeanUtils.populate()等方法将Map转换为实体Bean **/
	private String[] batchDataColumnKey;

	public CellIndex getBatchDataListStartCellIndex() {
		return batchDataListStartCellIndex;
	}

	public void setBatchDataListStartCellIndex(CellIndex batchDataListStartCellIndex) {
		this.batchDataListStartCellIndex = batchDataListStartCellIndex;
	}
	
	public String[] getBatchDataColumnKey() {
		return batchDataColumnKey;
	}

	public void setBatchDataColumnKey(String[] batchDataColumnKey) {
		this.batchDataColumnKey = batchDataColumnKey;
	}

}
