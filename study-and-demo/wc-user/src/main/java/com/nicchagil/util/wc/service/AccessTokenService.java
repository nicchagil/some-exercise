package com.nicchagil.util.wc.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nicchagil.properties.WcProperties;
import com.nicchagil.util.wc.WcCommonUtils;

@Service
public class AccessTokenService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private WcProperties wcProperties;
	
	public static String REDIS_ACCESS_TOKEN = "REDIS_ACCESS_TOKEN";
	
	/**
	 * 获取ACCESS_TOKEN
	 */
	public String getAccessToken() {
		String accessToken = this.stringRedisTemplate.opsForValue().get(REDIS_ACCESS_TOKEN);
		
		if (StringUtils.isEmpty(accessToken)) {
			String accessTokenNewGet = this.refreshAccessToken();
			return accessTokenNewGet;
		}
		
		return accessToken;
	}
	
	/**
	 * 重新获取AccessToken并返回
	 */
	public String refreshAccessToken() {
		String accessToken = WcCommonUtils.getAccessToken(wcProperties.getAppId(), wcProperties.getAppSecret());
		this.stringRedisTemplate.opsForValue().set(REDIS_ACCESS_TOKEN, accessToken, wcProperties.getAccessTokenLocalTimeout(), TimeUnit.SECONDS);
		
		this.logger.info("refreshAccessToken : {}", accessToken);
		return accessToken;
	}

}
