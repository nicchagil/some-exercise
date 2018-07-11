package com.nicchagil.util.shiro.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.Assert;

import com.nicchagil.util.shiro.UsernamePassword;

public class BaseTestRealm implements Realm {

	/**
	 * 返回Realm名字
	 */
	@Override
	public String getName() {
		return BaseTestRealm.class.getName();
	}

	/**
	 * 是否支持指定的授权令牌
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		boolean supportToken = token instanceof UsernamePasswordToken;
		return supportToken;
	}

	/**
	 * 根据授权令牌获取授权信息，如果授权令牌不正确（如账号密码错误），请抛出异常
	 */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePassword usernamePassword = new UsernamePassword(token);
		
		Assert.notNull(usernamePassword, "请输入账号密码");
		Assert.hasText(usernamePassword.getUsername(), "请输入账号");
		Assert.hasText(usernamePassword.getPassword(), "请输入密码");
		
		Assert.isTrue("".equals(usernamePassword.getUsername()) || "".equals(usernamePassword.getPassword()), "请输入正确的账号密码");
		return new SimpleAuthenticationInfo(usernamePassword.getUsername(), usernamePassword.getPassword(), getName());
	}
	
}
