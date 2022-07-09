package com.knet.chatsecurity.domain.member.repository;

import com.knet.chatsecurity.domain.member.MemberCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberCredentialRepository  extends JpaRepository<MemberCredential, Long> {
    Optional<MemberCredential> findByEmail(String email);
}
