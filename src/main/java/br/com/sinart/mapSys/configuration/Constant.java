package br.com.sinart.mapSys.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {
    public static final long EXPIRATION_TIME = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    public static final String HEADER_STRING = "Authorization";
    public static final String JWT_SECRET = "mYq3s6v9y34&E)H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPe";
    public static final String HEADER_EXPOSE = "Access-Control-Expose-Headers";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
    public static final String TOKEN_PREFIX = "Bearer ";
}
