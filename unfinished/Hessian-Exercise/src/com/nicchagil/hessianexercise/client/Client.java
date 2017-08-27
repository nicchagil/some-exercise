package com.nicchagil.hessianexercise.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.nicchagil.hessianexercise.server.UserAPI;

public class Client {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://127.0.0.1:8080/userAPI";

		HessianProxyFactory factory = new HessianProxyFactory();
		UserAPI userAPI = (UserAPI) factory.create(UserAPI.class, url);

		System.out.println("result -> " + userAPI.getUser());
	}

}
