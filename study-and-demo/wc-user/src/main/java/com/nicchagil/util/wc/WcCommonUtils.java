package com.nicchagil.util.wc;

import com.nicchagil.constant.WcConstants;
import com.nicchagil.util.http.HttpRequestor;

public class WcCommonUtils {
	
	/**
	 * 获取访问令牌
	 */
	public static String getAccessToken(String appId, String appSecret) throws Exception {
		String result = new HttpRequestor().doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) throws Exception {
		WcCommonUtils.getAccessToken(WcConstants.APP_ID, WcConstants.APP_SECRET);
	}

}
