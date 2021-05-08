package br.com.sinart.mapSys.resources.auth;

import br.com.sinart.mapSys.configuration.security.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static br.com.sinart.mapSys.configuration.Constant.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<String> refreshToken(HttpServletResponse response) {
        String token = new TokenProvider().generateToken(SecurityContextHolder.getContext().getAuthentication());

        response.addHeader(HEADER_EXPOSE, HEADER_STRING);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
}
