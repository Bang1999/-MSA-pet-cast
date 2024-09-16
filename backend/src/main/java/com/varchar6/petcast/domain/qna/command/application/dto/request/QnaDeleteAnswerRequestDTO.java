package com.varchar6.petcast.domain.qna.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnaDeleteAnswerRequestDTO {
    private Integer companyId;
    private int id;
    /* 설명. answererId를 통해서 companyId의 memberId에 속한지 알 수 있다.*/
    private Integer answererId;
}
