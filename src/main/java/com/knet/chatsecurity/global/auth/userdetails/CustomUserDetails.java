package com.knet.chatsecurity.global.auth.userdetails;

import com.knet.chatsecurity.domain.member.MemberCredential;
import com.knet.chatsecurity.domain.member.repository.MemberCredentialRepository;
import com.knet.chatsecurity.domain.member.repository.MemberRepository;
import com.knet.chatsecurity.global.auth.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private MemberCredentialRepository memberCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("userDetailsService in");
        MemberCredential member = memberCredentialRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾지 못하였습니다."));

        List<GrantedAuthority> auth =  new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(Role.USER.getKey()));

        MemberDetails memberDetails = new MemberDetails(member.getEmail(), member.getPassword(), auth);
        log.info("userDetailsService out");
        return memberDetails;
    }
}
