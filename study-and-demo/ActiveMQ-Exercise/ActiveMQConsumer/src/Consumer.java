import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class Consumer {
	
	private static final Logger LOG = Logger.getLogger(Consumer.class);
	
	// 是否继续响应，可按需由其他逻辑修改值，true：继续响应，false-停止响应
	public static volatile boolean handleFlag = true;

	public static void main(String[] args) {
		// 获取连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.1.101:61616");
		
		/* 获取连接 */
        Connection connection = null;
		try {
			connection = factory.createConnection();
			connection.start();
		} catch (JMSException e) {
			LOG.error("获取连接出现异常", e);
		}
		
		/* 创建会话 */
        Session session = null;
		try {
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			LOG.error("创建会话出现异常", e);
		}
		
		/* 创建消息生产者 */
        Destination destination = null;
		try {
			destination = session.createQueue("TestQueue");
		} catch (JMSException e) {
			LOG.error("创建队列出现异常", e);
		}
        
		/* 创建消费者 */
        MessageConsumer consumer = null;
		try {
			consumer = session.createConsumer(destination);
		} catch (JMSException e) {
			LOG.error("创建消费者出现异常", e);
		}
        
		/* 获取消息对象 */
        ObjectMessage objectMessage = null;
        while(handleFlag) {
        	try {
    			objectMessage = (ObjectMessage)consumer.receive();
    			handleMessage(objectMessage);
    		} catch (JMSException e) {
    			LOG.error("接收消息出现异常", e);
    		}
        }
        
        if (connection != null) {
        	try {
				connection.close();
			} catch (JMSException e) {
				LOG.error("关闭连接出现异常", e);
			}
        }
        
	}
	
	/**
	 * 处理消息对应的业务
	 * @param objectMessage 消息对象
	 */
	public static void handleMessage(final ObjectMessage objectMessage) {
		if (objectMessage == null) {
			return;
		}
		
		/* 处理业务 */
		Object object = null;
		try {
			object = objectMessage.getObject();
		} catch (JMSException e) {
			LOG.error("获取消息内容出现异常", e);
		}
		handleMessage(object);
	}
	
	/**
	 * 处理消息对应的业务
	 * @param messageString 消息内容
	 */
	public static void handleMessage(Object object) {
		if (object == null) {
			return;
		}
		
		String messageString = (String)object;
		LOG.info("Receive : " + messageString); // 这里仅作打印业务而已
	}

}
