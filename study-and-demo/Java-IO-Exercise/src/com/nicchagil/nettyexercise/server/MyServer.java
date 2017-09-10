package com.nicchagil.nettyexercise.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.nettyexercise.common.Constant;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class MyServer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Integer port;

	public MyServer(Integer port) {
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup(); // 事件循环群组
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap(); // 启动类

			serverBootstrap.group(eventLoopGroup) // 指定事件循环群组
					.channel(NioServerSocketChannel.class) // 指定通道类型
					.localAddress(this.port) // 指定监听端口
					.childHandler(new ChannelInitializer<SocketChannel>() { // 指定通道初始化器
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline channelPipeline = ch.pipeline();
							
							/* 分隔符方式分包 */
							ByteBuf delimiterByteBuf = Unpooled.copiedBuffer(Constant.DELIMITER.getBytes());
							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiterByteBuf)); // 指定单条消息最大长度和分隔符
							ch.pipeline().addLast(new StringDecoder());

							channelPipeline.addLast(new MyServerHandler()); // 指定数据入站处理器
						}
					});

			ChannelFuture cf = serverBootstrap.bind().sync(); // 服务器同步绑定
			cf.channel().closeFuture().sync(); // 关闭服务器通道
		} finally {
			eventLoopGroup.shutdownGracefully().sync(); // 释放线程池资源
		}
	}

	public static void main(String[] args) throws Exception {
		new MyServer(Constant.PORT).start();
	}

}
