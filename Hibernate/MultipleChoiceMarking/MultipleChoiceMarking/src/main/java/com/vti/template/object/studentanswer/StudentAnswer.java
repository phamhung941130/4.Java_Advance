package com.vti.template.object.studentanswer;

import java.util.List;

public class StudentAnswer {

	private String questionNumber;
	private List<String> answers;

	public StudentAnswer(String questionNumber, List<String> answers) {
		this.questionNumber = questionNumber;
		this.answers = answers;
	}

	public String getQuestionNumber() {
		return questionNumber;
	}

	public List<String> getAnswers() {
		return answers;
	}
}
