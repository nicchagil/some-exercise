package com.nicchagil.ioexercise.nio.server;

import java.nio.channels.SocketChannel;

/**
 * 连接成员
 */
public class Member {

	private String nickName; // 花名。TODO 当前值为IP:PORT，预想为首次连接分配的昵称
	private SocketChannel socketChannel; // 通道

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

	public void setSocketChannel(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	public Member(String nickName, SocketChannel socketChannel) {
		super();
		this.nickName = nickName;
		this.socketChannel = socketChannel;
	}

}
