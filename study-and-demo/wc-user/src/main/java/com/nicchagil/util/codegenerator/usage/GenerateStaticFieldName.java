package com.nicchagil.util.codegenerator.usage;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.nicchagil.util.codegenerator.CodeGenerateUtils;
import com.nicchagil.util.codegenerator.JavaFileConfig;
import com.nicchagil.util.codegenerator.User;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 以A类的所有属性名，生成一个类的所有静态的属性名（这需求看起来貌似有点古怪哈，我是用生成后的类属性作引用，避免以后实体类的字段更新，程序要改很多地方）
 * @author Administrator
 *
 */
public class GenerateStaticFieldName {
	
	public static void main(String[] args) throws Exception {
		GenerateStaticFieldName.generateClassFieldNameClass("com.nicchagil.util.codegenerator.output", User.class);
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

}
