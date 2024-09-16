package com.varchar6.petcast.domain.qna.query.mapper;

import com.varchar6.petcast.domain.qna.query.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
    List<QnaDTO> selectQnaByQuestionerId(Integer questionerId);

    List<QnaDTO> selectQnaByCompanyId(Integer companyId);

    QnaDTO selectQnaById(Integer id);
}
