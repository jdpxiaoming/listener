package com.lewen.listener.bean;

/**
 * 一条排队信息
 * @author poe
 *
 */
public class Queue {

	private String img;
	private String name;
	private float score;
	
	
	public Queue(String img, String name, float score) {
		super();
		this.img = img;
		this.name = name;
		this.score = score;
	}
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public float getScore() {
		return score;
	}


	public void setScore(float score) {
		this.score = score;
	}
	
}
