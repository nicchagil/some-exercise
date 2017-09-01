package com.nicchagil.ioexercise.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.ioexercise.Constant;

public class MyHelloServer {
	
	private static Logger logger = LoggerFactory.getLogger(MyHelloServer.class);
	
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(Constant.SERVER_PORT)) {
			while (true) {
				Socket socket = serverSocket.accept();
				new HelloBusinessThread(socket).start();
			}
		} catch (IOException e) {
			logger.error("输入输出异常", e);
			// throw new RuntimeException(e);
		}
	}

}
