package com.knet.chatsecurity.global.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import java.util.Collection;

public class AjaxAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;
    private Object credentials;

    // 인증 성공 전
    public AjaxAuthenticationToken(Object principal,Object credentials) {
        super(null);
        this.credentials = credentials;
        this.principal = principal;
        setAuthenticated(false);
    }

    // 인증 성공 후
    public AjaxAuthenticationToken(Object principal,Object credentials,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.credentials = credentials;
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
