package com.nicchagil.exercise.wcuser.thirdpartyframework.map2bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.locale.converters.IntegerLocaleConverter;
import org.junit.Test;

public class Map2BeanByBeanUtils {

	@Test
	public void test1() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 123);
		map.put("name", "Nick");

		User user = new User();
		try {
			BeanUtils.populate(user, map);
			System.out.println("user : " + user);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "123");
		map.put("name", "Nick");

		User user = new User();
		try {
			BeanUtils.populate(user, map);
			System.out.println("user : " + user);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "123");
		map.put("name", "Nick");
		map.put("birthday", new Date());
		
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			System.out.println("user : " + user);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4x1() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "123.0");
		map.put("name", "Nick");
		
		User user = new User();
		try {
			BeanUtils.populate(user, map); // WRONG : User [id=0, name=Nick, birthday=null]
			
			System.out.println("user : " + user);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4x2() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", "123.0");
		map.put("name", "Nick");

		User user = new User();
		try {
			ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
			convertUtilsBean.register(new IntegerLocaleConverter(), Integer.class);
			
			BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean);
			beanUtilsBean.populate(user, map);
			
			System.out.println("user : " + user);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static class User {
		private Integer id;
		private String name;
		private Date birthday;

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

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
		}
	}

}
