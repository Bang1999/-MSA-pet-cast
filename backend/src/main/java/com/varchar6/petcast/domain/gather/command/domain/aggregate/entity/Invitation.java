package com.varchar6.petcast.domain.gather.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_invitation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Invitation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "member_id")
    private int userId;

    @Column(name = "gather_id")
    private int gatherId;
}
