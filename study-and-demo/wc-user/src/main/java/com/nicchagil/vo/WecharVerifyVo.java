package com.nicchagil.vo;

public class WecharVerifyVo {

	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private String token;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "WecharVerifyVo [signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
				+ echostr + ", token=" + token + "]";
	}

}
