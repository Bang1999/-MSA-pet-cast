package com.varchar6.petcast.domain.member.command.domain.repository;

import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMember;
import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMemberPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleMember, RoleMemberPk> {
}
