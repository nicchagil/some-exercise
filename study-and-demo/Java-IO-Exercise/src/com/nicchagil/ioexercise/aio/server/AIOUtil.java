package com.nicchagil.ioexercise.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AIOUtil {
	
	private static Logger logger = LoggerFactory.getLogger(AIOUtil.class);
	
	/**
	 * 通过通道发送消息
	 * @param socketChannel 异步套接字通道
	 * @param message 消息
	 */
	public static void write(AsynchronousSocketChannel socketChannel, String message) {
		byte[] bytes = message.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		
		socketChannel.write(byteBuffer, byteBuffer, new WriteHandler(socketChannel)); // 写消息
	}
	
	/**
	 * 关闭通道
	 * @param socketChannel 异步套接字通道
	 */
	public static void close(AsynchronousSocketChannel socketChannel) {
		try {
			socketChannel.close();
		} catch (IOException e) {
			logger.error("关闭套接字通道异常：{}", e);
			throw new RuntimeException(e);
		}
	}

}
