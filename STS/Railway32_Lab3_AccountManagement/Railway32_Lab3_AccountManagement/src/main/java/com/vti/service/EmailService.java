package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sentConfirmEmailToAccount(String email) {
		String subject = "Xác nhận đăng ký tài khoản";
		String content = "Bạn đã đăng ký tài khoản thành công";

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("system.vti.academy@gmail.com");
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);

		javaMailSender.send(simpleMailMessage);
	}

}
