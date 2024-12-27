package com.udemy.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    JwtService() {}

    public static final String SECRET_KEY = "zv8y4cujxMSot1cgv/EnNI0Ks2zvK0+AJ8++hLI62dU=";

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "ADMIN");

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static void main(String[] args){
            String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaXlhciIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTczNTIzODQ1MSwiZXhwIjoxNzM1MjQ1NjUxfQ.WSycg5VTmm3dvf_umRpcA-cv_BTZhrFNBoAuOXmYMtQ";
            String key = "role";
            Object value = getClaimsByKey(token, key);
            System.out.println(value);
    }

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public static Object getClaimsByKey(String token, String key) {
        Claims claims = getClaims(token);
        return claims.get(key);
    }

    public <T> T exportToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    public static Key getKey(){
        byte[] keys = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }

    public String getUsernameFromToken(String token) {
        return exportToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expiredDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expiredDate);
    }

}
