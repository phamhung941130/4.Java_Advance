package com.vti.template.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {

	private String answerFilePath;

	private String answerStudentsFolderPath;

	private String outputGradedFolderPath;

	public PropertyConfig() {

		Properties propertiesFile = new Properties();

		try {
			propertiesFile.load(new FileInputStream("src/main/resource/config.properties"));

			answerFilePath = propertiesFile.getProperty("answer_file_path");
			answerStudentsFolderPath = propertiesFile.getProperty("answer_students_folder_path");
			outputGradedFolderPath = propertiesFile.getProperty("output_graded_folder_path");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the answerFilePath
	 */
	public String getAnswerFilePath() {
		return answerFilePath;
	}

	public void setAnswerFilePath(String answerFilePath) {
		this.answerFilePath = answerFilePath;
	}

	public String getAnswerStudentsFolderPath() {
		return answerStudentsFolderPath;
	}

	public void setAnswerStudentsFolderPath(String answerStudentsFolderPath) {
		this.answerStudentsFolderPath = answerStudentsFolderPath;
	}

	public String getOutputGradedFolderPath() {
		return outputGradedFolderPath;
	}

	public void setOutputGradedFolderPath(String outputGradedFolderPath) {
		this.outputGradedFolderPath = outputGradedFolderPath;
	}

}
