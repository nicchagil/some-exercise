package com.nicchagil.jacksonexercise;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JacksonTest {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(5);
		
		for (int i = 1; i < 100000000; i++) {
			final int index = i;
			es.execute(new Runnable() {
				@Override
				public void run() {
					JacksonTest.jsonTest(index);
				}
			});
		}
	}
	
	public static void jsonTest(int i) {
		User user = new User();
		user.setId(i);
		user.setName("Nick Huang" + i);
		user.setAge(new Random().nextInt(101));
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		
		String json = JSONUtil.toJSON(user);
		// User reUser = JSONUtil.toBean(json, new TypeReference<User>() {});
		System.out.println("json : " + json);
	}

}
