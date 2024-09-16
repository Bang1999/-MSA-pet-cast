package com.varchar6.petcast.domain.qna.command.application.service;

import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.response.QnaResponseDTO;
import com.varchar6.petcast.domain.qna.command.domain.aggregate.Qna;
import com.varchar6.petcast.domain.qna.command.domain.repository.QnaRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service(value = "commandQnaService")
public class QnaServiceImpl implements QnaService{

    private final QnaRepository qnaRepository;
    private final ModelMapper modelMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public QnaServiceImpl(QnaRepository qnaRepository, ModelMapper modelMapper) {
        this.qnaRepository = qnaRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public int insertQna(QnaCreateRequestDTO qnaCreateRequestDTO) {
        int result = 0;

        Qna qna = modelMapper.map(qnaCreateRequestDTO, Qna.class);
        qna.setCreatedAt(LocalDateTime.now().format(FORMATTER));
        qna.setActive(true);
        qna.setAnswered(false);
        qna.setAnswererId(null);
        qna.setAnswer("");

        try {
            qnaRepository.save(qna);
            result++;
        }catch(Exception e){
            throw new RuntimeException("qna 입력 실패");
        }

        return result;
    }

    @Override
    @Transactional
    public QnaResponseDTO updateQna(QnaUpdateRequestDTO qnaUpdateRequestDTO) {
        Qna qna = qnaRepository.findById(qnaUpdateRequestDTO.getId()).orElse(null);

        qna = modelMapper.map(qnaUpdateRequestDTO, Qna.class);
        qna.setAnsweredAt(LocalDateTime.now().format(FORMATTER));
        qna.setAnswered(true);

        QnaResponseDTO qnaResponseDTO = modelMapper.map(qna, QnaResponseDTO.class);

        return qnaResponseDTO;
    }

    @Override
    @Transactional
    public QnaResponseDTO deleteQnaAnswer(QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO) {
        Qna qna = qnaRepository.findById(qnaDeleteAnswerRequestDTO.getId()).orElse(null);

        /* 설명. 답변자 아이디와 db의 아이디와 비교 */
        if (qna != null && qna.getAnswererId() != qnaDeleteAnswerRequestDTO.getAnswererId()) {
            throw new IllegalArgumentException("검증 실패: 업체에 등록된 아이디와 일치하지 않습니다.");
        }

        qna.setAnswer("");

        QnaResponseDTO qnaResponseDTO = modelMapper.map(qna, QnaResponseDTO.class);

        return qnaResponseDTO;
    }

    @Override
    public int setQnaActive(int id) {
        int result = 0;

        try {
            qnaRepository.deleteById(id);
            result++;
        } catch (Exception e) {
            throw new RuntimeException("qna 삭제 실패");
        }

        return result;
    }

}
