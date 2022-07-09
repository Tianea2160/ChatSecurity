package com.knet.chatsecurity.domain.member.service;

import com.knet.chatsecurity.domain.member.Member;
import com.knet.chatsecurity.domain.member.MemberCredential;
import com.knet.chatsecurity.domain.member.dto.MemberDTO;
import com.knet.chatsecurity.domain.member.repository.MemberCredentialRepository;
import com.knet.chatsecurity.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberCredentialRepository memberCredentialRepository;
    private final PasswordEncoder passwordEncoder;

    public Long join(MemberDTO dto){
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        MemberCredential memberCredential = MemberCredential.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .build();

        MemberCredential savedMemberCredentials = memberCredentialRepository.save(memberCredential);

        Member member = Member.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .memberCredential(savedMemberCredentials)
                .build();

        return memberRepository.save(member).getId();
    }
}
