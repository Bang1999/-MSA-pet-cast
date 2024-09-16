package com.varchar6.petcast.domain.review.query.mapper;

import com.varchar6.petcast.domain.review.query.dto.ReviewDTO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    List<ReviewDTO> selectReviewByCompanyId(Map<String, String> params);

    List<ReviewDTO> selectReviewByCustomerId(Map<String, String> params);
}
