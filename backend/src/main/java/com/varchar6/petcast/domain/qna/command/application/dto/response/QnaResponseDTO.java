package com.varchar6.petcast.domain.qna.command.application.dto.response;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnaResponseDTO {
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
