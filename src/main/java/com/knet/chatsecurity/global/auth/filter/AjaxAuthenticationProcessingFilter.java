package com.knet.chatsecurity.global.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knet.chatsecurity.global.auth.dto.LoginDTO;
import com.knet.chatsecurity.global.auth.token.AjaxAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AjaxAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final String X_Http_requested = "X-Http-Requested";
    private static final String xmlHttpRequested = "XMLHttpRequested";

    @Autowired
    private ObjectMapper objectMapper;

    public  AjaxAuthenticationProcessingFilter() {
        super("/login");
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException
    {
        log.info("AjaxAuthenticationProcessingFilter in");

        // Is ajax?
        if(!isAjax(request)){
            throw new IllegalArgumentException("there is a not ajax request");
        }

        LoginDTO dto = objectMapper.readValue(request.getReader(), LoginDTO.class);

        // 값이 잘 전달 되었는지
        if(dto == null || dto.getEmail() == null || dto.getPassword() == null){
            throw new IllegalArgumentException("there is a no email or password");
        }

        AjaxAuthenticationToken token = new AjaxAuthenticationToken(dto.getEmail(), dto.getPassword(), null);
        log.info("AjaxAuthenticationProcessingFilter out");
        return getAuthenticationManager().authenticate(token);
    }

    private boolean isAjax(HttpServletRequest request) {
        return true;
    }
}
