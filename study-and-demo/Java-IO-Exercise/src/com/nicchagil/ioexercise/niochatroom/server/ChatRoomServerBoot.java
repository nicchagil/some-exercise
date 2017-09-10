package com.nicchagil.ioexercise.niochatroom.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatRoomServerBoot {
	
	private static Logger logger = LoggerFactory.getLogger(ChatRoomServerBoot.class);
	
	public static void main(String[] args) throws IOException {
		new Thread(new ChatRoomServer()).start();
	}

}
