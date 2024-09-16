package com.varchar6.petcast.common.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResponseMessage {
    private int httpStatus;
    private String message;
    private Object result;
}
