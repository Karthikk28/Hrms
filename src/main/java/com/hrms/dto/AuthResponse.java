package com.hrms.dto;

public class AuthResponse {

    private String firstName;
    private String email;
    private String accessToken;
    private String refreshToken;
    

    public AuthResponse() {}
    public AuthResponse(String firstName, String email, String accessToken, String refreshToken) {
        this.firstName = firstName;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getFirstName() { 
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}