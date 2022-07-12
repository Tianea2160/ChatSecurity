package com.knet.chatsecurity.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "member_credentials")
public class MemberCredential {
    @Id
    @Column(name = "member_uuid")
    private UUID uuid = UUID.randomUUID();

    @Column(unique = true)
    private String email;

    private String password;

    @Builder
    public MemberCredential(String email, String password) {
        this.email = email;
        this.password = password;
        this.uuid = UUID.randomUUID();
    }
}
