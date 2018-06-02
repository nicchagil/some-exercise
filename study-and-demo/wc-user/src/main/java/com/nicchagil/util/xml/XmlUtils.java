package com.nicchagil.util.xml;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

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
	
	/**
	 * Bean转换为XML（其实不用这么处理，Xstream会自动转义）
	 */
	@Deprecated
	public static String toXmlWithDecorateCdata(XStream xstream, Object obj) {
		if (obj == null) {
			return null;
		}
		
		XmlUtils.decorateCdata(obj);
		
		return xstream.toXML(obj);
	}
	
	/**
	 * 用CDATA装饰对象中的String属性值（其实不用这么处理，Xstream会自动转义）
	 */
	@Deprecated
	public static Object decorateCdata(Object obj) {
		if (obj == null) {
			return null;
		}
		
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(obj.getClass());
		for (PropertyDescriptor p : propertyDescriptors) {
			Class<?> clazz = p.getPropertyType();
			
			if (String.class.equals(clazz)) {
				Method readMethod = p.getReadMethod();
				Method writeMethod = p.getWriteMethod();
				
				try {
					/* 获取属性值 */
					Object result = readMethod.invoke(obj, null);
					
					/* 设置加上了CDATA的属性值 */
					if (result != null) {
						String resultStr = result.toString();
						
						writeMethod.invoke(obj, "< ![CDATA[" + resultStr + "] ]>");
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return obj;
	}
	
	@Test
	public void toXmlTest() {
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
	public void toXmlWithDecorateCdataTest() {
		User user = new User();
		user.setId(123);
		user.setName("Nick Huang");
		user.setAge(18);
		user.setSex(1);
		user.setAddress("深圳");
		
		XStream xstream = new XStream();
		xstream.alias(User.class.getSimpleName(), User.class);
		
		String xml = XmlUtils.toXmlWithDecorateCdata(xstream, user);
		this.logger.info("xml -> {}", xml);
	}
	
	@Test
	public void toBeanTest() {
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
