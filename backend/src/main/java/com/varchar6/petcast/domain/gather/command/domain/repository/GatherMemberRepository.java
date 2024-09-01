package com.varchar6.petcast.domain.gather.command.domain.repository;

import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMember;
import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.GatherMemberFK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatherMemberRepository extends JpaRepository<GatherMember, GatherMemberFK> {
}
