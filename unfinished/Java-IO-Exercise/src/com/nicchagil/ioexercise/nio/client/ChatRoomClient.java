package com.nicchagil.ioexercise.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.ioexercise.nio.util.NIOUtil;

public class ChatRoomClient implements Runnable {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String serverHost;
	private Integer serverPort;

	private Selector selector; // 多路复用器
	private SocketChannel socketChannel; // 通道
	
	public ChatRoomClient(String serverHost, Integer serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		
		try {
			this.selector = Selector.open(); // 创建多路复用器
			
			/* 配置通道 */
			this.socketChannel = SocketChannel.open();
			this.socketChannel.configureBlocking(false); // 非阻塞
		} catch (IOException e) {
			this.logger.error("输入输出异常", e);
			// throw new RuntimeException(e);
		}
		
	}

	@Override
	public void run() {
		/* 连接服务器 */
		try {
			this.connect();
		} catch (IOException e) {
			this.logger.error("连接出现异常", e);
			return;
		}
		
		Set<SelectionKey> selectionKeys = null;
		Iterator<SelectionKey> iterator = null;
		SelectionKey selectionKey = null;
		
		while (true) {
			try {
				// this.logger.info("polling...");
				this.selector.select(); // 阻塞轮询，当轮询通道中有IO就绪时，返回
				selectionKeys = this.selector.selectedKeys(); // 获取就绪通道的SelectionKey
				// this.logger.info("当前就绪的通道数 -> {}", selectionKeys.size());
				
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
		boolean connect = this.socketChannel.connect(new InetSocketAddress(this.serverHost, this.serverPort));
		
		if (connect) { // 连接成功
			socketChannel.register(selector, SelectionKey.OP_READ);
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
			this.read(selectionKey);
		}
	}
	
	/**
	 * 连接
	 */
	private void connect(SelectionKey selectionKey) throws IOException {
		// this.logger.info("连接事件");
		if (socketChannel.finishConnect()) { // 完成连接
			socketChannel.register(selector, SelectionKey.OP_READ);
		}
	}
	
	/**
	 * 读取信息
	 */
	private void read(SelectionKey selectionKey) throws IOException {
		// this.logger.info("读取事件");
		ByteBuffer byteBuffer = ByteBuffer.allocate(64); // 开辟缓冲区
		int readByteNum = socketChannel.read(byteBuffer); // 读取数据
		
		if (readByteNum > 0) { // 有数据
			byteBuffer.flip();
			byte[] bytes = new byte[byteBuffer.remaining()];
			byteBuffer.get(bytes);
			
			String message = new String(bytes, "UTF-8");
			this.logger.info(message);
		} else if (readByteNum == 0) { // 无数据
			// TODO
		} else { // readByteNum < 0，表示连接已关闭
			NIOUtil.cancelSelectionKey(selectionKey);
			socketChannel.close();
		}
	}
	
	/**
	 * 发送消息
	 */
	public void sendMessage(String message) throws ClosedChannelException {
		if (socketChannel == null) {
			this.logger.info("客户端还未连接服务器，或连接服务器异常");
		}
		
		NIOUtil.writeToChannel(socketChannel, message);
	}

	/**
	 * 获取当前通道
	 */
	public SocketChannel getSocketChannel() {
		return socketChannel;
	}
	
}
