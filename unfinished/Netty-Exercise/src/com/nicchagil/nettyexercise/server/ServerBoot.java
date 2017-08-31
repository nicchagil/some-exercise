package com.nicchagil.nettyexercise.server;

import com.nicchagil.nettyexercise.common.Constant;

public class ServerBoot {

	public static void main(String[] args) throws Exception {
        new MyServer(Constant.PORT).start();
    }

}
