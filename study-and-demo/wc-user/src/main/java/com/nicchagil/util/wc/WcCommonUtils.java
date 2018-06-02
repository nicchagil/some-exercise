package com.nicchagil.util.wc;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.fluent.Request;
import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.nicchagil.util.JsonUtils;
import com.nicchagil.util.http.UrlUtils;
import com.nicchagil.util.http.UrlUtils.KeyVal;

public class WcCommonUtils {

	private static Logger logger = LoggerFactory.getLogger(WcCommonUtils.class);

	/**
	 * 获取访问令牌
	 */
	public static AccessTokenResultVo getAccessTokenResult(String appId, String appSecret) {
		String url = UrlUtils.concatParameter("https://api.weixin.qq.com/cgi-bin/token", Lists.newArrayList(
				new KeyVal("grant_type", "client_credential"), 
				new KeyVal("appid", appId), 
				new KeyVal("secret", appSecret) ));
		try {
			String result = Request.Get(url).connectTimeout(1000).socketTimeout(1000)
					.execute().returnContent().asString();

			logger.info("access token result : {}", result);

			AccessTokenResultVo accessTokenResultVo = JsonUtils.toBean(result,
					new TypeReference<AccessTokenResultVo>() {
					});
			return accessTokenResultVo;
		} catch (IOException e) {
			throw new RuntimeException("获取微信访问令牌失败");
		}
	}

	/**
	 * 获取访问令牌
	 */
	public static String getAccessToken(String appId, String appSecret) {
		AccessTokenResultVo accessTokenResultVo = WcCommonUtils.getAccessTokenResult(appId, appSecret);
		return accessTokenResultVo.getAccess_token();
	}

	public static void main(String[] args) throws Exception {
		WcCommonUtils.getAccessToken(WcConstants.APP_ID, WcConstants.APP_SECRET);
	}
	
	/**
	 * 从输入流中获取XML
	 */
	public static String getXmlFromRequest(HttpServletRequest request) {
		ServletInputStream sis = null;
		try {
			sis = request.getInputStream();
			byte[] bytes = IOUtils.toByteArray(sis);
			
			String xml = new String(bytes, "UTF-8");
			logger.info("xml : {}", xml);
			return xml;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			org.apache.tomcat.util.http.fileupload.IOUtils.closeQuietly(sis);
		}
	}
	
	@Test
	public void toByteArrayTest() {
		try {
			byte[] bytes = IOUtils.toByteArray(new FileInputStream("d:/wc_workspace/xml.txt"));
			
			String xml = new String(bytes, "UTF-8"); // 在Windows中已将输入流的txt文件另存为UTF-8格式
			logger.info("xml : {}", xml);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

class AccessTokenResultVo {
	private String access_token;
	private Long expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
}