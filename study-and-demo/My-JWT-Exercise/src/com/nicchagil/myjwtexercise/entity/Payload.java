package com.nicchagil.myjwtexercise.entity;

public class Payload {

	private String userId;

	public Payload(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
