package com.varchar6.petcast.serviceothers.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /*
    * 400(Bad Request)
    *   이 응답은 잘못된 문법으로 인하여 서버가 요청을 이해할 수 없음을 의미합니다.
    * */
    WRONG_ENTRY_POINT(40000, HttpStatus.BAD_REQUEST, "잘못된 접근입니다."),
    DATA_INTEGRITY_VIOLATION(40001, HttpStatus.BAD_REQUEST,
            "데이터 무결성 위반입니다. 필수 값이 누락되었거나 유효하지 않습니다."),
    NOT_LEADER(40002, HttpStatus.BAD_REQUEST, "리더가 아닙니다."),
    INVALID_PARAMETER_FORMAT(40003, HttpStatus.BAD_REQUEST, "요청에 유효하지 않은 인자 형식입니다."),
    MISSING_REQUEST_PARAMETER(40004, HttpStatus.BAD_REQUEST, "필수 요청 파라미터가 누락되었습니다."),

    /*
     * 401(Unauthorized)
     *   비록 HTTP 표준에서는 "미승인(unauthorized)"를 명확히 하고 있지만,
     *   의미상 이 응답은 "비인증(unauthenticated)"을 의미합니다.
     *   클라이언트는 요청한 응답을 받기 위해서는 반드시 스스로를 인증해야 합니다.
     * */

    /*
     * 404(Not Found)
     *   서버는 요청받은 리소스를 찾을 수 없습니다. 브라우저에서는 알려지지 않은 URL을 의미합니다.
     *   이것은 API에서 종점은 적절하지만 리소스 자체는 존재하지 않음을 의미할 수도 있습니다.
     *   서버들은 인증받지 않은 클라이언트로부터 리소스를 숨기기 위하여 이 응답을 403 대신에 전송할 수도 있습니다.
     *   이 응답 코드는 웹에서 반복적으로 발생하기 때문에 가장 유명할지도 모릅니다.
     * */
    /* Gather Command */
    NOT_FOUND_MEMBER_ROLE(40401, HttpStatus.NOT_FOUND, "해당 멤버의 역할이 존재하지 않습니다."),
    NOT_FOUND_GATHER(40402, HttpStatus.NOT_FOUND, "해당 모임이 존재하지 않습니다."),
    NOT_FOUND_GATHER_DETAIL(40403, HttpStatus.NOT_FOUND, "해당 모임의 정보가 존재하지 않습니다."),
    NOT_FOUND_GATHER_MEMBER(40408, HttpStatus.NOT_FOUND, "모임 구성원 아이디를 찾지 못하였습니다."),

    /* Gather Query */
    NOT_FOUND_GATHER_LIST(40404, HttpStatus.NOT_FOUND, "모임 목록 조회에 실패하였습니다."),
    NOT_FOUND_GATHER_ACTIVE(40405, HttpStatus.NOT_FOUND, "해당 모임의 활성화 상태 조회에 실패하였습니다."),
    NOT_FOUND_GATHER_MEMBER_LIST(40406, HttpStatus.NOT_FOUND, "해당 모임의 모임원이 존재하지 않습니다."),
    NOT_FOUND_INVITATION(40407, HttpStatus.NOT_FOUND, "초대장을 찾을 수 없습니다."),
    /*
     * 500(Internal Server Error)
     *   서버가 처리 방법을 모르는 상황이 발생했습니다. 서버는 아직 처리 방법을 알 수 없습니다.
     * */
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
