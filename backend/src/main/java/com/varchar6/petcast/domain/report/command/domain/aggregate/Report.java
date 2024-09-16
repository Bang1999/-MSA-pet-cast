package com.varchar6.petcast.domain.report.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_report")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "reporter_id", nullable = false)
    private int reporterId;

    @Column(name = "respondent_id", nullable = false)
    private int respondentId;


}
