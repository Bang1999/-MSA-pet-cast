package com.varchar6.petcast.domain.request.command.domain.aggregate.entity;

import com.varchar6.petcast.domain.request.command.domain.aggregate.RequestsStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_request")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "hope_cost")
    private int hopeCost = 0;

    @Column(name = "hope_location", nullable = false)
    private String hopeLocation;

    @Column(name = "hope_time")
    private String hopeTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestsStatus status;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "company_id", nullable = false)
    private int companyId;

    @Column(name = "member_id", nullable = false)
    private int memberId;


}
