package com.nicchagil.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wc")
public class WcProperties {

	private String verifyServerToken;
	private String appId;
	private String appSecret;
	private Long accessTokenLocalTimeout;

	public String getVerifyServerToken() {
		return verifyServerToken;
	}

	public void setVerifyServerToken(String verifyServerToken) {
		this.verifyServerToken = verifyServerToken;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Long getAccessTokenLocalTimeout() {
		return accessTokenLocalTimeout;
	}

	public void setAccessTokenLocalTimeout(Long accessTokenLocalTimeout) {
		this.accessTokenLocalTimeout = accessTokenLocalTimeout;
	}
	
}
