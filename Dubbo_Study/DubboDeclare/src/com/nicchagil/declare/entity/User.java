package com.nicchagil.declare.entity;

import java.io.Serializable;

public class User implements Serializable {

	private Integer id;
	private String username;

	public User(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}

}
