package com.nicchagil.jvmexercise.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyClassLoader extends ClassLoader {

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = null;
		
		/* 从指定路径读取Class */
		try (FileInputStream fis = new FileInputStream(
				"D:/my_classload_classpath/" + name + ".class")) {
			
			/* 读取字节 */
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int ch = 0;
			while (-1 != (ch = fis.read())) {
				baos.write(ch);
			}
			bytes = baos.toByteArray();
		} catch (FileNotFoundException e) {
			throw new ClassNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		/* 读取不到Class */
		if (bytes == null) {
			throw new ClassNotFoundException();
		}

		return this.defineClass(name, bytes, 0, bytes.length);
	}
	
	/**
	 * 打印ClassLoad层级
	 * @param classLoader 类加载器
	 */
	public static void printClassLoadHierarchy(ClassLoader classLoader) {
		int depth = 0;
		
		System.out.println("depth " + ++depth +  " : " + classLoader);
		ClassLoader parentClassLoader = null;
		
		do {
			parentClassLoader = classLoader.getParent();
			System.out.println("depth " + ++depth +  " : " + parentClassLoader);
			classLoader = parentClassLoader;
		} while (classLoader != null);
	}
	
	public static void main(String[] args) throws Exception {
		MyClassLoader myClassLoader = new MyClassLoader();
		Class<?> clazz = myClassLoader.loadClass("User");
		System.out.println("clazz.newInstance() : " + clazz.newInstance());
		System.out.println("clazz.getClassLoader() : " + clazz.getClassLoader());
		System.out.println("打印ClassLoad层级：");
		printClassLoadHierarchy(myClassLoader);
	}

}
