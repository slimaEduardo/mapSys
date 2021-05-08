package br.com.sinart.mapSys.configuration.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static br.com.sinart.mapSys.configuration.Constant.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = this.getAuthentication(request);
        String header = request.getHeader(HEADER_STRING);
        if (ObjectUtils.isEmpty(header) || !header.startsWith(TOKEN_PREFIX)) {
            filter.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filter.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (!ObjectUtils.isEmpty(token)) {
            try {
                byte[] signingKey = JWT_SECRET.getBytes();

                Jws<Claims> parsedToken = Jwts
                        .parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""));

                String username = parsedToken.getBody().get("username", String.class);
                ArrayList authorities = parsedToken.getBody().get("roles", ArrayList.class);

                ArrayList<SimpleGrantedAuthority> auth = new ArrayList<>();
                for (Object obj : authorities) {
                    auth.add(new SimpleGrantedAuthority(obj.toString()));
                }

                if (!ObjectUtils.isEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, auth);
                }
            } catch (SignatureException ex) {
                log.error("Invalid JWT signature - {}", ex.getMessage());
            } catch (MalformedJwtException ex) {
                log.error("Invalid JWT token - {}", ex.getMessage());
            } catch (ExpiredJwtException ex) {
                log.error("Expired JWT token - {}", ex.getMessage());
            } catch (UnsupportedJwtException ex) {
                log.error("Unsupported JWT token - {}", ex.getMessage());
            } catch (IllegalArgumentException ex) {
                log.error("JWT claims string is empty - {}", ex.getMessage());
            }
        }
        return null;
    }
}
