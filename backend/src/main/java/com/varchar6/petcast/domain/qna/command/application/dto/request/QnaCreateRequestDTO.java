package com.varchar6.petcast.domain.qna.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnaCreateRequestDTO {
    private String title;
    private String content;
    private Integer questionerId;
    private Integer companyId;
}
