package com.nicchagil.util.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void initTest() {
		/* 实例化安全管理器 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:com/nicchagil/util/shiro/shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
		/* 设置安全工具类 */
		SecurityUtils.setSecurityManager(securityManager);
		
		logger.info("Shiro初始化完毕");
	}
	
	@Test
	public void loginSuccessTest() {
		/* 实例化安全管理器 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:com/nicchagil/util/shiro/shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
		/* 设置安全工具类 */
		SecurityUtils.setSecurityManager(securityManager);
		
		logger.info("Shiro初始化完毕");
		
		Subject subject = SecurityUtils.getSubject();
		logger.info("登录用户：{}", subject);
		
		subject.login(new UsernamePasswordToken("nick-huang", "123456"));
		logger.info("用户（{}）登录成功", subject.getPrincipal());
	}
	
	@Test
	public void loginFailTest() {
		/* 实例化安全管理器 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:com/nicchagil/util/shiro/shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
		/* 设置安全工具类 */
		SecurityUtils.setSecurityManager(securityManager);
		
		logger.info("Shiro初始化完毕");
		
		Subject subject = SecurityUtils.getSubject();
		logger.info("登录用户：{}", subject);
		
		subject.login(new UsernamePasswordToken("nick-huang", "123456789"));
		logger.info("用户（{}）登录成功", subject.getPrincipal());
	}
	

	@Test
	public void checkRoleTest() {
		/* 实例化安全管理器 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:com/nicchagil/util/shiro/shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
		/* 设置安全工具类 */
		SecurityUtils.setSecurityManager(securityManager);
		
		logger.info("Shiro初始化完毕");
		
		Subject subject = SecurityUtils.getSubject();
		logger.info("登录用户：{}", subject);
		
		subject.login(new UsernamePasswordToken("hello-kitty", "789456"));
		logger.info("用户（{}）登录成功", subject.getPrincipal());
		
		logger.info("用户（{}）是否有{}角色：{}", subject.getPrincipal(), "admin", subject.hasRole("admin"));
		logger.info("用户（{}）是否有{}角色：{}", subject.getPrincipal(), "tester", subject.hasRole("tester"));
		
		logger.info("用户（{}）是否有{}权限：{}", subject.getPrincipal(), "user:update", subject.isPermitted("user:update"));
		logger.info("用户（{}）是否有{}权限：{}", subject.getPrincipal(), "user:delete", subject.isPermitted("user:delete"));
	}

}
