package com.nicchagil.ioexercise.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHelloServer {
	
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(9999)) {
			while (true) {
				Socket socket = serverSocket.accept();
				new HelloBusinessThread(socket).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
