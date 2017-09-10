package com.nicchagil.ioexercise.aio.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取消息处理器
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AsynchronousSocketChannel socketChannel;

	public ReadHandler(AsynchronousSocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	/**
	 * 读取消息
	 */
	@Override
	public void completed(Integer result, ByteBuffer byteBuffer) {
		byteBuffer.flip(); // 翻转缓冲区

		byte[] bytes = new byte[byteBuffer.remaining()];
		byteBuffer.get(bytes);
		try {
			String message = new String(bytes, "UTF-8");
			this.logger.info("接收到消息 -> {}", message);
		} catch (UnsupportedEncodingException e) {
			this.logger.info("接收消息异常：{}", e);
		}
		
		// 向客户端发送消息
		write("hi, client");
	}

	/**
	 * 发送消息
	 */
	private void write(String message) {
		byte[] bytes = message.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		
		socketChannel.write(byteBuffer, byteBuffer, new WriteHandler(socketChannel)); // 写消息
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		this.logger.info("接收消息异常");
		
		try {
			this.socketChannel.close();
		} catch (IOException e) {
			this.logger.error("关闭套接字通道异常：{}", e);
		}
	}
}
