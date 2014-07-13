package com.lewen.listener.bean;

/**
 * 分类题目的简介（我的课件、英语...)
 * @author poe
 *			"id": "79", 
            "image": "http://ting.joysw.cn/Courseware/140710010639983255/20140710010738.png", 
            "title": "驯龙高手", 
            "summary": "驯龙记第二季全名驯龙记博客岛的守卫者"
 */
public class QuestionProp {

	private String id;
	private String icon;
	private String title;
	private String summary;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
