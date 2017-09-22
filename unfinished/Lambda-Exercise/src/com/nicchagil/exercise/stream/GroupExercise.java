package com.nicchagil.exercise.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupExercise {

	public static void main(String[] args) {
		/* 准备数据 */
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			if (i == 2) {
				list.add(new User(null));
			} else {
				list.add(new User(i));
			}
		}

		/* 按insert、update分组 */
		Map<String, List<User>> map = list.stream().collect(Collectors.groupingBy(item -> {
			if (item.getId() == null) {
				return "insert";
			} else {
				return "update";
			}
		}));
		
		System.out.println("map -> " + map);

	}

	public static class User {
		private Integer id;

		public Integer getId() {
			return id;
		}

		public User(Integer id) {
			super();
			this.id = id;
		}
	}

}
