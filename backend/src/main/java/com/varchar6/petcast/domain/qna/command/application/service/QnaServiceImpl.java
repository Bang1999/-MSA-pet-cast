package com.varchar6.petcast.domain.qna.command.application.service;

import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaSetActiveRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.domain.qna.command.application.dto.response.QnaResponseDTO;
import com.varchar6.petcast.domain.qna.command.domain.aggregate.Qna;
import com.varchar6.petcast.domain.qna.command.domain.repository.QnaRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

        try {
            qnaRepository.save(qna);
            result++;
        }catch(Exception e){
            log.info("qna 생성 실패");
        }

        return result;
    }

    @Override
    @Transactional
    public QnaResponseDTO updateQna(QnaUpdateRequestDTO qnaUpdateRequestDTO) {
        Qna qna = qnaRepository.findById(qnaUpdateRequestDTO.getId()).orElse(null);

        /* 설명.
         * answererId와 tbl_company의 Id == companyId 조회해서 해당하는 memberId조회
         * 가져오는 법을 까먹었다. 그냥 모르는 거 였다
        * */

//        CompanyDTO companyDTO = companyClient.getCompanyById(qnaUpdateRequestDTO.getCompanyId());
//
//        // 데이터 검증: Company의 memberId와 answererId 비교
//        if (!companyDTO.getMemberId().equals(qnaUpdateRequestDTO.getAnswererId())) {
//            throw new IllegalArgumentException("검증 실패: Company의 memberId와 AnswererId가 일치하지 않습니다.");
//        }

        qna = modelMapper.map(qnaUpdateRequestDTO, Qna.class);
        qna.setAnsweredAt(LocalDateTime.now().format(FORMATTER));
        qna.setAnswered(true);

        QnaResponseDTO qnaResponseDTO = modelMapper.map(qna, QnaResponseDTO.class);

        return qnaResponseDTO;
    }

    @Override
    @Transactional
    public QnaResponseDTO setQnaActive(QnaSetActiveRequestDTO qnaSetActiveRequestDTO) {
        Qna qna = qnaRepository.findById(qnaSetActiveRequestDTO.getId()).orElse(null);

        /* 설명. 답변자 아이디와 db의 아이디와 비교 */
        if (qna != null && qna.getAnswererId() != qnaSetActiveRequestDTO.getAnswererId()) {
            throw new IllegalArgumentException("검증 실패: 업체에 등록된 아이디와 일치하지 않습니다.");
        }

        qna.setAnswer("");

        QnaResponseDTO qnaResponseDTO = modelMapper.map(qna, QnaResponseDTO.class);

        return qnaResponseDTO;
    }
}
