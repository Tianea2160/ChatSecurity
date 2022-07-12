package com.knet.chatsecurity.global.auth;

import com.knet.chatsecurity.global.auth.filter.AjaxAuthenticationProcessingFilter;
import com.knet.chatsecurity.global.auth.handler.AjaxAccessDeniedHandler;
import com.knet.chatsecurity.global.auth.handler.AjaxAuthenticationSuccessHandler;
import com.knet.chatsecurity.global.auth.manager.CustomAuthenticationManager;
import com.knet.chatsecurity.global.auth.manager.provider.AjaxAuthenticationProvider;
import com.knet.chatsecurity.global.auth.userdetails.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableRedisHttpSession
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetails();
    }

    @Bean
    public AjaxAuthenticationProcessingFilter ajaxAuthenticationProcessingFilter() throws Exception {
        AjaxAuthenticationProcessingFilter filter = new AjaxAuthenticationProcessingFilter();
        filter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public AjaxAuthenticationProvider ajaxAuthenticationProvider(){
        return new AjaxAuthenticationProvider();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new CustomAuthenticationManager();
    }

    @Bean
    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler(){
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AjaxAccessDeniedHandler ajaxAccessDeniedHandler(){
        return new AjaxAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/api/member/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                    .addFilterBefore(ajaxAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                    .userDetailsService(userDetailsService())
                    .authenticationProvider(ajaxAuthenticationProvider())
                .exceptionHandling()
                    .accessDeniedHandler(ajaxAccessDeniedHandler())
                .and()
                    .httpBasic(withDefaults());

        http.csrf().disable();

        return http.build();
    }
}
