package com.vti.event;

import org.springframework.context.ApplicationEvent;

public class onConfirmRegistrationviaEmailEvent extends ApplicationEvent {
	private String email;

	public onConfirmRegistrationviaEmailEvent(String email) {
		super(email);
		this.email = email;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
