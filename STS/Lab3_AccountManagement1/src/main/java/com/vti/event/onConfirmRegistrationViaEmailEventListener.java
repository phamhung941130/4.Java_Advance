package com.vti.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vti.service.IEmailService;

@Component
public class onConfirmRegistrationViaEmailEventListener
		implements ApplicationListener<onConfirmRegistrationviaEmailEvent> {

	private IEmailService emailService;

	@Override
	public void onApplicationEvent(onConfirmRegistrationviaEmailEvent event) {
		// nhận đc event
		String email = event.getEmail(); // email người dùng đăng ký
		emailService.
//		dùng EmailSevice để gửi
	}

}
