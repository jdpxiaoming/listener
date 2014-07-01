package com.lewen.listener.bean;

public class AuthReply {

	/**
	 * 开放id
	 */
	private String openID;
	private String source;//qq , weibo
	private String expire_time;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getExpire_time() {
		return expire_time;
	}
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
}
