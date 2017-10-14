package com.nicchagil.exercise.springbootexercise;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicchagil.exercise.springbootexercise.base.BaseSimpleSpringBootTest;
import com.nicchagil.exercise.springbootexercise.controller.UserController;

public class UserControllerTest extends BaseSimpleSpringBootTest {
	
	@Autowired
	private UserController userController;
	
	@Test
	public void loginTest() {
		String result = this.userController.login("", "");
		this.logger.info("结果：{}", result);
	}

}
