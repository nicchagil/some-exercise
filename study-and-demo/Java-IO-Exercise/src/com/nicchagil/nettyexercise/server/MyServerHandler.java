package com.nicchagil.nettyexercise.server;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
	
	public static AtomicInteger counter = new AtomicInteger();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.logger.info("通道被激活");
	}
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		this.logger.info("第{}次读取信息 -> {}", counter.incrementAndGet(), msg);
    }
	
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	this.logger.info("读取完成");
    	ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer("ok...", CharsetUtil.UTF_8));
    	// channelFuture.addListener(ChannelFutureListener.CLOSE);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
        this.logger.error("出现异常 -> {}", throwable);
        ctx.close();
    }

}
