package com.web.application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.application.auth.dto.response.TokenInfo;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;

@Slf4j
@Component
public class JwtUtil {


    private final SecretKey secretKey;
    private final ObjectMapper objectMapper;

    @Value("${app.jwt.access.expiration}")
    private Long accessExpiration;

    @Value("${app.jwt.refresh.expiration}")
    private Long refreshExpiration;

    public JwtUtil(@Value("${app.jwt.secret}") String secret, ObjectMapper objectMapper) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.objectMapper = objectMapper;
    }

    // Access 토큰 발급
    public String createAccessToken(TokenInfo token) {
        Long expiration = accessExpiration * 60 * 1000L;

        return createToken(token, expiration);
    }

    // Refresh 토큰 발급
    public String createRefreshToken(TokenInfo token) {
        Long expiration = refreshExpiration * 60 * 1000L;

        return createToken(token, expiration);
    }

    // 토큰 정보 추출
    public TokenInfo getTokenInfo(String token) {

        LinkedHashMap<?, ?> tokenInfo = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload().get("token", LinkedHashMap.class);

        return objectMapper.convertValue(tokenInfo, TokenInfo.class);
    }

    // 토큰 검증
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    // 토큰 만료일
    public Date getExpiration(String token) {

        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload().getExpiration();
    }

    // 토큰 발급
    private String createToken(TokenInfo token, Long expiration) {

        return Jwts.builder()
                .claim("token", token)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }

}
