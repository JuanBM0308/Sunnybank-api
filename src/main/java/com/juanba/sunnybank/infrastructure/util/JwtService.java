package com.juanba.sunnybank.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.juanba.sunnybank.infrastructure.persistance.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${sunny.security.oauth0.jwt.secret-key}")
    private String SECRET;

    public String generateToken(UserEntity user) {
        try {
            var algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withJWTId(UUID.randomUUID().toString())
                    .withIssuer("api-sunny-admin")
                    .withSubject(user.getEmail())
                    .withClaim("roles", List.of(user.getRole().name()))
                    .withIssuedAt(issueDate())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating JWT:", exception);
        }
    }

    private Instant issueDate() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("-05:00"));
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer("api-sunny-admin")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid JWT or expired");
        }
    }
}
