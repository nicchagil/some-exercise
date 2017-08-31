package com.nicchagil.nettyexercise.client;

import com.nicchagil.nettyexercise.common.Constant;

public class ClientBoot {
	
	public static void main(String[] args) throws Exception {
		new MyClient(Constant.HOST, Constant.PORT).start();
		System.in.read();
	}

}
