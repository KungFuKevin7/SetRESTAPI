package com.example.SetRESTAPI.api.authentication;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    //@Value("${security.jwt.secret-key}")
    private final String SecretKey = "secretKey";

    //@Value("${security.jwt.expiration-time}")
    private final int ExpirationTime = 300000;

    public String getPlayerName(String token) {
        return Jwts.parser()
                .setSigningKey(SecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateToken(String playerName) {
      return Jwts.builder()
                .setSubject(playerName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
                .signWith(SignatureAlgorithm.HS256, SecretKey)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parser()
                .setSigningKey(SecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expirationDate.before(new Date());
    }

    public boolean validateToken(String receivedToken, String playerName) {
        try {
            String playername = getPlayerName(receivedToken);
            return playerName.equals(playername) && !isTokenExpired(receivedToken);
        } catch (Exception e) {
            return false;
        }
    }




}
