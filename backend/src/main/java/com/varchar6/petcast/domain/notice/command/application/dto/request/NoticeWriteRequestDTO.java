package com.varchar6.petcast.domain.notice.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeWriteRequestDTO {
    private String title;
    private String content;
    private int memberId;
    private boolean fixed;
}
