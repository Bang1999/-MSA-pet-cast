package com.varchar6.petcast.domain.request.command.application.service;

import com.varchar6.petcast.domain.request.command.application.dto.request.CreateRequestsRequestDTO;

public interface RequestsService {
    // 요청서 작성
    void createRequest(CreateRequestsRequestDTO createRequestsRequestDTO, int memberId);

    //  요청서 삭제
    void deleteRequest(int requestId, int memberId);

    // 요청서 수락
    void acceptRequest(int requestId);

    // 요청서 거절
    void rejectRequest(int requestId);
}