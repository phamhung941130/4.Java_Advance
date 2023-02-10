package com.example.form;

import java.util.Date;

import lombok.Data;

@Data
public class FormGroup {
	private int id;
	private String groupName;
	private int member;

	private Date createDate;

}
