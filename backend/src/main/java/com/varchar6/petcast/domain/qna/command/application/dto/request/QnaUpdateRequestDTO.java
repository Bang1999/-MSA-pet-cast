package com.varchar6.petcast.domain.qna.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnaUpdateRequestDTO {
    private int id;
    private String answer;
    private Integer answererId;
}
