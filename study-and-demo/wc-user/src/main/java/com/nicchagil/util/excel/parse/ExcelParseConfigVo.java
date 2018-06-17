package com.nicchagil.util.excel.parse;

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
