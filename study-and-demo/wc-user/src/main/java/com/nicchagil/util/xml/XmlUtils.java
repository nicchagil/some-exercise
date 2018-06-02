package com.nicchagil.util.xml;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

public class XmlUtils {
	
	private Logger logger = LoggerFactory.getLogger(XmlUtils.class);
	
	/**
	 * XML转换为Bean
	 */
	public static <T> T toBean(XStream xstream, Class<T> clazz, String xml) {
		if (xml == null || xml.trim().length() == 0) {
			return null;
		}
		
		Object obj = xstream.fromXML(xml);
		if (obj != null) {
			return clazz.cast(obj);
		}
		
		return null;
	}
	
	/**
	 * Bean转换为XML
	 */
	public static String toXml(XStream xstream, Object obj) {
		if (obj == null) {
			return null;
		}
		
		return xstream.toXML(obj);
	}
	
	@Test
	public void toXml() {
		User user = new User();
		user.setId(123);
		user.setName("Nick Huang");
		user.setAge(18);
		user.setSex(1);
		user.setAddress("深圳");
		
		XStream xstream = new XStream();
		xstream.alias(User.class.getSimpleName(), User.class);
		
		String xml = xstream.toXML(user);
		this.logger.info("xml -> {}", xml);
	}
	
	@Test
	public void toBean() {
		String xml = "<xml><id>123</id><name>Nick Huang</name><age>18</age><sex>1</sex><address>深圳</address></xml>";
		
		XStream xstream = new XStream();
		xstream.alias("xml", User.class);
		
		Object obj = xstream.fromXML(xml);
		this.logger.info("bean -> {}", obj);
	}
	
	@Test
	public void toBeanGenericTest() {
		String xml = "<xml><id>123</id><name>Nick Huang</name><age>18</age><sex>1</sex><address>深圳</address></xml>";
		
		XStream xstream = new XStream();
		xstream.alias("xml", User.class);
		
		User user = XmlUtils.toBean(xstream, User.class, xml);
		this.logger.info("user -> {}", user);
	}
	
	public static class User {
		private Integer id;
		private String name;
		private Integer age;
		private Integer sex;
		private String address;

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

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Integer getSex() {
			return sex;
		}

		public void setSex(Integer sex) {
			this.sex = sex;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("User [id=").append(id).append(", name=").append(name).append(", age=").append(age)
					.append(", sex=").append(sex).append(", address=").append(address).append("]");
			return builder.toString();
		}
		
	}

}
