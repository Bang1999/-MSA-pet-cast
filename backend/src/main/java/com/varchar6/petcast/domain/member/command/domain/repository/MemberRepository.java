package com.varchar6.petcast.domain.member.command.domain.repository;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
