package com.varchar6.petcast.domain.qna.query.service;

import com.varchar6.petcast.domain.qna.query.dto.QnaDTO;

import java.util.List;

public interface QnaService {
    List<QnaDTO> getQnaByQuestionerId(Integer questionerId);

    List<QnaDTO> getQnaByCompanyId(Integer companyId);

    QnaDTO getQnaById(Integer id);
}
