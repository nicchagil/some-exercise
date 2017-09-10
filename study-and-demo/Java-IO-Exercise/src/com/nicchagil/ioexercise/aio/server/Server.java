package com.nicchagil.ioexercise.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

import com.nicchagil.ioexercise.Constant;

public class Server {
	
	public static void main(String[] args) throws Exception {
		AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(); // 服务器套接字通道
		serverSocketChannel.bind(new InetSocketAddress(Constant.SERVER_PORT)); // 监听端口
		
		/*
		 * 接收一个连接，此方法初始一个异步操作来接收一个连接。
		 * 处理器参数是一个完成处理器，当接收到连接或连接失败时被回调完成处理器。
		 * 为了能并发处理新连接，完成处理器并不是被初始线程直接调用。
		 */
		serverSocketChannel.accept(serverSocketChannel, new AcceptHandler()); // 接收一个连接
		
		System.in.read();
	}

}
