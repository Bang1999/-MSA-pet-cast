package com.varchar6.petcast.serviceothers.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    // final 필드나 @NonNull 필드에 대해 생성자들을 자동 생성
public class CommonException extends RuntimeException{
    private final ErrorCode errorCode;

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
