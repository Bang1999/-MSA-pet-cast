package com.varchar6.petcast.domain.member.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_member_role")
@IdClass(RoleMemberPk.class)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMember {
    @Id
    @Column(name = "member_id")
    private int memberId;

    @Id
    @Column(name = "role_id")
    private int roleId;
}
