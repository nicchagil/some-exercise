package com.nicchagil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HowToUse {
	
	public static Random RANDOM = new Random(); // 随机数类
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式类

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-activemq.xml"});
        context.start();

        ProducerService producerService = (ProducerService)context.getBean("producerService");
        ActiveMQQueue activeMQQueue = (ActiveMQQueue)context.getBean("testQueue");
        
        /* 随机发送内容为“yyyy-MM-dd HH:mm:ss”时间格式的消息 */
        Calendar c = Calendar.getInstance();
        int counter = 0; // 消息数统计器
        for (int i = 0; i < 10000; i++) {
        	int random = random();
        	c.add(Calendar.SECOND, random); // 时间递增随机值
        	
        	System.out.println(sdf.format(c.getTime()));
        	producerService.sendMessage(activeMQQueue, sdf.format(c.getTime()));
        	counter++;
        }
        
        System.out.println("sent counter : " + counter);
        
	}
	
	/**
	 * 获取0-5的随机数
	 * @return 0-5的随机数
	 */
	public static int random() {
		return RANDOM.nextInt(5);
	}

}
