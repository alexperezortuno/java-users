package com.glign.backend.component;

import com.glign.backend.jpa.entity.User;
import com.glign.backend.service.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private Key key;
    private ITokenService tokenService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationInMs;

    @Autowired
    public void setTokenService(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostConstruct
    public void init() {
        this.key = new SecretKeySpec(Base64.getDecoder().decode(jwtSecret), SignatureAlgorithm.HS384.getJcaName());
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        var response = Jwts.builder()
                .setSubject(user.getUuid().toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(this.key, SignatureAlgorithm.HS384)
                .compact();

        int seconds = Math.toIntExact((expiryDate.getTime() - now.getTime()) / 1000);
        tokenService.storeToken(user.getUuid().toString(), response, seconds);
        return response;
    }

    public boolean validateToken(String token) {
        try {
            token = cleanToken(token);
            var userId = getUserIdFromToken(token);
            if (!this.tokenExist(userId)) {
                return false;
            }
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        token = cleanToken(token);
        Claims claims = Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        token = cleanToken(token);
        Claims claims = Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public boolean isTokenExpired(String token) {
        token = cleanToken(token);
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public boolean tokenExist(String token) {
        token = cleanToken(token);
        return tokenService.tokenExist(token);
    }

    public boolean removeToken(String id) {
        return tokenService.removeToken(id);
    }

    private String cleanToken(String token) {
        if (token.contains("Bearer ") || token.contains("bearer ")) {
            token = token.replace("Bearer ", "");
            token = token.replace("bearer ", "");
        }
        return token;
    }
}