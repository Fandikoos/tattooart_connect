package com.almozara.tattooart_connect.security.jwt;

import com.almozara.tattooart_connect.security.service.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .claim("roles", getRoles(userPrincipal))
                .claim("cara", "feisima")
                .signWith(getKey(secret), Jwts.SIG.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getKey(secret)).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Expired token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token");
        } catch (MalformedJwtException e) {
            logger.error("Malformed token");
        } catch (io.jsonwebtoken.security.SignatureException e) {
            logger.error("Bad signature");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Fail token");
        }
        return false;
    }

    private List<String> getRoles(UserPrincipal userPrincipal){
        return userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    private SecretKey getKey(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
