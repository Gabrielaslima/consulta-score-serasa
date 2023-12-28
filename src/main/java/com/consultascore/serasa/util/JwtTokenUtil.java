package com.consultascore.serasa.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {

    @Value("${JwtExpirationMs}")
    private int JwtExpirationMs;

    @Value("${JwtSecret}")
    private String JwtSecret;

    /**
     * Metodo responsavel por gerar o token JWT
     * */
    public String generateToken(Long idUser) {
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(idUser.toString())
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, JwtSecret).compact();

    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtSecret));
    }

    /**
     * Metodo responsavel por validar o token JWT com base na secret
     * */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * Metodo responsavel por retornar o ID do User
     */
    public String getSubjectFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

}
