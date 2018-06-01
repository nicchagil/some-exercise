package com.nicchagil.util.codegenerator;

import java.beans.PropertyDescriptor;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CodeGenerateUtils {

	public static final String SOURCE_CODE_ROOT_FOLDER = "D:/git_workspace/some-exercise/study-and-demo/wc-user/src/main/java/";

	public static void main(String[] args) throws Exception {
		CodeGenerateUtils.generateClassFieldNameClass("com.nicchagil.util.codegenerator.output", User.class);
	}
	
	/**
	 * 在指定包生成指定类的属性名组成的常量类
	 */
	public static void generateClassFieldNameClass(String packagePath, Class<?> clazz) {
		Configuration configuration = new Configuration();

		/* 配置模板目录 */
		configuration.setClassForTemplateLoading(CodeGenerateUtils.class, "/com/nicchagil/util/codegenerator/template/");
		/* 配置模板 */
		Template template;
		try {
			template = configuration.getTemplate("generate-field-final-string.ftl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String className = clazz.getSimpleName() + "Constants";

		/* 提取属性名 */
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
		List<String> fields = Lists.newArrayList();
		for (PropertyDescriptor p : propertyDescriptors) {
			if ("class".equals(p.getName())) {
				continue;
			}
			fields.add(p.getName());
		}

		/* 参数 */
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("packagePath", packagePath);
		parameterMap.put("className", className);
		parameterMap.put("fields", fields);

		CodeGenerateUtils.process(template, parameterMap, new JavaFileConfig(packagePath, className, ".java"));
	}

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
