package com.varchar6.petcast.serviceothers.domain.gather.command.domain.aggregate.entity;

import com.varchar6.petcast.serviceothers.domain.gather.command.domain.aggregate.GatherRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_group_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GatherMember {

    @Id
    @Column(name = "group_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "group_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private GatherRole role = GatherRole.LEADER;

    @Column(name = "member_id", nullable = false)
    private int memberId;

    @Column(name = "group_id", nullable = false)
    private int gatherId;
}
