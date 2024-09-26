package com.example.sirumatek.util;

import java.io.*;
import com.example.sirumatek.config.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@Component
public class JwtUtil {
    @Autowired
    private MyConfig secretKey; // Usamos inyección de dependencias

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public JwtUtil(MyConfig secretKey) {
        this.secretKey = secretKey;
    }

    public String generateToken(String username) {
        try {
            if (username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username is null or empty");
            }

            String secret = secretKey.getSecret_key();
            if (secret == null || secret.isEmpty()) {
                throw new IllegalStateException("JWT secret key is null or empty");
            }

            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expira en 10 horas
                    .signWith(SignatureAlgorithm.HS256, secretKey.getSecret_key())
                    .compact();
        } catch (IllegalArgumentException e) {
            logger.error("Error generating token: {}", e.getMessage());
            throw e;
        } catch (IllegalStateException e) {
            logger.error("Secret key error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error generating token: {}", e.getMessage());
            throw new RuntimeException("Error generating token");
        }
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getSecret_key())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getSecret_key())
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}