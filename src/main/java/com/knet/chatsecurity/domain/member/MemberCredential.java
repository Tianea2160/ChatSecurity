package com.knet.chatsecurity.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "member_credential")
public class MemberCredential {

    @Id
    @Column(name = "member_credential_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_uuid", unique = true)
    private UUID uuid = UUID.randomUUID();

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public MemberCredential(String email, String password) {
        this.email = email;
        this.password = password;
        this.uuid = UUID.randomUUID();
    }
}
