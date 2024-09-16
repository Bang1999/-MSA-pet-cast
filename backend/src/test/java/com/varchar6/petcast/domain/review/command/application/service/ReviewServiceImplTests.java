package com.varchar6.petcast.domain.review.command.application.service;

import com.varchar6.petcast.domain.review.command.application.dto.request.ReviewCreateRequestDTO;
import com.varchar6.petcast.domain.review.command.application.dto.request.ReviewUpdateRequestDTO;
import com.varchar6.petcast.domain.review.command.application.dto.response.ReviewResponseDTO;
import com.varchar6.petcast.domain.review.command.domain.aggregate.entity.Review;
import com.varchar6.petcast.domain.review.command.domain.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewServiceImplTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        reviewService = new ReviewServiceImpl(reviewRepository, modelMapper);
    }

    @Test
    void testInsertReview() {
        ReviewCreateRequestDTO reviewCreateRequestDTO = new ReviewCreateRequestDTO();
        reviewCreateRequestDTO.setComment("테스트용 comment");
        reviewCreateRequestDTO.setTitle("테스트용 title");
        reviewCreateRequestDTO.setEventId(3);
        reviewCreateRequestDTO.setScore(5);

        reviewService.insertReview(reviewCreateRequestDTO);

        // 실제로 저장되었는지 확인
        Review review = reviewRepository.findLatestReview();
        assertNotNull(review);
        assertEquals("테스트용 comment", review.getComment());
        assertEquals("테스트용 title", review.getTitle());
        assertEquals(5, review.getScore());
        assertEquals(3, review.getEventId());
    }

    @Test
    void testUpdateReview() {
        ReviewUpdateRequestDTO reviewUpdateRequestDTO = new ReviewUpdateRequestDTO();
        reviewUpdateRequestDTO.setId(2);
        reviewUpdateRequestDTO.setComment("테스트용 수정된 comment");
        reviewUpdateRequestDTO.setTitle("테스트용 수정된 title");
        reviewUpdateRequestDTO.setScore(4);

        ReviewResponseDTO responseDTO = reviewService.updateReview(reviewUpdateRequestDTO);

        // 업데이트된 리뷰 검증
        assertNotNull(responseDTO);
        assertEquals("테스트용 수정된 comment", responseDTO.getComment());
        assertEquals("테스트용 수정된 title", responseDTO.getTitle());
        assertEquals(4, responseDTO.getScore());
    }

    @Test
    void testDeleteReview() {
        // 리뷰를 미리 저장
        Review review = new Review();
        review.setComment("테스트용 삭제될 comment");
        review.setTitle("테스트용 삭제될 title");
        review.setScore(2);
        review.setEventId(3);
        Review savedReview = reviewRepository.save(review);

        reviewService.deleteReview(savedReview.getId());

        // 삭제되었는지 확인
        Review deletedReview = reviewRepository.findById(savedReview.getId()).orElse(null);
        assertNull(deletedReview);
    }

}