package com.swiftselect.infrastructure.security;

import com.swiftselect.infrastructure.exceptions.ApplicationException;
import com.swiftselect.utils.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private long jwtExpirationDate = SecurityConstants.JWT_EXPIRATION;
    private String jwtSecret = SecurityConstants.JWT_SECRET_KEY;

    // Utility method to generate token
    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date issueDate = new Date();
        Date expirationDate = new Date(issueDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(key())
                .compact();
    }

    // key Decoder
    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // Token name Extractor
    public String getUserName(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Jwt Token Validator
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);

            return true;
        } catch (MalformedJwtException e) {
            throw new ApplicationException("Invalid JWT Token", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException e) {
            throw new ApplicationException("Expired JWT Token", HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException e) {
            throw new ApplicationException("Unsupported JWT Token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("JWT Claims string is empty", HttpStatus.BAD_REQUEST);
        }
    }
}
