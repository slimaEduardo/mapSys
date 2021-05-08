package br.com.sinart.mapSys.configuration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.sinart.mapSys.configuration.Constant.*;

public class TokenProvider {

    public String generateToken(Authentication authentication) {

        byte[] signingKey = JWT_SECRET.getBytes();
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(new Date())
                .setAudience(TOKEN_AUDIENCE)
                .setHeaderParam("typ", TOKEN_TYPE)
                .claim("username", authentication.getName().toLowerCase())
                .claim("roles", authorities)
                .setExpiration(new Date(new Date().getTime() + java.util.concurrent.TimeUnit.MINUTES.toMillis(EXPIRATION_TIME)))
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .compact();

        return token;
    }
}
