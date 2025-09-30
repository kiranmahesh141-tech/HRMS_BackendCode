package com.HRMSdemo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreplyhrmspcx@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
	}

	public void sendHtmlEmailWithImage(String email, String subject, String firstName, String role, String username,
			String plainPassword) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom("noreplyhrmspcx@gmail.com");
			helper.setTo(email);
			helper.setSubject(subject);

			String htmlBody = "<html><body>" + "<p>Dear " + firstName + ",</p>" + "<p>Congratulations! 🎉</p>"
					+ "<p>You have been successfully onboarded as a <b>" + role + "</b> with PharmCrux.</p>"
					+ "<p>Here are your login credentials:</p>" + "<ul>" + "<li>Username: " + username + "</li>"
					+ "<li>Password: " + plainPassword + "</li>" + "</ul>"
					+ "<p>Login Portal: <a href='http://localhost:3000/login'>Click here to login</a></p>"
					+ "<p>Please keep this information safe and change your password after first login.</p>"
					+ "<p><img src='cid:logoImage'></p>" + "<p>Best Regards,<br>HR Team<br>PharmCrux Pvt Ltd</p>"
					+ "</body></html>";

			helper.setText(htmlBody, true);

// Use ClassPathResource instead of FileSystemResource
			ClassPathResource res = new ClassPathResource("static/logo.png");
			helper.addInline("logoImage", res);

			mailSender.send(message);
			System.out.println("Email sent successfully to " + email);
		} catch (MessagingException e) {
			System.err.println("Failed to send email to " + email);
			e.printStackTrace();
		}
	}

}
