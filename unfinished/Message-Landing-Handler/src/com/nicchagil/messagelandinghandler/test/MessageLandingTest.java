package com.nicchagil.messagelandinghandler.test;

import org.junit.Test;

import com.nicchagil.messagelandinghandler.BusinessService;
import com.nicchagil.messagelandinghandler.User;

public class MessageLandingTest {
	
	@Test
	public void test01() {
		BusinessService businessService = new BusinessService();
		for (int i = 1; i <= 20; i++) {
			try {
				businessService.business(new User(i, "nick huang " + i));
			} catch (Exception e) {
			}
		}
	}

}
