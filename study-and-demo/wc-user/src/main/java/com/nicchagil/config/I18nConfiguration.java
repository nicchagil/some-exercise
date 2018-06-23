package com.nicchagil.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class I18nConfiguration {

	@Bean
	public LocaleResolver localeResolver() {
		// 会话区域解析器
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		
		// 设置区域会话默认值
		sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		
		return sessionLocaleResolver;
	}

}
