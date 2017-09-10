package com.nicchagil.ioexercise.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.ioexercise.Constant;
import com.nicchagil.ioexercise.nio.util.NIOUtil;

public class NIOServer {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	
	public NIOServer() throws Exception {
		selector = Selector.open(); // 多路复用器
		
		/* 配置通道 */
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false); // 非阻塞
		serverSocketChannel.socket().bind(new InetSocketAddress(Constant.SERVER_HOST, Constant.SERVER_PORT), 512); // 监听的主机、端口，和挂起的最大连接数
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 通道绑定多路复用器，并监听“连接”事件
		
		Set<SelectionKey> selectionKeys = null;
		Iterator<SelectionKey> iterator = null;
		SelectionKey selectionKey = null;
				
		while (true) {
			try {
				this.logger.info("polling...");
				selector.select(); // 阻塞轮询，当轮询通道中有IO就绪时，返回
				selectionKeys = selector.selectedKeys(); // 获取就绪通道的SelectionKey
				this.logger.info("当前就绪的通道数 -> {}", selectionKeys.size());
				
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
	 * 处理通道业务
	 */
	public void handle(SelectionKey selectionKey) throws IOException {
		if (!selectionKey.isValid()) { // 无效快速失败
			this.logger.info("连接无效");
			return;
		}
		
		/* 连接事件 */
		if (selectionKey.isAcceptable()) {
			this.accept(selectionKey);
		}
		/* 读取事件 */
		else if (selectionKey.isReadable()) {
			SocketChannel socketChannel = (SocketChannel)selectionKey.channel(); // 转换为连接Socket
			String message = NIOUtil.readToString(socketChannel, selectionKey);
			this.logger.info("message -> {}", message);
			
			if (message != null) {
				NIOUtil.writeToChannel(socketChannel, "hi, client");
			}
		}
	}
	
	/**
	 * 接受连接
	 */
	private void accept(SelectionKey selectionKey) throws IOException {
		this.logger.info("开始连接事件");
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel(); // 转换为服务器Socket
		
		SocketChannel socketChannel = serverSocketChannel.accept(); // 接收一个连接
		socketChannel.configureBlocking(false); // 非阻塞
		
		socketChannel.register(selector, SelectionKey.OP_READ); // 通道绑定多路复用器，并监听“读取”事件
	}
	
	public static void main(String[] args) throws Exception {
		new NIOServer();
	}
	
}
