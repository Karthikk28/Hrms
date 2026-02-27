package com.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("ZentraHR OTP Verification");
        message.setText("Dear User,\n\nYour OTP is: " + otp +
                "\nThis OTP is valid for 5 minutes.\n\nRegards,\nZentraHR Team");

        mailSender.send(message);
    }

    public void sendResetLink(String to, String token) {
        String resetLink = "http://localhost:3000/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reset Your Password");
        message.setText("Click the link below to reset your password:\n" + resetLink);

        mailSender.send(message);
    }

    public void sendEmail(String to, String subject, String messageText) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(messageText);

        mailSender.send(mail);
    }
}