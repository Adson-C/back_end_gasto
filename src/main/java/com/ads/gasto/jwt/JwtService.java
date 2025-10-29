package com.ads.gasto.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ads.gasto.model.UsuariosModel;
import com.ads.gasto.service.impl.VariableGlobalServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    @Autowired
    private VariableGlobalServiceImpl variableGlobalService;

    public String generateToken(String username) {
       Map<String, Object> claims = new HashMap<>();
       return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 6000 * 30))
            .signWith(getSigningKey() ,SignatureAlgorithm.HS512)
            .compact();
    }
    
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(variableGlobalService.buscarPorId(2L).getValor());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaims(token, claims -> claims.getSubject());
    }

    public <T> T extractClaims(String token, java.util.function.Function<io.jsonwebtoken.Claims, T> claimsResolver) {
        final io.jsonwebtoken.Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private io.jsonwebtoken.Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, claims -> claims.getExpiration()).before(new Date());
    }

    public Boolean validateToken(String token, UsuariosModel userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getCorreo()) && !isTokenExpired(token));
    }
}
