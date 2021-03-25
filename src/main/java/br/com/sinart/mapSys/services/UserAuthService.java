package br.com.sinart.mapSys.services;

import br.com.sinart.mapSys.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthService {
    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}
