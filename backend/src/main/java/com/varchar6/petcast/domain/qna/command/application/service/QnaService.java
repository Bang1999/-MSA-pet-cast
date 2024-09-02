package com.varchar6.petcast.domain.qna.command.application.service;

import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaSetActiveRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.response.QnaResponseDTO;

public interface QnaService {
    int insertQna(QnaCreateRequestDTO qnaCreateRequestDTO);
    QnaResponseDTO updateQna(QnaUpdateRequestDTO qnaUpdateRequestDTO);
    QnaResponseDTO setQnaActive(QnaSetActiveRequestDTO qnaSetActiveRequestDTO);
}
