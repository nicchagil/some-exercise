package com.nicchagil.util.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class UsernamePassword {
	
	private String username;
	private String password;

	public UsernamePassword(AuthenticationToken token) {
		if (token == null) {
			return;
		}
		
		if (token.getPrincipal() != null) {
			username = token.getPrincipal().toString();
		}
		
		if (token.getCredentials() != null) {
			password = token.getCredentials().toString();
		}
		
		return;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
