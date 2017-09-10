package com.nicchagil.ioexercise.niochatroom.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.ioexercise.Constant;
import com.nicchagil.ioexercise.niochatroom.util.NIOUtil;

public class ChatRoomServer implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Selector selector; // 多路复用器
	private ServerSocketChannel serverSocketChannel; // 通道
	private ConcurrentHashMap<String, Member> members = new ConcurrentHashMap<String, Member>(); // 当前连接的成员
	
	private ChatRoomService chatRoomService = new ChatRoomService(); // 聊天室Service
	
	public ChatRoomServer() {
		try {
			this.selector = Selector.open(); // 创建多路复用器
			
			/* 配置通道 */
			this.serverSocketChannel = ServerSocketChannel.open();
			this.serverSocketChannel.configureBlocking(false); // 非阻塞
			this.serverSocketChannel.socket().bind(new InetSocketAddress(Constant.SERVER_HOST, Constant.SERVER_PORT), 512); // 监听的主机、端口，和挂起的最大连接数
			this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 通道绑定多路复用器，并监听“连接”事件
		} catch (IOException e) {
			this.logger.error("配置服务器连接出现异常", e);
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void run() {
		Set<SelectionKey> selectionKeys = null;
		Iterator<SelectionKey> iterator = null;
		SelectionKey selectionKey = null;
				
		while (true) {
			try {
				this.logger.info("polling...");
				this.selector.select(); // 阻塞轮询，当轮询通道中有IO就绪时，返回
				selectionKeys = this.selector.selectedKeys(); // 获取就绪通道的SelectionKey
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
			this.read(selectionKey);
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
		socketChannel.register(this.selector, SelectionKey.OP_READ); // 通道绑定多路复用器，并监听“读取”事件
		
		/* 记录连接的成员 */
		SocketAddress socketAddress = socketChannel.getRemoteAddress();
		members.put(socketAddress.toString(), new Member(socketAddress.toString(), socketChannel));
	}
	
	/**
	 * 读取信息
	 */
	private void read(SelectionKey selectionKey) throws IOException {
		this.logger.info("开始读取事件");
		SocketChannel socketChannel = (SocketChannel)selectionKey.channel(); // 转换为连接Socket
		ByteBuffer byteBuffer = ByteBuffer.allocate(64); // 开辟缓冲区
		int readByteNum = socketChannel.read(byteBuffer); // 读取数据
		
		if (readByteNum > 0) { // 有数据
			byteBuffer.flip();
			byte[] bytes = new byte[byteBuffer.remaining()];
			byteBuffer.get(bytes);
			
			String message = new String(bytes, "UTF-8");
			this.logger.info("接收到消息：" + message);
			
			this.chatRoomService.sendMessageToOtherMember(socketChannel.getRemoteAddress().toString(), message, members); // 发送聊天信息给其它成员
		} else if (readByteNum == 0) { // 无数据
			// TODO
		} else { // readByteNum < 0，表示连接已关闭
			NIOUtil.cancelSelectionKey(selectionKey);
			socketChannel.close();
		}
	}

}
