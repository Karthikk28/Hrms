package com.hrms.controller;

import com.hrms.entity.User;
import com.hrms.repository.UserRepository;
import com.hrms.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/oauth2")
public class MicrosoftOAuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    private static final String CLIENT_ID =
            "";

    private static final String CLIENT_SECRET =
            "";

    private static final String TENANT = "common";

    private static final String REDIRECT_URI =
            "http://localhost:8081/login/oauth2/code/microsoft";

    @GetMapping("/login/microsoft")
    public void microsoftLogin(HttpServletResponse response) throws Exception {

        String authorizeUrl =
                "https://login.microsoftonline.com/" + TENANT + "/oauth2/v2.0/authorize" +
                        "?client_id=" + CLIENT_ID +
                        "&response_type=code" +
                        "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8) +
                        "&scope=" + URLEncoder.encode("openid profile email User.Read", StandardCharsets.UTF_8) +
                        "&prompt=select_account";

        response.sendRedirect(authorizeUrl);
    }

    @GetMapping("/callback/microsoft")
    public void microsoftCallback(
            @RequestParam("code") String code,
            HttpServletResponse response
    ) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            String tokenUrl =
                    "https://login.microsoftonline.com/" + TENANT + "/oauth2/v2.0/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
            form.add("client_id", CLIENT_ID);
            form.add("client_secret", CLIENT_SECRET);
            form.add("grant_type", "authorization_code");
            form.add("code", code);
            form.add("redirect_uri", REDIRECT_URI);
            form.add("scope", "openid profile email User.Read");

            Map<String, Object> tokenResponse =
                    restTemplate.postForObject(
                            tokenUrl,
                            new HttpEntity<>(form, headers),
                            Map.class
                    );

            String accessToken = (String) tokenResponse.get("access_token");

            HttpHeaders userHeaders = new HttpHeaders();
            userHeaders.setBearerAuth(accessToken);

            Map<String, Object> userInfo =
                    restTemplate.exchange(
                            "https://graph.microsoft.com/v1.0/me",
                            HttpMethod.GET,
                            new HttpEntity<>(userHeaders),
                            Map.class
                    ).getBody();

        
            String email = (String) userInfo.get("mail");
            if (email == null || email.isBlank()) {
                email = (String) userInfo.get("userPrincipalName");
            }
            final String finalEmail = email;

           
            String firstName = (String) userInfo.getOrDefault("givenName", "User");
            String lastName = (String) userInfo.getOrDefault("surname", "");

            User user = userRepository.findByEmail(finalEmail).orElseGet(() -> {
                User u = new User();
                u.setEmail(finalEmail);
                u.setFirstName(firstName);
                u.setLastName(lastName);
                return userRepository.save(u);
            });

            String jwt = jwtService.generateToken(user.getEmail());

            response.sendRedirect(
                    "http://localhost:3000/oauth-success?token=" +
                            URLEncoder.encode(jwt, StandardCharsets.UTF_8)
            );

        } catch (Exception e) {
            try {
                response.sendRedirect(
                        "http://localhost:3000/login?error=microsoft_login_failed"
                );
            } catch (Exception ignored) {}
        }
    }
}
