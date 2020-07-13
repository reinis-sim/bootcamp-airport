package com.example.demo.services.impl;

import java.io.ByteArrayOutputStream;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.demo.models.BoardingPass;
import com.example.demo.services.IUserService;

@Component
public class EmailServiceImpl {
	@Autowired
	JavaMailSender emailSender;
	@Autowired
	IUserService userService;
	
	public void sendEmail(String to , BoardingPass boardingPass) {
		//Creates MIME Style message
		MimeMessage message = emailSender.createMimeMessage();
		//Output stream for streaming PDF file
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		//Message body and subject
		String subject = "Airport Boarding Pass";
		String text = "Check attachments for your boarding pass";
		try {
			//MIME Helper for sending message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(text);
		    //Call for creation of PDF file while passing in output stream
		    userService.exportBookedFlightAsPDF(boardingPass, outputStream);
		    //Save PDF to byte array
		    byte[] bytes = outputStream.toByteArray();
		    //Data source provides access to file
		    DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		    //Add PDF as email attachment
		    helper.addAttachment("BoardingPass", dataSource);
		    emailSender.send(message);
		} catch (MessagingException e) {
			System.out.println("Failed to send message!");
			e.printStackTrace();
		}
		
	}

}
