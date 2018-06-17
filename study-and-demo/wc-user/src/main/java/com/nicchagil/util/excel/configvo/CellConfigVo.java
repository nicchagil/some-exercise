package com.nicchagil.util.excel.configvo;

public class CellConfigVo extends CellIndex {
	
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
