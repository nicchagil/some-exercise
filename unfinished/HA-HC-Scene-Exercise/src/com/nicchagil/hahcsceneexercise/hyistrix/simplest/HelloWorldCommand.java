package com.nicchagil.hahcsceneexercise.hyistrix.simplest;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import rx.Observable;
import rx.functions.Action1;

public class HelloWorldCommand extends HystrixCommand<String> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    public HelloWorldCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("my-group"));
    }

    @Override
    protected String run() {
        return "hello world";
    }
    
    @Test
    public void runByExecute() {
    	this.logger.info(new HelloWorldCommand().execute()); // 同步执行
    	this.logger.info(new HelloWorldCommand().execute());
    	
    	/** Result:
    	2017-08-11 23:12:03.693 [main] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world 
		2017-08-11 23:12:03.694 [main] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world 
    	 */
	}
    
    @Test
    public void runByQueue() {
    	Future<String> future1 = new HelloWorldCommand().queue();
    	Future<String> future2 = new HelloWorldCommand().queue();
    	
    	try {
			this.logger.info(future1.get());
			this.logger.info(future2.get());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    	
    	/** Result:
	  	2017-08-11 23:13:03.040 [main] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world 
		2017-08-11 23:13:03.040 [main] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world 
    	 */
	}
    
    @Test
    public void runByObserve() {
    	Observable<String> observable1 = new HelloWorldCommand().observe();
    	Observable<String> observable2 = new HelloWorldCommand().observe();
    	
    	observable1.subscribe(new Action1<String>() {
			@Override
			public void call(String arg0) {
				logger.info(arg0);
			}
    	});
    	
    	observable2.subscribe(new Action1<String>() {
			@Override
			public void call(String arg0) {
				logger.info(arg0);
			}
    	});
    	
    	/** Result:
    	2017-08-11 23:15:05.529 [hystrix-my-group-2] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world 
		2017-08-11 23:15:05.529 [hystrix-my-group-1] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world 
    	 */
    }
    
    @Test
    public void runByToObservable() {
    	Observable<String> observable1 = new HelloWorldCommand().toObservable();
    	Observable<String> observable2 = new HelloWorldCommand().toObservable();
    	
    	observable1.subscribe(new Action1<String>() {
			@Override
			public void call(String arg0) {
				logger.info(arg0);
			}
    	});
    	
    	observable2.subscribe(new Action1<String>() {
			@Override
			public void call(String arg0) {
				logger.info(arg0);
			}
    	});
    	
    	/** Result:
    	2017-08-11 23:16:21.557 [hystrix-my-group-1] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world 
		2017-08-11 23:16:21.557 [hystrix-my-group-2] INFO  c.n.h.h.simplest.HelloWorldCommand - hello world
    	 */
    }
    
}
