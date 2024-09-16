package com.varchar6.petcast.domain.notice.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
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
