package com.nicchagil.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Sha1Utils {

	/**
	 * 摘要
	 */
	public static String encode(String source) {
		String sha1Result = new SimpleHash("SHA-1", source).toString();
		return sha1Result;
	}

	public static void main(String[] args) {
		String s1 = "d9dhff7fkf8f62h48f7gjfg1679418881527002495";
		System.out.println(Sha1Utils.encode(s1));
	}

}
