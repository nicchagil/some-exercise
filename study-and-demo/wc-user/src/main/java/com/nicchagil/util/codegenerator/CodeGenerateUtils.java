package com.nicchagil.util.codegenerator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.apache.poi.util.IOUtils;

import freemarker.template.Template;

public class CodeGenerateUtils {

	public static final String SOURCE_CODE_ROOT_FOLDER = "D:/git_workspace/some-exercise/study-and-demo/wc-user/src/main/java/";

	/**
	 * 执行模板并输出
	 */
	public static void process(Template template, Map<String, Object> parameterMap, JavaFileConfig javaFileConfig) {
		String sourceCodePath = SOURCE_CODE_ROOT_FOLDER + javaFileConfig.getPackagePath().replace(".", "/") + "/"
				+ javaFileConfig.getClassName() + javaFileConfig.getFileSuffix();

		CodeGenerateUtils.process(template, parameterMap, sourceCodePath);
	}

	/**
	 * 执行模板并输出
	 */
	public static void process(Template template, Map<String, Object> parameterMap, String fileName) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			fos = new FileOutputStream(fileName);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);

			// 执行模板，输出到输出流
			template.process(parameterMap, bw);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(bw);
			IOUtils.closeQuietly(osw);
			IOUtils.closeQuietly(fos);
		}
	}

}
