package com.example.survey.service;

import com.example.survey.model.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Service

public class JWTService {

    public String createJWT (UserDto userDto){
        byte[] keyBytes = Decoders.BASE64.decode("servicesecretkeyservicesecretkeyservicesecretkey");
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        String jwt = Jwts.builder()
                .claim("id", userDto.getId())
                .claim("name", userDto.getName())
                .expiration(new Date(System.currentTimeMillis() + 30L * 24L * 60L * 60L * 1000L))
                .signWith(secretKey)
                .compact();

        return jwt;
    }



    public void validationJWT (String token, UUID userId) {
        token = token.replace("Bearer ", "");
        byte[] keyBytes = Decoders.BASE64.decode("servicesecretkeyservicesecretkeyservicesecretkey");
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        boolean isValid = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", String.class)
                .equals(userId.toString());

        if(!isValid){
            throw new RuntimeException("ошибка авторизации");
        }
    }
}
