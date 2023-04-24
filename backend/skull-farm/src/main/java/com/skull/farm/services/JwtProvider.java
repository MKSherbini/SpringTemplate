package com.skull.farm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtEncoder jwtEncoder;
    @Value("${security.jwtExpirationInSeconds}")
    private long jwtExpirationInSeconds;

    public String generateToken(Map<String, Object> claims, String subject) {
        var claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(jwtExpirationInSeconds))
                .subject(subject)
                .claims(c -> c.putAll(claims))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public String generateToken(Map<String, Object> claims) {
        var claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(jwtExpirationInSeconds))
                .claims(c -> c.putAll(claims))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public Map<String, Object> getInfo() {
        log.info("authorities: {}",
                SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        log.info("authorities: {}",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getClaims();
    }
}
