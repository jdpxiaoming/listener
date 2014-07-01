package com.lewen.listener.bean;

public class ResponeLogin {

	private String uid;//用户id
	private String salt;//秘钥
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
