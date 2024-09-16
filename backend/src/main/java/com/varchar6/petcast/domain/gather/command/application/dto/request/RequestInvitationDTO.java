package com.varchar6.petcast.domain.gather.command.application.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestInvitationDTO {
    private int userId;
    private int invitationId;
}
