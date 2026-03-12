package com.hrms.controller;

import com.hrms.dto.EmailRequest;
import com.hrms.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {

        emailService.sendEmail(
                request.getTo(),
                request.getSubject(),
                request.getMessage()
        );

        return ResponseEntity.ok("Email sent successfully");
    }
}

