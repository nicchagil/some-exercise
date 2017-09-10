package com.nicchagil.ioexercise.aio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 写消息处理器
 */
public class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private AsynchronousSocketChannel socketChannel;

	public WriteHandler(AsynchronousSocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void completed(Integer result, ByteBuffer buffer) {
		if (buffer.hasRemaining()) {
			socketChannel.write(buffer, buffer, this);
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		this.logger.info("写消息异常");
		
		try {
			socketChannel.close();
		} catch (IOException e) {
			this.logger.error("关闭套接字通道异常：{}", e);
		}
	}

}
