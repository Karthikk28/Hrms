package com.hrms.controller;

import com.hrms.dto.ForgotPasswordRequest;
import com.hrms.dto.TokenResetPasswordRequest;
import com.hrms.service.PasswordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@CrossOrigin(origins = "http://localhost:3000")
public class PasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/forgot")
    public String forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return passwordService.forgotPassword(request);
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestBody TokenResetPasswordRequest request) {
        return passwordService.resetPassword(request);
    }
}