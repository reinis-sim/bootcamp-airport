package com.example.demo.services.impl;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfDocument;

@Component
public class EmailServiceImpl {
	@Autowired
	JavaMailSender emailSender;
	
	void sendEmail(String to, PdfDocument pdfdoc) {
		MimeMessage message = emailSender.createMimeMessage();
		String subject = "Airport Boarding Pass";
		String text = "Check attachments for your boarding pass";
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(text);
		    helper.addAttachment("BoardingPass", (DataSource) pdfdoc);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
