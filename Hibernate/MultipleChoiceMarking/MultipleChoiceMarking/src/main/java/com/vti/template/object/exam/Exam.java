package com.vti.template.object.exam;

import java.util.List;

public class Exam {

	private String name;
	private String code;
	private int totalQuestion;
	private String time;
	private List<Question> questions;

	public Exam(String name, String code, int totalQuestion, String time, List<Question> questions) {
		this.name = name;
		this.code = code;
		this.totalQuestion = totalQuestion;
		this.time = time;
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public String getTime() {
		return time;
	}

	public List<Question> getQuestions() {
		return questions;
	}

}
