package br.com.alura.mvc.mudi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value ("${mudi.jwt.expiration}")
    private String expiration;
    @Value ("${mudi.jwt.secret}")
    private String secret;


    public String buildToken(Authentication auth) {
        User username = (User) auth.getPrincipal();
        Date hoje = new Date();
        Date dataExp = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Api do MooLite!")
                .setSubject(username.getUsername())
                .setIssuedAt(hoje)
                .setExpiration(dataExp)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

}
