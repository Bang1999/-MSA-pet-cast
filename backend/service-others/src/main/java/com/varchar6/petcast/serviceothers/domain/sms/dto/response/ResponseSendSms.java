package com.varchar6.petcast.serviceothers.domain.sms.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ResponseSendSms {
    private String messageId;
    private String country;
    private String from;
    private String to;
    private String statusCode;
}
