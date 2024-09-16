package com.varchar6.petcast.domain.gather.command.application.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseSendInvitaionDTO {
    private int userId;
    private int gatherId;
}
