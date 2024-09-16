package com.varchar6.petcast.domain.qna.query.service;

import com.varchar6.petcast.domain.qna.query.dto.QnaDTO;
import com.varchar6.petcast.domain.qna.query.mapper.QnaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service(value="queryQnaService")
public class QnaServiceImpl implements QnaService{
    private final QnaMapper qnaMapper;

    @Autowired
    public QnaServiceImpl(QnaMapper qnaMapper) {
        this.qnaMapper = qnaMapper;
    }

    @Override
    @Transactional
    public List<QnaDTO> getQnaByQuestionerId(Integer questionerId){
        return qnaMapper.selectQnaByQuestionerId(questionerId);
    }

    @Override
    @Transactional
    public List<QnaDTO> getQnaByCompanyId(Integer companyId){
        return qnaMapper.selectQnaByCompanyId(companyId);
    }

    @Override
    @Transactional
    public QnaDTO getQnaById(Integer id){
        return qnaMapper.selectQnaById(id);
    }
}
