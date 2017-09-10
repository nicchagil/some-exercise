package com.nicchagil.ioexercise.nio.test;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteBufferTest {
	
	private static Logger logger = LoggerFactory.getLogger(ByteBufferTest.class);

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(20);
		ByteBufferTest.printByteBuffer("初始时", bb);
		
		for (int i = 0; i < 10; i++) {
			bb.put((byte)i);
		}
		ByteBufferTest.printByteBuffer("写入10字节后", bb);
		
		bb.flip();
		ByteBufferTest.printByteBuffer("翻转后", bb);
		
		for (int i = 0; i < 3; i++) {
			bb.get();
		}
		ByteBufferTest.printByteBuffer("读取3字节后", bb);
	}
	
	private static void printByteBuffer(String prefix, ByteBuffer bb) {
		logger.info("{} : position -> {}, limit -> {}, capacity -> {}", prefix, bb.position(), bb.limit(), bb.capacity());
	}

}
