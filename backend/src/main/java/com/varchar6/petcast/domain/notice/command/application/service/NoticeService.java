package com.varchar6.petcast.domain.notice.command.application.service;

import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeUpdateRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.response.NoticeResponseDTO;

import java.util.List;


public interface NoticeService {
    int insertNotice(NoticeWriteRequestDTO noticeWriteRequestDTO);
    NoticeResponseDTO updateNotice(NoticeUpdateRequestDTO noticeUpdateRequestDTO);
    int deleteNotice(int noticeId);

}
