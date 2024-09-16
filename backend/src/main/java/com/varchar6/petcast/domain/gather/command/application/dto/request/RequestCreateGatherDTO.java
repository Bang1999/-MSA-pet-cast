package com.varchar6.petcast.domain.gather.command.application.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestCreateGatherDTO {
    private String name;
    private String content;
    private int number;
    private String url;
    private boolean active;

    private int userId;
}
