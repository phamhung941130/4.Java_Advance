package com.vti.template.object.exam;

import java.util.List;

public class Question {

	private int questionNumber;
	private String levelQuestion;
	private List<Answer> answers;

	public Question(int questionNumber, String levelQuestion, List<Answer> answers) {
		this.questionNumber = questionNumber;
		this.levelQuestion = levelQuestion;
		this.answers = answers;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public String getLevelQuestion() {
		return levelQuestion;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

}
