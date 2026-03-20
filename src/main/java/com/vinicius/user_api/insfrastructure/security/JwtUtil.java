package com.vinicius.user_api.insfrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {

    // Chave secreta usada para assinar o token (DEVE ser longa o suficiente)
    private final String secretKey = "sua-chave-secreta-super-segura-que-deve-ser-bem-longa";

    // Converte a chave String em uma chave criptográfica válida
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Gera um token JWT com base no username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // define o usuário dono do token
                .setIssuedAt(new Date()) // data de criação
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // expira em 1h
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // assina o token
                .compact(); // gera o token final
    }

    // Extrai todas as informações (claims) do token
    public Claims extractClaims(String token) {
        return Jwts.parser() // forma correta atual
                .setSigningKey(getSigningKey()) // valida com a chave
                .build()
                .parseClaimsJws(token) // parse do token
                .getBody(); // retorna o conteúdo
    }

    // Extrai o username do token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Verifica se o token expirou
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valida o token comparando com o usuário real
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token); // pega username do token
        return (extractedUsername.equals(username) && !isTokenExpired(token)); // compara e verifica expiração
    }
}