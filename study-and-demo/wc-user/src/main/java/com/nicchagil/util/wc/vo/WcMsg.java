package com.nicchagil.util.wc.vo;

public class WcMsg {

	/** 开发者微信号 **/
	private String ToUserName;
	
	/** 发送方帐号（一个OpenID） **/
	private String FromUserName;
	
	/** 消息创建时间 （整型） **/
	private Integer CreateTime;
	
	/** 消息类型 **/
	private String MsgType;
	
	/** 文本消息内容 **/
	private String Content;
	
	/** 消息id，64位整型 **/
	private String MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Integer getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Integer createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WcMsg [ToUserName=").append(ToUserName).append(", FromUserName=").append(FromUserName)
				.append(", CreateTime=").append(CreateTime).append(", MsgType=").append(MsgType).append(", Content=")
				.append(Content).append(", MsgId=").append(MsgId).append("]");
		return builder.toString();
	}

}
