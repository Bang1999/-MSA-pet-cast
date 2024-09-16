package com.varchar6.petcast.domain.review.query.service;

import com.varchar6.petcast.domain.review.query.dto.ReviewDTO;
import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findReviewByCompany(Integer companyId, Integer lastReviewId);

    List<ReviewDTO> findEventByCustomer(Integer userId, Integer lastReviewId);
}
