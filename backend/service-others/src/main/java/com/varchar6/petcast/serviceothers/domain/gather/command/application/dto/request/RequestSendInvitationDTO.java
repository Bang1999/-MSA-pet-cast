package com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestSendInvitationDTO {
    private int userId;
    private int gatherId;
    private int invitationId;
    private String number;
    private String title;
    private String description;
}
