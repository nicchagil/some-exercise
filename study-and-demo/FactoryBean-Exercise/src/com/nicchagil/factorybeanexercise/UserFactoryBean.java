package com.nicchagil.factorybeanexercise;

import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {

	/**
	 * 获取对象
	 */
	@Override
	public User getObject() throws Exception {
		User user = new User();
		user.setId(123);
		user.setName("Nick Huang");
		return user;
	}

	/**
	 * 获取对象的类型
	 */
	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	/**
	 * 对象是否单例
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
