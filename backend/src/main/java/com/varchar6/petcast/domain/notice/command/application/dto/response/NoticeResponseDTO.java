package com.varchar6.petcast.domain.notice.command.application.dto.response;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponseDTO {
    private int id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private int view;
    private int memberId;
    private boolean fixed;
}
