package com.varchar6.petcast.serviceothers.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    public ExceptionResponse(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
