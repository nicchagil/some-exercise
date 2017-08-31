package com.nicchagil.nettyexercise.client;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String host;
	private final int port;

	public MyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup)
					.channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress(this.host, this.port))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new MyClientHandler());
						}
					});

			ChannelFuture cf = bootstrap.connect().sync();
			cf.channel().closeFuture().sync();
		} finally {
			eventLoopGroup.shutdownGracefully().sync();
		}
	}

}