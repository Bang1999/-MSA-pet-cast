package com.varchar6.petcast.domain.qna.query.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QnaDTO {
    private int id;
    private String title;
    private String content;
    private String createdAt;
    private String answeredAt;
    private String answer;
    private boolean answered;
    private boolean active;
    private Integer companyId;
    private Integer questionerId;
    private Integer answererId;
}
