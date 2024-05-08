package com.devmarcos.passguard.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.devmarcos.passguard.entities.User;

@Service
public class TokenService {

    // Substituir o valor por variáveis de ambiente
    private static final String ISSUER = "PASSGUARD";
    private static final String SECRET_WORD = "PASSGUARD";
    
    public String gerarToken(User user) {
        return JWT.create()
                  .withIssuer(ISSUER)
                  .withSubject(user.getUsername())
                  .withClaim("id", user.getId())
                  .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
                  .sign(Algorithm.HMAC256(SECRET_WORD));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_WORD))
                  .withIssuer(ISSUER)
                  .build()
                  .verify(token)
                  .getSubject();
    }

    public String getExpiresAt(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_WORD))
                  .withIssuer(ISSUER)
                  .build()
                  .verify(token)
                  .getExpiresAt()
                  .toString();
    }


}
