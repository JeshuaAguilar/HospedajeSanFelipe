package com.hospedajesanfelipe.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private String secret = "dwHFwTQc4UAAOdeuunjhvH/+7eXmHnwioncruUYV8DO7nPknoVCHMdhNdN9lPZ1pVlBOKr7xrfrIjfXpvQ2NTQ==";

    private long expirationMs = 10800000;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
