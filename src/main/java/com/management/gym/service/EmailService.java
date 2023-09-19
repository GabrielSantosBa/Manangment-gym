package com.management.gym.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.model.dto.EmailDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	public void send(EmailDTO emailDTO) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailFrom);
			message.setTo(emailDTO.getEmailTo());
			message.setSubject(emailDTO.getSubject());
			message.setText(emailDTO.getMessage());
			javaMailSender.send(message);
		} catch (MailException err) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in sending email..");
		}
		
	}
	
}
