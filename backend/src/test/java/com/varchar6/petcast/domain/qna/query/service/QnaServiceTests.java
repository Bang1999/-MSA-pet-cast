package com.varchar6.petcast.domain.qna.query.service;

import com.varchar6.petcast.domain.qna.query.dto.QnaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaServiceTests {

    @Autowired
    private QnaService qnaService;

    @Test
    public void 고객_본인이_작성한_QnA_조회(){
        int targetQuestionerId = 7;
        List<QnaDTO> QnaDTOList = qnaService.getQnaByQuestionerId(targetQuestionerId);

        for(QnaDTO qna : QnaDTOList){
            assertEquals(qna.getQuestionerId(), targetQuestionerId);
        }
    }

    @Test
    public void 업체에_달린_QnA_조회(){
        int targetCompanyId = 1;
        List<QnaDTO> QnaDTOList = qnaService.getQnaByCompanyId(targetCompanyId);

        for(QnaDTO qna : QnaDTOList){
            assertEquals(qna.getCompanyId(), targetCompanyId);
        }
    }

    @Test
    public void QnA_상세_조회(){
        int targetId = 1;
        QnaDTO qnaDTO = qnaService.getQnaById(targetId);

        assertEquals(qnaDTO.getId(), targetId);
    }

}