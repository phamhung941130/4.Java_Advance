package com.vti.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.vti.service.IEmailService;

@Component
public class onConfirmRegistrationViaEmailEventListener
		implements ApplicationListener<onConfirmRegistrationViaEmailEvent> {

	@Autowired
	private IEmailService emailService;

	@Override
	public void onApplicationEvent(onConfirmRegistrationViaEmailEvent event) {
//		Nhận được event ở đây và xử lý
		String email = event.getEmail(); // Email người dùng đăng ký
// Dùng EmailService để gửi email
		emailService.sentConfirmEmailToAccount(email);
	}

}
