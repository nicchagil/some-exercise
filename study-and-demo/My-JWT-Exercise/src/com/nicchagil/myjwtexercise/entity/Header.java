package com.nicchagil.myjwtexercise.entity;

public class Header {

	private String type;
	private String algorithm;

	public Header(String type, String algorithm) {
		super();
		this.type = type;
		this.algorithm = algorithm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
