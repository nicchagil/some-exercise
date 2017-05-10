package com.nicchagil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NotifyConsumer {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 时间格式器
	public static Integer NOTIFY_MIN = 1; // 延迟几分钟触发通知

	public static void consumer(String dateStr) {
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		date.setTime(date.getTime() + NOTIFY_MIN * 1000 * 60);
		// System.out.println("norify time : " + sdf.format(date));
		// System.out.println("now    time : " + sdf.format(new Date()));
		
		while (new Date().getTime() < date.getTime()) { // 还没到时间
			long sleepTime = date.getTime() - new Date().getTime();
			System.out.println("sleep milliseconds : " + sleepTime); // 时间还没到，睡会儿
			
			if (sleepTime > 0) {
				try {
					TimeUnit.MILLISECONDS.sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		/* 模拟调用通知所需时间 */
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("notify at " + sdf.format(new Date()) + " for the msg : " + dateStr);

	}
	
}
