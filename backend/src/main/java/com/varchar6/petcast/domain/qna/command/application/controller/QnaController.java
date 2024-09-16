package com.varchar6.petcast.domain.qna.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.response.QnaResponseDTO;
import com.varchar6.petcast.domain.qna.command.application.service.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController(value = "commandQnaController")
@RequestMapping("/api/v1/qna")
public class QnaController {

    private final QnaService qnaService;

    @Autowired
    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("")
    private ResponseEntity<ResponseMessage> createQna(@RequestBody QnaCreateRequestDTO qnaCreateRequestDTO,
                                                      @RequestAttribute("memberId") int memberId){

        qnaCreateRequestDTO.setQuestionerId(memberId);
        int result = qnaService.insertQna(qnaCreateRequestDTO);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Q&A 생성 성공")
                                .result(true)
                                .build()
                );
    }

    @PutMapping("")
    private ResponseEntity<ResponseMessage> updateAnswer(@RequestBody QnaUpdateRequestDTO qnaUpdateRequestDTO,
                                                         @RequestAttribute("memberId") int memberId){

        qnaUpdateRequestDTO.setAnswererId(memberId);
        QnaResponseDTO qnaResponseDTO = qnaService.updateQna(qnaUpdateRequestDTO);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Q&A 수정 성공")
                                .result(true)
                                .build()
                );
    }

    @DeleteMapping("")
    private ResponseEntity<ResponseMessage> setQnaActive(@RequestBody Map<String, Integer> request){
        int id = request.get("id");
        int result = qnaService.setQnaActive(id);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Q&A 삭제 성공")
                                .result(true)
                                .build()
                );
    }
    @PutMapping("/answer")
    private ResponseEntity<ResponseMessage> deleteQnaAnswer(@RequestBody QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO,
                                                            @RequestAttribute("memberId") int memberId){

        qnaDeleteAnswerRequestDTO.setAnswererId(memberId);
        QnaResponseDTO qnaResponseDTO = qnaService.deleteQnaAnswer(qnaDeleteAnswerRequestDTO);

        return ResponseEntity
                .ok()
                .body(
                        ResponseMessage.builder()
                                .httpStatus(HttpStatus.CREATED.value())
                                .message("Q&A 수정 성공")
                                .result(true)
                                .build()
                );
    }
}
