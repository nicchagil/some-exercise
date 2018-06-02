package com.nicchagil.util.wc.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicchagil.util.wc.MsgType;
import com.nicchagil.util.wc.WcCommonUtils;
import com.nicchagil.util.wc.vo.WcMsg;
import com.nicchagil.util.xml.XmlUtils;
import com.thoughtworks.xstream.XStream;

@Service
public class WechatMsgService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 处理微信发送的信息
	 */
	public String receiveAndHandleMsg(HttpServletRequest request) {
		/* 接收到消息 */
		String xml = WcCommonUtils.getXmlFromRequest(request);
		this.logger.info("receive xml : {}", xml);
		
		WcMsg receivedMsg = this.xmlToWcMsg(xml);
		this.logger.info("receivedMsg : {}", receivedMsg);
		
		/* 响应消息 */
		WcMsg sendMsg = new WcMsg();
		sendMsg.setFromUserName(receivedMsg.getToUserName());
		sendMsg.setToUserName(receivedMsg.getFromUserName());
		sendMsg.setCreateTime(new Date().getTime());
		sendMsg.setMsgType(MsgType.TEXT.getCode());
		sendMsg.setContent("你的消息已收到");
		
		XStream xstream = new XStream();
		xstream.alias("xml", WcMsg.class);
		
		String responseXml = xstream.toXML(sendMsg);
		this.logger.info("response xml : {}", responseXml);
		return responseXml;
	}
	
	/**
	 * 从请求中获取的XML转换为微信发送的消息对象
	 */
	public WcMsg xmlToWcMsg(String xml) {
		if (xml == null || xml.trim().length() == 0) {
			return null;
		}
		
		XStream xstream = new XStream();
		xstream.alias("xml", WcMsg.class);
		
		WcMsg wcMsg = XmlUtils.toBean(xstream, WcMsg.class, xml);
		return wcMsg;
	}

}
