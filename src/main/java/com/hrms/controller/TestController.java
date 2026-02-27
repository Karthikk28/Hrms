package com.hrms.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final JavaMailSender mailSender;

    public TestController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/mail")
    public String sendTestMail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("karthikkolukuluru28@gmail.com");
        message.setSubject("Test Mail from HRMS");
        message.setText("Mail is working successfully!");

        mailSender.send(message);

        return "Mail sent successfully";
    }
}