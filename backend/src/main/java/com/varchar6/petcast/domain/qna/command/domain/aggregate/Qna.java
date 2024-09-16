package com.varchar6.petcast.domain.qna.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_qna")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Qna {
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

    @Column(name = "answered_at")
    private String answeredAt;

    @Column(name = "answer")
    private String answer;

    @Column(name = "answered", nullable = false)
    private boolean answered;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "questioner_id", nullable = false)
    private Integer questionerId;

    @Column(name = "answerer_id")
    private Integer answererId;
}
