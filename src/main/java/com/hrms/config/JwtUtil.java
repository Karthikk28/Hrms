package com.hrms.config;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
 
import java.security.Key;
import java.util.Date;
 
@Component
public class JwtUtil {
    private static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 60; 
    private static final long REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7; 
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
 
    public String generateToken(String username) {
        return buildToken(username, ACCESS_TOKEN_VALIDITY);
    }
 
    public String generateRefreshToken(String username) {
        return buildToken(username, REFRESH_TOKEN_VALIDITY);
    }
     
        private String buildToken(String username, long validity) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + validity))
                    .signWith(key)
                    .compact();
        }
     
        public String extractUsername(String token) {
            return extractAllClaims(token).getSubject();
        }
     
        public boolean isTokenValid(String token, String username) {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username) && !isTokenExpired(token);
        }
     
        private boolean isTokenExpired(String token) {
            return extractAllClaims(token)
                    .getExpiration()
                    .before(new Date());
        }
     
        private Claims extractAllClaims(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
    }
     