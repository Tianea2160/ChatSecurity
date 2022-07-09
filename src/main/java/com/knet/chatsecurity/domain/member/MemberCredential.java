package com.knet.chatsecurity.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "member_credentials")
@Entity
@NoArgsConstructor
public class MemberCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_uuid")
    private Long uuid;

    @Column(unique = true)
    private String email;

    private String password;

    @Builder
    public MemberCredential(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
