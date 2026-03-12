package com.hrms.service;
 
import com.hrms.dto.*;

import com.hrms.entity.User;

import com.hrms.repository.UserRepository;

import com.hrms.config.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
 
@Service
public class AuthService {
 
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final OtpService otpService;
    
    public AuthService(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            OtpService otpService) {
this.userRepository = userRepository;
this.passwordEncoder = passwordEncoder;
this.jwtUtil = jwtUtil;
this.otpService = otpService;
}
    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            throw new RuntimeException("User already exists with email: " + request.getEmail());

        }
 
        User user = new User();

        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setOrganization(request.getOrganization());

        user.setPhone(request.getPhone());

        user.setSubscriptionType(request.getSubscriptionType());

        user.setCountryCode(request.getCountryCode() != null ? request.getCountryCode() : "+91");

        user.setProvider(request.getProvider() != null ? request.getProvider() : "local");

        user.setOtpAttempts(0); 

        userRepository.save(user);


        String accessToken = jwtUtil.generateToken(user.getEmail());

        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
 
        return new AuthResponse(user.getFirstName(),user.getEmail(), accessToken, refreshToken);

    }

    public AuthResponse login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String accessToken = jwtUtil.generateToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(user.getFirstName(),user.getEmail(), accessToken, refreshToken);
    }

       public AuthResponse loginWithDomain(DomainLoginRequest request) {

           User user = userRepository.findByEmail(request.getEmail())
                   .orElseThrow(() -> new RuntimeException("User not found"));

           if (!user.getOrganization().equalsIgnoreCase(request.getDomain())) {
               throw new RuntimeException("User does not belong to this organization");
           }

           if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
               throw new RuntimeException("Invalid password");
           }

           String accessToken = jwtUtil.generateToken(user.getEmail());
           String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

           return new AuthResponse(user.getFirstName(),user.getEmail(), accessToken, refreshToken);
       }
 
    public String sendOtp(String email) {

        otpService.generateAndSendOtp(email);

        return "OTP sent successfully to " + email;

    }


    public AuthResponse loginWithOtp(OtpRequest request) {

        return otpService.verifyOtpAndLogin(request);

    }

    public String forgotPassword(String email) {

        otpService.generateAndSendOtp(email);

        return "OTP sent to " + email + " for password reset";

    }

    public String resetPassword(OtpRequest request, String newPassword) {

        User user = userRepository.findByEmail(request.getEmail())

                .orElseThrow(() -> new RuntimeException("User not found"));
 
        otpService.verifyOtpAndLogin(request);
 
        user.setPassword(passwordEncoder.encode(newPassword));

        user.setOtpAttempts(0);

        userRepository.save(user);
 
        return "Password updated successfully";

    }
    public void changePassword(String email, String currentPassword, String newPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setOtpAttempts(0);

        userRepository.save(user);
    }

}

 