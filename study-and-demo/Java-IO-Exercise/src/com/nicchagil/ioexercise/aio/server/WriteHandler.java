package com.nicchagil.ioexercise.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送消息处理器
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
		this.logger.info("发送消息异常");
		
		AIOUtil.close(socketChannel);
	}

}
