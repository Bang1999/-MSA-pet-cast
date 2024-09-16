package com.varchar6.petcast.domain.gather.command.application.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseDeleteMemberDTO {
    private int userId;
    private int gatherId;
    private int memberId;
}
