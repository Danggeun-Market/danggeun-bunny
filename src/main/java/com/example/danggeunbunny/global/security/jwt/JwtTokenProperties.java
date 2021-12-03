package com.example.danggeunbunny.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Base64;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtTokenProperties {

    private final String secret;
    private final String header;
    private final String prefix;
    private final Long access;
    private final Long refresh;

    public JwtTokenProperties(String secret, Long access, Long refresh,
                         String header, String prefix) {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.access = access;
        this.refresh = refresh;
        this.header = header;
        this.prefix = prefix;
    }
}