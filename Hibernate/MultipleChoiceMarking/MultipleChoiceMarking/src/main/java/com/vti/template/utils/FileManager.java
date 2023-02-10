package com.vti.template.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import com.vti.template.object.exam.Answer;
import com.vti.template.object.exam.Exam;
import com.vti.template.object.exam.Question;
import com.vti.template.object.studentanswer.Student;
import com.vti.template.object.studentanswer.StudentAnswer;

public class FileManager {

	public List<String> getExcelFiles(String folderPath) {

		File folder = new File(folderPath);

		if (!folder.isDirectory()) {
			System.out.println("It is not Folder");
		}

		List<String> excelFileNames = new ArrayList<>();

		for (File file : folder.listFiles()) {
			String fileName = file.getName();

			// get only excel file
			if (fileName.endsWith(".xlsx")) {
				excelFileNames.add(fileName);
			}
		}

		return excelFileNames;
	}

	public Exam parseAnswerFile(String excelFilePath) {
		try {

			// init workbook
			Workbook workbook = WorkbookFactory.create(new File(excelFilePath));

			// init sheet
			Sheet answerSheet = workbook.getSheetAt(0);

			// read content

			// get basic information exam
			String nameExam = answerSheet.getRow(0).getCell(CellReference.convertColStringToIndex("B"))
					.getStringCellValue();

			String codeExam = answerSheet.getRow(1).getCell(CellReference.convertColStringToIndex("D"))
					.getStringCellValue();

			int totalQuestion = (int) answerSheet.getRow(2).getCell(CellReference.convertColStringToIndex("D"))
					.getNumericCellValue();

			String timeExam = answerSheet.getRow(3).getCell(CellReference.convertColStringToIndex("D"))
					.getStringCellValue();

			// question & answer
			int title_question_row_index = 9;
			List<Question> questions = new ArrayList<>();

			for (int i = 0; i < totalQuestion; i++) {
				questions.add(getInformationQuestion(answerSheet.getRow(title_question_row_index + i)));
			}

			// Closing the workbook
			workbook.close();

			return new Exam(nameExam, codeExam, totalQuestion, timeExam, questions);

		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Question getInformationQuestion(Row row) {

		int questionNumber = (int) row.getCell(CellReference.convertColStringToIndex("A")).getNumericCellValue();

		String levelQuestion = row.getCell(CellReference.convertColStringToIndex("B")).getStringCellValue();

		// get answers
		List<Answer> answers = new ArrayList<>();
		for (int i = CellReference.convertColStringToIndex("C"); i < 10; i++) {

			if (row.getCell(i) == null) {
				break;
			}

			String cellValue = row.getCell(i).getStringCellValue();

			if (null == cellValue || cellValue.isEmpty()) {
				break;
			} else {
				answers.add(parseAnswerCell(cellValue));
			}
		}

		return new Question(questionNumber, levelQuestion, answers);

	}

	private Answer parseAnswerCell(String cellValue) {
		return new Answer(Arrays.asList(cellValue.split(",")));
	}

	public Student parseStudentAnswerFile(String excelFilePath, Exam exam) {
		File file = new File(excelFilePath);

		if (!file.exists()) {
			System.out.println("File Not Exists!");
		}

		try {

			// init workbook
			Workbook workbook = WorkbookFactory.create(new File(excelFilePath));

			// init sheet
			Sheet answerSheet = workbook.getSheetAt(0);

			// read content

			// get basic information exam
			String fullNameStudent = answerSheet.getRow(4).getCell(CellReference.convertColStringToIndex("C"))
					.getStringCellValue();

			// question & answer
			int label_Dap_An_row_index = 11;
			List<StudentAnswer> answers = new ArrayList<>();

			for (int i = 0; i < exam.getTotalQuestion(); i++) {
				answers.add(getInformationStudentAnswer(answerSheet.getRow(label_Dap_An_row_index + i)));
			}

			// Closing the workbook
			workbook.close();

			return new Student(fullNameStudent, answers);

		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private StudentAnswer getInformationStudentAnswer(Row row) {

		String questionNumber = row.getCell(CellReference.convertColStringToIndex("A")).getStringCellValue();

		// get answers
		List<String> answers = new ArrayList<>();
		for (int i = CellReference.convertColStringToIndex("B"); i < 4
				+ CellReference.convertColStringToIndex("B"); i++) {

			String cellValue = row.getCell(i).getStringCellValue();
			if (parseStudentAnswer(cellValue)) {
				answers.add(CellReference.convertNumToColString(i - CellReference.convertColStringToIndex("B")));
			}
		}

		return new StudentAnswer(questionNumber, answers);
	}

	private boolean parseStudentAnswer(String answer) {

		if (null == answer || answer.isEmpty() || !answer.equalsIgnoreCase("x")) {
			return false;
		}

		return true;
	}

	public void copyFile(String originalFilePath, String folderPath) {

		File originalFile = new File(originalFilePath);

		if (!originalFile.exists()) {
			System.out.println("original File Exists!");
		}

		createFolderIfNotExists(folderPath);

		File copiedFile = new File(folderPath + "\\" + originalFile.getName());

		if (copiedFile.exists()) {
			copiedFile.delete();
			// System.out.println("Destination File Exists!");
		}

		try {
			com.google.common.io.Files.copy(originalFile, copiedFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createFolderIfNotExists(String folderPath) {
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	public void writeMarkResult(Student student, String excelFilePath, Exam exam) {
		File file = new File(excelFilePath);

		if (!file.exists()) {
			System.out.println("File Not Exists!");
		}

		try {
			// init workbook
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			Workbook workbook = WorkbookFactory.create(inputStream);

			// init sheet
			Sheet answerSheet = workbook.getSheetAt(0);

			// read content

			// Correct Question Amount
			Row row10 = answerSheet.getRow(10);
			Cell correctQuestionAmountLabel = row10.createCell(CellReference.convertColStringToIndex("G"));
			correctQuestionAmountLabel.setCellType(CellType.STRING);
			correctQuestionAmountLabel.setCellValue("Số câu hỏi đúng");
			correctQuestionAmountLabel.setCellStyle(getPointLabelStyle(workbook));
			answerSheet.autoSizeColumn(CellReference.convertColStringToIndex("G"));

			Row row11 = answerSheet.getRow(11);
			Cell correctQuestionAmountValue = row11.createCell(CellReference.convertColStringToIndex("G"));
			correctQuestionAmountValue.setCellType(CellType.NUMERIC);
			correctQuestionAmountValue.setCellValue(student.getCorrectQuestionAmount());
			correctQuestionAmountValue.setCellStyle(getPointValueStyle(workbook));

			// point
			Cell pointLabel = row10.createCell(CellReference.convertColStringToIndex("H"));
			pointLabel.setCellType(CellType.STRING);
			pointLabel.setCellValue("Điểm");
			pointLabel.setCellStyle(getPointLabelStyle(workbook));

			Cell pointValue = row11.createCell(CellReference.convertColStringToIndex("H"));
			pointValue.setCellType(CellType.NUMERIC);
			pointValue.setCellValue(((float) student.getCorrectQuestionAmount()) / exam.getTotalQuestion() * 10);
			pointValue.setCellStyle(getPointValueStyle(workbook));

			// highlight wrong answer
			int label_Dap_An_row_index = 10;

			for (int wrongQuestion : student.getWrongQuestions()) {
				highlightCorrectAnswer(workbook, answerSheet.getRow(label_Dap_An_row_index + wrongQuestion),
						exam.getQuestions().get(wrongQuestion - 1).getAnswers().get(0).getAnswer());
			}

			inputStream.close();

			// Write the output to the file
			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);

			workbook.close();
			outputStream.close();

		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private CellStyle getPointLabelStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();

		Font font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);

		style.setAlignment(HorizontalAlignment.CENTER);

		return style;
	}

	private CellStyle getPointValueStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();

		Font font = workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);

		style.setAlignment(HorizontalAlignment.CENTER);

		return style;
	}

	private CellStyle getCorrectAnswerStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();

		Font font = workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);

		style.setAlignment(HorizontalAlignment.CENTER);

		return style;
	}

	private CellStyle getWrongQuestionStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();

		Font font = workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		style.setFont(font);

		return style;
	}

	private void highlightCorrectAnswer(Workbook workbook, Row row, List<String> answers) {
		row.getCell(CellReference.convertColStringToIndex("A")).setCellStyle(getWrongQuestionStyle(workbook));

		for (String answer : answers) {
			Cell cell = row.getCell(CellReference.convertColStringToIndex(answer) + 1);
			cell.setCellValue("x");
			cell.setCellStyle(getCorrectAnswerStyle(workbook));
		}
	}
}
