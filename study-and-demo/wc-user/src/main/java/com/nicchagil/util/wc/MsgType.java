package com.nicchagil.util.wc;

public enum MsgType {

	TEXT("text", "文本消息");

	private String code;
	private String name;

	private MsgType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
