package com.varchar6.petcast.serviceothers.domain.gather.command.application.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseSendInvitaionDTO {
    private String messageId;
    private String country;
    private String from;
    private String to;
    private String statusCode;
}
