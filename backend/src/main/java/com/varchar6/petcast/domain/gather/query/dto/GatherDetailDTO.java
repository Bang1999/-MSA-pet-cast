package com.varchar6.petcast.domain.gather.query.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class GatherDetailDTO {
    private int id;
    private String name;
    private String content;
    private int count;
    private String url;
    private String updatedAt;
    private String createdAt;
    private boolean activeYn;
    private String invitationId;
    private String intitationContent;

    private List<String> members;
}
