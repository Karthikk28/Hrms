package com.hrms.service;

import com.hrms.dto.ForgotPasswordRequest;
import com.hrms.dto.TokenResetPasswordRequest;
import com.hrms.entity.PasswordResetToken;
import com.hrms.entity.User;
import com.hrms.repository.PasswordResetTokenRepository;
import com.hrms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordService(UserRepository userRepository,
                           PasswordResetTokenRepository tokenRepository,
                           EmailService emailService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public String forgotPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setEmail(user.getEmail());
        resetToken.setToken(token);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        tokenRepository.save(resetToken);

        emailService.sendResetLink(user.getEmail(), token);

        return "Reset link sent successfully";
    }

    public String resetPassword(TokenResetPasswordRequest request) {

        PasswordResetToken token = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        User user = userRepository.findByEmail(token.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        tokenRepository.deleteByEmail(user.getEmail());

        return "Password updated successfully";
    }
}