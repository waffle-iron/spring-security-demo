package ru.proshik.jalmew.word.configuration;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by proshik on 18.06.16.
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public AuthenticationToken(String token) {
        super(null);
        this.token = token;
    }

    public AuthenticationToken(Collection<? extends GrantedAuthority> authorities, String token) {
        super(authorities);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public String getToken() {
        return token;
    }
}
