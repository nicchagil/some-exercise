package com.nicchagil.util.classpathfile;

import java.io.InputStream;

import org.junit.Test;

public class ClassPathFileUtils {
	
	/**
	 * 根据CLASSPATH加载文件输入流
	 */
	public static InputStream loadFileFromClasspath(String classpath) {
		InputStream is = ClassPathFileUtils.class.getResourceAsStream(classpath);
		return is;
	}
	
	@Test
	public void loadFileFromClasspathTest() {
		InputStream is = ClassPathFileUtils.loadFileFromClasspath("/com/nicchagil/util/excel/export/TEMPLATE.XLS");
		System.out.println(is);
	}

}
