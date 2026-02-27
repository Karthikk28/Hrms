package com.hrms.service;

import com.hrms.config.JwtUtil;
import com.hrms.dto.AuthResponse;
import com.hrms.dto.OtpRequest;
import com.hrms.entity.User;
import com.hrms.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final JavaMailSender mailSender;

    private final Map<String, OtpDetails> otpStorage = new ConcurrentHashMap<>();

    private static final int OTP_EXPIRY_MINUTES = 5;
    private static final int MAX_RESEND = 6;

    public OtpService(UserRepository userRepository,
                      JwtUtil jwtUtil,
                      JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.mailSender = mailSender;
    }

    public void generateAndSendOtp(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OtpDetails existing = otpStorage.get(email);
        if (existing != null && existing.resendCount >= MAX_RESEND) {
            throw new RuntimeException("Maximum OTP attempts reached");
        }

        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);

        otpStorage.put(email,
                new OtpDetails(otp, expiry,
                        existing == null ? 1 : existing.resendCount + 1));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText(
                "Hi,\n\n" +
                "Your OTP for reset password is: " + otp + "\n" +
                "OTP is valid for 5 minutes.\n\n" +
                "Regards,\n" +
                "Zentra HR"
        );

        mailSender.send(message);
    }

    public AuthResponse verifyOtpAndLogin(OtpRequest request) {

        OtpDetails details = otpStorage.get(request.getEmail());

        if (details == null)
            throw new RuntimeException("No OTP found");

        if (LocalDateTime.now().isAfter(details.expiry))
            throw new RuntimeException("OTP expired");

        if (!details.otp.equals(request.getOtp()))
            throw new RuntimeException("Invalid OTP");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtUtil.generateToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        otpStorage.remove(request.getEmail());

        return new AuthResponse(user.getName(),user.getEmail(), accessToken, refreshToken);
    }

    private static class OtpDetails {
        private final String otp;
        private final LocalDateTime expiry;
        private final int resendCount;

        public OtpDetails(String otp, LocalDateTime expiry, int resendCount) {
            this.otp = otp;
            this.expiry = expiry;
            this.resendCount = resendCount;
        }
    }
}