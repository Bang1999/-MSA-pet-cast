package com.varchar6.petcast.domain.qna.command.application.service;

import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.response.QnaResponseDTO;
import com.varchar6.petcast.domain.qna.command.domain.aggregate.Qna;
import com.varchar6.petcast.domain.qna.command.domain.repository.QnaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaServiceTests {

    private final ModelMapper modelMapper;
    private final QnaService qnaService;
    private final QnaRepository qnaRepository;

    private static QnaCreateRequestDTO qnaCreateRequestDTO = new QnaCreateRequestDTO();
    private static QnaUpdateRequestDTO qnaUpdateRequestDTO = new QnaUpdateRequestDTO();
    private static QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO = new QnaDeleteAnswerRequestDTO();

    @Autowired
    public QnaServiceTests(ModelMapper modelMapper, QnaService qnaService, QnaRepository qnaRepository) {
        this.modelMapper = modelMapper;
        this.qnaService = qnaService;
        this.qnaRepository = qnaRepository;
    }

    @Test
    @Transactional
    public void 질문_작성_테스트(){
        qnaCreateRequestDTO.setTitle("뭐가 문제인건가요??");
        qnaCreateRequestDTO.setContent("흠...");
        qnaCreateRequestDTO.setQuestionerId(8);
        qnaCreateRequestDTO.setCompanyId(7);


        int result = qnaService.insertQna(qnaCreateRequestDTO);
        assertEquals(1,result);
    }

    @Test
    @Transactional
    public void 답변_작성_테스트(){
        qnaUpdateRequestDTO.setId(23);
        qnaUpdateRequestDTO.setAnswer("저희도 모릅니다");
        qnaUpdateRequestDTO.setAnswererId(3);

        QnaResponseDTO qnaResponseDTO = qnaService.updateQna(qnaUpdateRequestDTO);

        assertEquals(23,qnaResponseDTO.getId());
    }

    @Test
    @Transactional
    public void 답변_삭제_테스트(){
        qnaDeleteAnswerRequestDTO.setId(22);
        qnaDeleteAnswerRequestDTO.setCompanyId(22);
        qnaDeleteAnswerRequestDTO.setAnswererId(32);

        QnaResponseDTO qnaResponseDTO = qnaService.deleteQnaAnswer(qnaDeleteAnswerRequestDTO);

        assertEquals("", qnaResponseDTO.getAnswer());
    }

    @Test
    @Transactional
    public void 질문_답변_비활성화_테스트(){
        int result =0;
        result = qnaService.setQnaActive(21);

        assertEquals(1,result);
    }
}