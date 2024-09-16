package com.varchar6.petcast.domain.gather.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class GatherDTO {
    private int id;
    private String name;
    private String content;
    private int number;
    private String url;
    private String updatedAt;
    private String createdAt;
    private boolean active;
}
