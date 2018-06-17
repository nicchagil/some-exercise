package com.nicchagil.util.excel.configvo;

/**
 * 单元格坐标
 */
public class CellIndex {

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
