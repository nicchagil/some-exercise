package com.nicchagil.springbootexercise.controller;

public class UserPage extends Page {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserPage [name=" + name + ", getPage()=" + getPage() + ", getPageSize()=" + getPageSize() + "]";
	}

}
