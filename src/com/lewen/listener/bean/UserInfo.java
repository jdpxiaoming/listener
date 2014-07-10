package com.lewen.listener.bean;

public class UserInfo {

	private String id;//
	private String user_name;
	private String user_level;//听霸的等级
	private String user_tb;//听币
	private String user_red;//红宝石
	private String user_rmb;//人民币
	
	//other
	private String replay_time;//重播卡
	private String reverse_time;//复读卡
	private String user_speed;//速度还真不如阁下号
	private String tips;//提示
	
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_level() {
		return user_level;
	}
	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
	public String getUser_tb() {
		return user_tb;
	}
	public void setUser_tb(String user_tb) {
		this.user_tb = user_tb;
	}
	public String getUser_red() {
		return user_red;
	}
	public void setUser_red(String user_red) {
		this.user_red = user_red;
	}
	public String getUser_rmb() {
		return user_rmb;
	}
	public void setUser_rmb(String user_rmb) {
		this.user_rmb = user_rmb;
	}
	public String getReplay_time() {
		return replay_time;
	}
	public void setReplay_time(String replay_time) {
		this.replay_time = replay_time;
	}
	public String getReverse_time() {
		return reverse_time;
	}
	public void setReverse_time(String reverse_time) {
		this.reverse_time = reverse_time;
	}
	public String getUser_speed() {
		return user_speed;
	}
	public void setUser_speed(String user_speed) {
		this.user_speed = user_speed;
	}
}
