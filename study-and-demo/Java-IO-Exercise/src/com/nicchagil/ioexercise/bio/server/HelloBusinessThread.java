package com.nicchagil.ioexercise.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloBusinessThread extends Thread {
	
	Logger logger = LoggerFactory.getLogger(HelloBusinessThread.class);
	
	Socket socket = null;
	
	public HelloBusinessThread(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String result = bufferedReader.readLine();
			logger.info("接收一个请求：" + result);
			
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.println("hello");
			logger.info("发送一个响应");
			printWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
