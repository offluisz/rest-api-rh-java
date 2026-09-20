package com.example.api_rh.security.jwt;

import com.example.api_rh.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${api.jwt.secret}")
    private String jwtSecret;

    @Value("${api.jwt.expiration-ms:18000000}")
    private int jwtExpiraMs;

    public String gerarToken(UserDetailsImpl userData) {
        return Jwts.builder()
                .subject(userData.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiraMs))
                .signWith(getSignKey())
                .compact();
    }

    public SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validarToken(String authToekn) {
        try {
            Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(authToekn);

            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Token JWT inválido: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token JWT não suportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string está vazia: " + e.getMessage());
        }
        return false;
    }

    public String getUserToken(String token) {
        return Jwts.parser().verifyWith(getSignKey())
                .build().parseSignedClaims(token).getBody().getSubject();
    }
}
