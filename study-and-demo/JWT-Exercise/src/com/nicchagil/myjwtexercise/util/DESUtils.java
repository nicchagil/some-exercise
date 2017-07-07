package com.nicchagil.myjwtexercise.util;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.junit.Test;

public class DESUtils {
	
	/**
	 * 加密
	 * @param data 加密的数据
	 * @param key 密钥
	 * @return 加密后的数据
	 */
	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			DESKeySpec desKeySpec = new DESKeySpec(key);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(data);
			
		} catch (InvalidKeyException e) {
			throw new RuntimeException("无效的Key值");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("无DES算法");
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("无效的KeySpec");
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("无Padding");
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 解密
	 * @param data 解密前的数据
	 * @param key 密钥
	 * @return 解密后的数据
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			DESKeySpec desKeySpec = new DESKeySpec(key);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(data);
			
		} catch (InvalidKeyException e) {
			throw new RuntimeException("无效的Key值");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("无DES算法");
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("无效的KeySpec");
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("无Padding");
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void encryptTest() {
		System.out.println(Base64.getEncoder().encodeToString(DESUtils.encrypt("hello".getBytes(), "12345678".getBytes())));
	}
	
	@Test
	public void decryptTest() {
		System.out.println(new String(DESUtils.decrypt(Base64.getDecoder().decode("uhbGoCVxJa8="), "12345678".getBytes())));
	}
	
}
