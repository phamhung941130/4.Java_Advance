package com.vti.template.object.studentanswer;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private String fullName;
	private List<StudentAnswer> answers;

	private int correctQuestionAmount;
	private List<Integer> correctQuestions;
	private List<Integer> wrongQuestions;

	public Student(String fullName, List<StudentAnswer> answers) {
		this.fullName = fullName;
		this.answers = answers;

		correctQuestionAmount = 0;
		correctQuestions = new ArrayList<>();
		wrongQuestions = new ArrayList<>();
	}

	public String getFullName() {
		return fullName;
	}

	public List<StudentAnswer> getAnswers() {
		return answers;
	}

	public void increaseCorrectQuestionAmount() {
		correctQuestionAmount++;
	}

	public int getCorrectQuestionAmount() {
		return correctQuestionAmount;
	}

	public void addCorrectQuestion(int question) {
		correctQuestions.add(question);
	}

	public void addWrongQuestion(int question) {
		wrongQuestions.add(question);
	}

	public List<Integer> getCorrectQuestions() {
		return correctQuestions;
	}

	public List<Integer> getWrongQuestions() {
		return wrongQuestions;
	}

}
