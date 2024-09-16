package com.varchar6.petcast.domain.gather.command.application.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ResponseCreateGatherDTO {
    private String name;
    private String content;
    private int number;
    private String url;
    private boolean active;
    private int userId;
}
