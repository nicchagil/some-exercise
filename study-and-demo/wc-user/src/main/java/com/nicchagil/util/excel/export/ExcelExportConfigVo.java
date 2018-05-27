package com.nicchagil.util.excel.export;

import java.util.List;
import java.util.Map;

public class ExcelExportConfigVo {

	/** 需要填充的零散的单元格 **/
	private Map<CellIndex, String> scatteredCellList;

	/** 批量导出的数据的单元格坐标 **/
	private CellIndex batchDataListStartCellIndex;

	/** 批量导出的数据集合 **/
	private List<?> batchDataList;

	/** 批量导出的数据的键 **/
	private String[] batchDataColumnKey;

	public Map<CellIndex, String> getScatteredCellList() {
		return scatteredCellList;
	}

	public void setScatteredCellList(Map<CellIndex, String> scatteredCellList) {
		this.scatteredCellList = scatteredCellList;
	}

	public CellIndex getBatchDataListStartCellIndex() {
		return batchDataListStartCellIndex;
	}

	public void setBatchDataListStartCellIndex(CellIndex batchDataListStartCellIndex) {
		this.batchDataListStartCellIndex = batchDataListStartCellIndex;
	}

	public List<?> getBatchDataList() {
		return batchDataList;
	}

	public void setBatchDataList(List<?> batchDataList) {
		this.batchDataList = batchDataList;
	}

	public String[] getBatchDataColumnKey() {
		return batchDataColumnKey;
	}

	public void setBatchDataColumnKey(String[] batchDataColumnKey) {
		this.batchDataColumnKey = batchDataColumnKey;
	}

	/**
	 * 单元格坐标
	 */
	public static class CellIndex {
		private Integer row;
		private Integer column;

		public CellIndex(Integer row, Integer column) {
			super();
			this.row = row;
			this.column = column;
		}

		public Integer getRow() {
			return row;
		}

		public void setRow(Integer row) {
			this.row = row;
		}

		public Integer getColumn() {
			return column;
		}

		public void setColumn(Integer column) {
			this.column = column;
		}

	}

}
