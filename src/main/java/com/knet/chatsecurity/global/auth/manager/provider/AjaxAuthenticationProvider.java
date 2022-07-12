package com.knet.chatsecurity.global.auth.manager.provider;

import com.knet.chatsecurity.global.auth.Role;
import com.knet.chatsecurity.global.auth.token.AjaxAuthenticationToken;
import com.knet.chatsecurity.global.auth.userdetails.MemberDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("provider in");

        String email = authentication.getName();
        String password = (String) authentication.getPrincipal();

        MemberDetails memberDetails = (MemberDetails) userDetailsService.loadUserByUsername(email);

        // password confirm
        if(!passwordEncoder.matches(password, memberDetails.getPassword())){
            throw new BadCredentialsException("password is not matched");
        }

        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(Role.USER.getKey()));

        AjaxAuthenticationToken token = new AjaxAuthenticationToken(email,null, auth);
        log.info("provider out");
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AjaxAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
