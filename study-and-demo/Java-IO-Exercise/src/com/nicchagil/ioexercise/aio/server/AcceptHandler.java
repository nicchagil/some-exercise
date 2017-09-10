package com.nicchagil.ioexercise.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收连接处理器
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void completed(AsynchronousSocketChannel socketChannel, AsynchronousServerSocketChannel serverSocketChannel) {
		serverSocketChannel.accept(serverSocketChannel, this); // 服务器Socket继续接收请求
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		
		/*
		 * 从通道中读取字节到缓冲区，此方法初始一个异步读取操作，从通道中读取字节到缓冲区。
		 * 处理器参数是一个完成处理器，读取完成或失败时被调用。
		 * 读取的字节数会传递给处理器，如没有可读取的字节，则传递-1。
		 */
		socketChannel.read(byteBuffer, byteBuffer, new ReadHandler(socketChannel)); // 读取消息
	}

	@Override
	public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
		this.logger.error("接收连接异常：{}", exc);
	}
	
}