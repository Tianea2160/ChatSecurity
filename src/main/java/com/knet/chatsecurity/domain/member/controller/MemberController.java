package com.knet.chatsecurity.domain.member.controller;

import com.knet.chatsecurity.domain.member.dto.MemberDTO;
import com.knet.chatsecurity.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public Long join(@RequestBody MemberDTO dto){
        return memberService.join(dto);
    }
}
