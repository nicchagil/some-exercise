package com.nicchagil.util.excel.export;

import java.util.List;

public class ExcelExportConfigVo {

	/** 需要填充的零散的单元格 **/
	private List<CellConfigVo> scatteredCellList;

	/** 批量导出的数据的单元格坐标 **/
	private CellIndex batchDataListStartCellIndex;

	/** 批量导出的数据集合 **/
	private List<?> batchDataList;

	/** 批量导出的数据的键 **/
	private String[] batchDataColumnKey;

	public List<CellConfigVo> getScatteredCellList() {
		return scatteredCellList;
	}

	public void setScatteredCellList(List<CellConfigVo> scatteredCellList) {
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

	public static class CellConfigVo extends CellIndex {
		private Object value;

		public CellConfigVo(Integer row, Integer column, Object value) {
			super(row, column);
			this.value = value;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

	}

}
