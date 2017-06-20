package com.nicchagil.entity;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -5490973977018492996L;
	
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	public User() {
		super();
	}
	
}
