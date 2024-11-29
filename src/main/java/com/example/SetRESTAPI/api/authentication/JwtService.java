package com.example.SetRESTAPI.api.authentication;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtService {
    private final String SecretKey = "secretKey";
    private final int ExpirationTime = 300000;

    public String generateToken(String username) {

      return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
                .signWith(SignatureAlgorithm.HS256, SecretKey)
                .compact();
    }

    public String validateToken(String receivedToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecretKey)
                    .parseClaimsJws(receivedToken)
                    .getBody();
            return claims.getSubject();

        } catch (Exception e) {

            return "Token is Invalid!";
        }
    }




}
