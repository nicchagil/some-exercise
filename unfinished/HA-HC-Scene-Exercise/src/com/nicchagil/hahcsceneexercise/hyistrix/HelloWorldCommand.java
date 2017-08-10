package com.nicchagil.hahcsceneexercise.hyistrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldCommand extends HystrixCommand<String> {

    public HelloWorldCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("my-group"));
    }

    @Override
    protected String run() {
        return "hello world";
    }
    
    public static void main(String[] args) {
		new HelloWorldCommand().execute();
	}
    
}
