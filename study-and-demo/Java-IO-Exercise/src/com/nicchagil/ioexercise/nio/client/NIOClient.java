package com.nicchagil.ioexercise.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.ioexercise.Constant;
import com.nicchagil.ioexercise.nio.util.NIOUtil;

public class NIOClient {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Selector selector; // 多路复用器
	private SocketChannel socketChannel; // 通道
	
	public NIOClient() throws IOException {
		this.selector = Selector.open(); // 多路复用器
		
		/* 配置通道 */
		this.socketChannel = SocketChannel.open();
		this.socketChannel.configureBlocking(false); // 非阻塞
		
		/* 连接服务器 */
		this.connect();
		
		Set<SelectionKey> selectionKeys = null;
		Iterator<SelectionKey> iterator = null;
		SelectionKey selectionKey = null;
		
		while (true) {
			try {
				this.selector.select(); // 阻塞轮询，当轮询通道中有IO就绪时，返回
				selectionKeys = this.selector.selectedKeys(); // 获取就绪通道的SelectionKey
				
				iterator = selectionKeys.iterator();
				
				while (iterator.hasNext()) {
					selectionKey = iterator.next();
					iterator.remove();
					
					try {
						this.handle(selectionKey); // 处理该通道业务
					} catch (IOException e) {
						this.logger.error("通道{}出现异常：{}", selectionKey, e);
						NIOUtil.cancelSelectionKey(selectionKey);
					}
				}
				
			} catch (IOException e) {
				this.logger.error("多路复用监听出现异常", e);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 连接服务器
	 */
	public void connect() throws IOException {
		boolean connect = this.socketChannel.connect(new InetSocketAddress(Constant.SERVER_HOST, Constant.SERVER_PORT));
		
		if (connect) { // 连接成功
			socketChannel.register(selector, SelectionKey.OP_READ);
			NIOUtil.writeToChannel(socketChannel, "hi, server, abcdefghijklmnopqrstuvwxyz");
		} else { // 连接失败
			this.socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}
	
	/**
	 * 处理通道业务
	 */
	public void handle(SelectionKey selectionKey) throws IOException {
		if (!selectionKey.isValid()) { // 无效快速失败
			// this.logger.info("连接无效");
			return;
		}
		
		/* 连接事件 */
		if (selectionKey.isConnectable()) {
			this.connect(selectionKey);
		}
		/* 读取事件 */
		else if (selectionKey.isReadable()) {
			SocketChannel socketChannel = (SocketChannel)selectionKey.channel(); // 转换为连接Socket
			String message = NIOUtil.readToString(socketChannel, selectionKey);
			this.logger.info("message -> {}", message);
		}
	}
	
	/**
	 * 连接
	 */
	private void connect(SelectionKey selectionKey) throws IOException {
		if (socketChannel.finishConnect()) { // 完成连接
			socketChannel.register(selector, SelectionKey.OP_READ);
			NIOUtil.writeToChannel(socketChannel, "hi, server, abcdefghijklmnopqrstuvwxyz");
		}
	}
	
	public static void main(String[] args) throws Exception {
		new NIOClient();
	}
	
}
