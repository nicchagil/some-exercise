package com.nicchagil.myjwtexercise.util;
import java.util.Base64;

import org.junit.Test;

public class Base64Utils {
	
	public static String encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static byte[] decode(String s) {
		return Base64.getDecoder().decode(s);
	}
	
	@Test
	public void encodeTest() {
		System.out.println(Base64Utils.encode("hello".getBytes()));
	}
	
	@Test
	public void decodeTest() {
		System.out.println(new String(Base64Utils.decode("aGVsbG8=")));
	}

}
