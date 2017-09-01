package com.nicchagil.ioexercise.nio.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOUtil {
	
	private static Logger logger = LoggerFactory.getLogger(NIOUtil.class);
	
	/**
	 * 取消/关闭SelectionKey
	 */
	public static void cancelSelectionKey(SelectionKey selectionKey) {
		if (selectionKey == null) {
			return;
		}
		
		selectionKey.cancel(); // 取消SelectionKey
		
		if (selectionKey.channel() != null) {
			try {
				selectionKey.channel().close(); // 关闭通道
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 将信息写入通道
	 */
	public static void writeToChannel(SocketChannel socketChannel, String message) {
		if (message == null || message.trim().length() == 0) {
			return;
		}
		
		byte[] bytes = null;
		try {
			bytes = message.getBytes("UTF-8"); // 转换为字节数组
		} catch (UnsupportedEncodingException e1) {
			throw new RuntimeException(e1);
		}
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length); // 开辟缓冲区
		byteBuffer.put(bytes); // 放入缓冲区
		byteBuffer.flip(); // 切换读取缓冲区模式
		
		try {
			socketChannel.write(byteBuffer); // 写入通道
			// logger.info("发送 -> {}", message);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
