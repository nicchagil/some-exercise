package com.nicchagil.nettyexercise.client;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.nettyexercise.common.ByteBufUtil;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends ChannelInboundHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.logger.info("通道被激活");
		ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Netty", CharsetUtil.UTF_8));
	}
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		byte[] bytes = ByteBufUtil.getBytesFromByteBuf(msg);
		this.logger.info("读取信息 -> {}", new String(bytes));
    }
	
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	this.logger.info("读取完成");
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
        this.logger.error("出现异常 -> {}", throwable);
        ctx.close();
    }

}