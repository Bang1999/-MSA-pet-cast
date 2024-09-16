package com.varchar6.petcast.domain.notice.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_notice")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "view", nullable = false)
    private int view;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "fixed", nullable = false)
    private boolean fixed;
}
