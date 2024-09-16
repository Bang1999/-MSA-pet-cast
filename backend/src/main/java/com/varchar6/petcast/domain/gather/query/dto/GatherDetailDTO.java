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
    private int number;
    private String url;
    private String updatedAt;
    private String createdAt;
    private boolean active;

    private List<String> members;
}
