package com.knet.chatsecurity.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member extends TimeField {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_nickname")
    private String nickname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="member_uuid")
    private MemberCredential memberCredential;

    @Builder
    public Member(String name, String nickname, MemberCredential memberCredential) {
        this.name = name;
        this.nickname = nickname;
        this.memberCredential = memberCredential;
        this.setCreated_at(LocalDateTime.now());
        this.setLeaved_at(null);
    }
}
