package com.nicchagil.ioexercise.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class MyHelloClient {
	
	private static Logger logger = Logger.getLogger("MyHelloClient");
	
	public static void main(String[] args) {
		try (Socket socket = new Socket("127.0.0.1", 9999);) {
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
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
