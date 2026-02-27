package com.hrms.service;
 
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;
 
import java.security.Key;

import java.util.Date;
 
@Service

public class JwtService {
 

    private static final String SECRET = "mysecretkey1234567890mysecretkey1234567890"; 

    private static final long EXPIRATION = 1000 * 60 * 60;
 
    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(SECRET.getBytes());

    }

    public String generateToken(String email) {

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))

                .signWith(getSigningKey(), SignatureAlgorithm.HS256)

                .compact();

    }

    public boolean isTokenValid(String token, String email) {

        String tokenEmail = extractEmail(token);

        return (tokenEmail.equals(email) && !isTokenExpired(token));

    }
 
    public String extractEmail(String token) {

        return extractAllClaims(token).getSubject();

    }

    private boolean isTokenExpired(String token) {

        return extractAllClaims(token).getExpiration().before(new Date());

    }

    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getSigningKey())

                .build()

                .parseClaimsJws(token)

                .getBody();

    }

}

 