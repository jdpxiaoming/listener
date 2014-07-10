package com.lewen.listener.bean;

public class Friend {

	private String uid;//
	private String icon;//
	private String name;//杨子琪
	private String sex;//性别 （0保密 1男 2女）
	private String level;//等级
	private String type;//挑战类型 (1猜单词 2猜图片)
	private String act;//动作：1(应战 2挑战 3查看结果)
	private String warid;//战争id(应战和查看结果)
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getWarid() {
		return warid;
	}
	public void setWarid(String warid) {
		this.warid = warid;
	}
}
