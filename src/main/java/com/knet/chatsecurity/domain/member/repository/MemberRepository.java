package com.knet.chatsecurity.domain.member.repository;

import com.knet.chatsecurity.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long> {

}
