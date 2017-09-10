package com.nicchagil.ioexercise.niochatroom.server;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.nicchagil.ioexercise.niochatroom.util.NIOUtil;

public class ChatRoomService {
	
	/**
	 * 发送聊天信息给其它成员
	 */
	public void sendMessageToOtherMember(String from, String message, ConcurrentHashMap<String, Member> members) {
		Set<String> keySet = members.keySet();
		
		/* TODO 效率低，暂时用此方式 */
		for (String s : keySet) {
			if (s.equals(from)) {
				continue;
			}
			
			NIOUtil.writeToChannel(members.get(s).getSocketChannel(), members.get(s).getNickName() + " : " + message);
		}
	}

}
