package com.vti.template;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.vti.template.object.exam.Answer;
import com.vti.template.object.exam.Exam;
import com.vti.template.object.exam.Question;
import com.vti.template.object.studentanswer.Student;
import com.vti.template.object.studentanswer.StudentAnswer;
import com.vti.template.utils.FileManager;
import com.vti.template.utils.PropertyConfig;

public class Function {

	private FileManager fileManager;
	private PropertyConfig config;
	private Exam exam;
	private List<Student> students;

	public Function() {
		fileManager = new FileManager();
		config = new PropertyConfig();
		exam = loadAnswerFile();
		students = loadListAnswerSheet(config.getAnswerStudentsFolderPath(), exam);
	}

	public void mark() {
		for (Student student : students) {
			markAStudent(student);
		}
	}

	public void printStudentPoint() {
		for (Student student : students) {

			String correctQuestion = Joiner.on(',').join(student.getCorrectQuestions());
			String wrongQuestion = Joiner.on(',').join(student.getWrongQuestions());

			System.out.println("Tên: " + student.getFullName());
			System.out.println("Correct Question Amount: " + student.getCorrectQuestionAmount());
			System.out.println("Correct Question: " + correctQuestion);
			System.out.println("Wrong Question: " + wrongQuestion);

			System.out.println("\n\n-----------------------------------------------------------------");

		}
	}

	public void printOutPut() {
		cloneStudentAnswersExcelFile();
		writeMarkResult();
	}

	public void writeMarkResult() {
		// get all excel file
		List<String> excelFileNames = fileManager.getExcelFiles(config.getOutputGradedFolderPath());

		for (int i = 0; i < excelFileNames.size(); i++) {
			Student student = students.get(i);
			String filePath = config.getOutputGradedFolderPath() + "\\" + excelFileNames.get(i);
			fileManager.writeMarkResult(student, filePath, exam);
		}

	}

	private void cloneStudentAnswersExcelFile() {

		// get all excel file
		List<String> excelFileNames = fileManager.getExcelFiles(config.getAnswerStudentsFolderPath());

		for (String fileName : excelFileNames) {
			fileManager.copyFile(config.getAnswerStudentsFolderPath() + "//" + fileName,
					config.getOutputGradedFolderPath());
		}
	}

	private Exam loadAnswerFile() {
		return fileManager.parseAnswerFile(config.getAnswerFilePath());
	}

	private List<Student> loadListAnswerSheet(String rootFolder, Exam exam) {

		List<Student> students = new ArrayList<>();

		// get all excel file
		List<String> excelFileNames = fileManager.getExcelFiles(rootFolder);

		for (String nameFile : excelFileNames) {
			students.add(fileManager.parseStudentAnswerFile(rootFolder + "//" + nameFile, exam));
		}

		return students;
	}

	private void markAStudent(Student student) {
		for (int i = 0; i < exam.getTotalQuestion(); i++) {

			boolean result = markASAnswer(student.getAnswers().get(i), exam.getQuestions().get(i));

			if (result) {
				student.increaseCorrectQuestionAmount();
				student.addCorrectQuestion(i + 1);

			} else {
				student.addWrongQuestion(i + 1);
			}
		}
	}

	private boolean markASAnswer(StudentAnswer studentAnswer, Question question) {

		String studentAnswerStr = String.join(",", studentAnswer.getAnswers());

		for (Answer answer : question.getAnswers()) {

			String answerStr = String.join(",", answer.getAnswer());

			if (answerStr.equalsIgnoreCase(studentAnswerStr)) {
				return true;
			}
		}

		return false;
	}

}
