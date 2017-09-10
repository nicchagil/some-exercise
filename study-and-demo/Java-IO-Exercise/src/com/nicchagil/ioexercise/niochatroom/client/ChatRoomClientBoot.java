package com.nicchagil.ioexercise.niochatroom.client;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.ioexercise.Constant;

public class ChatRoomClientBoot {
	
	private static Logger logger = LoggerFactory.getLogger(ChatRoomClientBoot.class);
	
	public static void main(String[] args) throws IOException {
		/* 启动客户端 */
		ChatRoomClient client = new ChatRoomClient(Constant.SERVER_HOST, Constant.SERVER_PORT);
		new Thread(client).start();
		
		/* 监听键盘输入，按输入值发送消息给服务器 */
		try (Scanner scanner = new Scanner(System.in)) {
			scanner.useDelimiter("\n");
			
			String inputValue = null;
			do {
				System.err.print(client.getSocketChannel().getLocalAddress() + " : ");
				inputValue = scanner.next(); // 等待输入值
				
				if (!inputValue.equalsIgnoreCase("Exit")) { // 如果输入的值不是Exit就继续输入
					client.sendMessage(inputValue);
				} else {
					break;
				}
				
				
			} while (true);
			
			logger.info("程序已退出！");
		}
	}

}
