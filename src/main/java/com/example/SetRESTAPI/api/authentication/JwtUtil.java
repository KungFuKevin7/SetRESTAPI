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

    @Value("${security.jwt.secret-key}")
    private final String SecretKey = "secretKey";

    @Value("${security.jwt.expiration-time}")
    private final int ExpirationTime = 300000;

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody();
    }

    public String getPlayerName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> claims, String playerName) {
      return Jwts.builder()
                .setClaims(claims)
                .setSubject(playerName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
                .signWith(SignatureAlgorithm.HS256, SecretKey)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public boolean validateToken(String receivedToken, UserDetails userDetails) {
        try {
            String playerName = getPlayerName(receivedToken);
            return playerName.equals(userDetails.getUsername()) && !isTokenExpired(receivedToken);
        } catch (Exception e) {
            return false;
        }
    }




}
