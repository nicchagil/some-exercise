package com.nicchagil.exercies.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeExercise {
	
	private static Unsafe unsafe = null;
	private static long valueOffsetForUser = 0L;
	
	static {
		Field theUnsafeField = ReflectUtil.getFiled(Unsafe.class, "theUnsafe"); // 获取Unsafe的theUnsafe属性
		theUnsafeField.setAccessible(true); // 设置可访问
		try {
			unsafe = (Unsafe)theUnsafeField.get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		/* 获取User类的value的坐标 */
		try {
			valueOffsetForUser = unsafe.objectFieldOffset
	                (User.class.getDeclaredField("value"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		User user = new User();
		boolean result = unsafe.compareAndSwapInt(user, valueOffsetForUser, 0, 1); // CAS更新
		System.out.println("result : " + result);
		System.out.println("user.getValue() : " + user.getValue());
	}

}
