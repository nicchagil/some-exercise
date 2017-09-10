package com.nicchagil.ioexercise.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.ioexercise.Constant;

public class MyHelloClient {
	
	private static Logger logger = LoggerFactory.getLogger(MyHelloClient.class);
	
	public static void main(String[] args) throws IOException {
		try (Socket socket = new Socket(Constant.SERVER_HOST, Constant.SERVER_PORT);) {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.println("hi");
			logger.info("发送一个请求");
			printWriter.flush();
			
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String result = bufferedReader.readLine();
			logger.info("收到一个答复：" + result);
			
		} catch (UnknownHostException e) {
			logger.error("无法找到此主机", e);
			// throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("输入输出异常", e);
			// throw new RuntimeException(e);
		}
		
		System.in.read();
	}

}
