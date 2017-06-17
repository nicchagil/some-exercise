package com.nicchagil.myjwtexercise.util;

import java.util.Arrays;

import com.nicchagil.myjwtexercise.entity.Header;
import com.nicchagil.myjwtexercise.entity.Payload;

public class MyJWTUtils {
	
	private static final String DES_KEY = "12345678"; // 对称加密密钥
	
	/**
	 * 生成令牌
	 * @param header 头部对象
	 * @param payload 负载对象
	 * @return 令牌
	 */
	public static String generateToken(Header header, Payload payload) {
		/* 转换JSON */
		String headerJSON = JSONUtils.toJSON(header);
		String payloadJSON = JSONUtils.toJSON(payload);
		System.out.println("headerJSON -> " + headerJSON);
		System.out.println("payloadJSON -> " + payloadJSON);
		
		/* 转为Base64 */
		/*
		String headerBase64 = Base64Utils.encode(headerJSON.getBytes());
		String payloadBase64 = Base64Utils.encode(payloadJSON.getBytes());
		*/
		
		/* 加密并转为Base64 */
		String token = Base64Utils.encode(DESUtils.encrypt(headerJSON.getBytes(), DES_KEY.getBytes())) + "." + Base64Utils.encode(DESUtils.encrypt(payloadJSON.getBytes(), DES_KEY.getBytes()));
		System.out.println("token -> " + token);
		
		/* 加上签名 */
		/*
		String verifyBase64 = Base64Utils.encode((headerJSON + "." + payloadJSON).getBytes());
		*/
		token = token + "." + Base64Utils.encode(DESUtils.encrypt((headerJSON + "." + payloadJSON).getBytes(), DES_KEY.getBytes()));
		System.out.println("token -> " + token);
		return token;
	}
	
	/**
	 * 验证令牌的签名是否正确
	 * @param token 令牌
	 * @return 签名是否正确
	 */
	public static boolean verifySignature(String token) {
		/* 验证Token是否为空 */
		if (token == null || token.trim().length() == 0) {
			return false;
		}
		token = token.trim(); // 去空格
		
		/* 验证Token格式 */
		String[] tokenArray = token.split("\\.");
		if (tokenArray == null || tokenArray.length != 3) {
			return false;
		}
		for (String s : tokenArray) {
			if (s == null || s.length() == 0) {
				return false;
			}
		}
		
		String header = tokenArray[0];
		String payload = tokenArray[1];
		String signature = tokenArray[2];
		
		String headerJSON = new String(DESUtils.decrypt(Base64Utils.decode(header), DES_KEY.getBytes()));
		String payloadJSON = new String(DESUtils.decrypt(Base64Utils.decode(payload), DES_KEY.getBytes()));
		
		String newSignature = Base64Utils.encode(DESUtils.encrypt((headerJSON + "." + payloadJSON).getBytes(), DES_KEY.getBytes()));
		
		if (signature != null && newSignature != null && signature.equals(newSignature)) {
			return true;
		}
		
		return false;
	}
	
}
