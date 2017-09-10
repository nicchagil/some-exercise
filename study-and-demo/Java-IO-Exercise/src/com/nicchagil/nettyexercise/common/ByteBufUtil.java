package com.nicchagil.nettyexercise.common;

import io.netty.buffer.ByteBuf;

public class ByteBufUtil {
	
	public static byte[] getBytesFromByteBuf(Object msg) {
		ByteBuf buf = (ByteBuf) msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		
		return bytes;
	}

}
