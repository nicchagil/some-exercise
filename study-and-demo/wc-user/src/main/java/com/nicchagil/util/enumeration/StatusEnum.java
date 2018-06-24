package com.nicchagil.util.enumeration;

public enum StatusEnum implements IValueNameEnum {

	ACTIVE("Y", "有效"), INACTIVE("N", "无效");

	private String value;
	private String name;

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private StatusEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
}
