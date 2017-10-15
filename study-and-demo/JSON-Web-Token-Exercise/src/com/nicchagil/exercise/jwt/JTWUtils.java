package com.nicchagil.exercise.jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

public class JTWUtils {
	
	private static String KEY = "XXXXX";
	
	/**
	 * 生成令牌
	 * @param claims
	 * @return
	 */
	public static String generateToken(Claims claims) {
		if (claims == null) {
			throw new IllegalArgumentException("claims为空");
		}
		
		return Jwts.builder()
		  .setClaims(claims)
		  .signWith(SignatureAlgorithm.HS512, KEY)
		  .compact();
	}
	
	/**
	 * 获取认证信息
	 * @param token 令牌 
	 * @return 
	 */
	public static Claims decodeClaims(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
	
	public static void main(String[] args) {
		Claims claims = new DefaultClaims();
		claims.setIssuer("nh"); // 签发者
		claims.setIssuedAt(new Date()); // 签发时间
		claims.setAudience("vk"); // 接收者
		claims.setSubject("Nick Huang"); // 认证内容
		claims.setNotBefore(new Date()); // TOKEN不能在此时间使用，则认为无效
		
		Date expirationDate = new Date();
		expirationDate.setTime(new Date().getTime() + 1000 * 60 * 60 * 2);
		claims.setExpiration(expirationDate); // 过期时间
		
		/* 其它信息 */
		claims.put("ID", "123456");
		claims.put("ROLE", "1,3");
		
		String token = JTWUtils.generateToken(claims);
		System.out.println("token : " + token);
		
		Claims decodedClaims = JTWUtils.decodeClaims(token);
		System.out.println("decodedClaims : " + decodedClaims);
	}

}
