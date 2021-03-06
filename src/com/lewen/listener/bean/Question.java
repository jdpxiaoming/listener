package com.lewen.listener.bean;

public class Question {

	public static enum LISTENERTYPE{word,picture,video};
	private LISTENERTYPE type;//提醒类型
	private String id;	//题目编号 
	private String question;
	private String answer;//答案、 选项
	private String answerDes;//汉语翻译
	private String selectedA;//A选项
	private String selectedB;
	private String selectedC;
	private String selectedD;//可以是单词 、图片uri
	private String startTime;//开始时间
	private String endTime;	 //结束时间
	
	public String getId() {
		return id;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setId(String id) {
		this.id = id;
	}
	public LISTENERTYPE getType() {
		return type;
	}
	
	public String getAnswerDes() {
		return answerDes;
	}
	public void setAnswerDes(String answerDes) {
		this.answerDes = answerDes;
	}
	public void setType(LISTENERTYPE type) {
		this.type = type;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSelectedA() {
		return selectedA;
	}
	public void setSelectedA(String selectedA) {
		this.selectedA = selectedA;
	}
	public String getSelectedB() {
		return selectedB;
	}
	public void setSelectedB(String selectedB) {
		this.selectedB = selectedB;
	}
	public String getSelectedC() {
		return selectedC;
	}
	public void setSelectedC(String selectedC) {
		this.selectedC = selectedC;
	}
	public String getSelectedD() {
		return selectedD;
	}
	public void setSelectedD(String selectedD) {
		this.selectedD = selectedD;
	}
	
}
