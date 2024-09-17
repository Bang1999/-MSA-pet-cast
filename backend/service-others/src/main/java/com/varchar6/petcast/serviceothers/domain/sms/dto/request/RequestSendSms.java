package com.varchar6.petcast.serviceothers.domain.sms.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RequestSendSms {
    private int userId;
    private int gatherId;
    private String number;
    private String title;
    private String description;
}
