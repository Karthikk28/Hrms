package com.hrms.config;

import com.hrms.entity.User;
import com.hrms.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public OAuth2SuccessHandler(UserRepository userRepository,
                                JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
     
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String registrationId =
                request.getRequestURI().contains("microsoft")
                        ? "MICROSOFT"
                        : "GOOGLE";

        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            email = oAuth2User.getAttribute("preferred_username");
        }

        if (email == null) {
            email = oAuth2User.getAttribute("upn");
        }

        String name = oAuth2User.getAttribute("name");

        if (name == null) {
            name = email;
        }

        final String finalEmail = email;
        final String finalName = name;
        final String finalProvider = registrationId;

        User user = userRepository.findByEmail(finalEmail)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(finalEmail);
                    newUser.setName(finalName);
                    newUser.setProvider(finalProvider);
                    return userRepository.save(newUser);
                });

        String accessToken = jwtUtil.generateToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
 
        String redirectUrl =
                "http://localhost:3000/oauth-success" +
                "?accessToken=" + accessToken +
                "&refreshToken=" + refreshToken +
                "&email=" + email;
 
        response.sendRedirect(redirectUrl);
    }
    }
     
     