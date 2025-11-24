package com.bowlfullbuddies.bowlfullbuddies.config;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.hmacShaKeyFor("replace-this-with-a-very-long-secret-key-should-be-256-bit".getBytes());
    private final long expirationMs = 1000L * 60 * 60 * 24; // 24h

    public String generateToken(String username, Long userId) {
        Date now = new Date();
        return Jwts.builder()
            .setSubject(username)
            .claim("userId", userId)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + expirationMs))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
