package com.example.hibernate.jwt;

import io.jsonwebtoken.*;
import javax.crypto.SecretKey;
import java.util.Date;

public class JWT {

    SecretKey key = Jwts.SIG.HS256.key().build();
    String secretKey = "secretKey";

    long expirationTime = 1000 * 60 * 60 * 24;

    public String generateToken() {
        return Jwts.builder()
                .subject("user1")
                .claim("username", "user1")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }


    public void setClaims(String token) {

        Claims claims = extractClaims(token);

        String subject = claims.getSubject();
        String username = claims.get("username", String.class);
        Date expiration = claims.getExpiration();
        Date issuedAt = claims.getIssuedAt();

        System.out.println("Subject: " + subject);
        System.out.println("Username: " + username);
        System.out.println("Expiration: " + expiration);
        System.out.println("Issued At: " + issuedAt);
    }

    private Claims extractClaims(String token) {

        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        return claimsJws.getPayload();
    }
}



