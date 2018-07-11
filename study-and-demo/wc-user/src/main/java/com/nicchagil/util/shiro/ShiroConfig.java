package com.nicchagil.util.shiro;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
	@Bean
    public SecurityManager securityManager() {
		/* 实例化安全管理器 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:com/nicchagil/util/shiro/shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		return securityManager;
	}

}
