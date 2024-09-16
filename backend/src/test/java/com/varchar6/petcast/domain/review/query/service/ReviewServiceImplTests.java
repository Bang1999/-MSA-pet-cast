package com.varchar6.petcast.domain.review.query.service;

import com.varchar6.petcast.domain.review.command.domain.aggregate.entity.Review;
import com.varchar6.petcast.domain.review.command.domain.repository.ReviewRepository;
import com.varchar6.petcast.domain.review.query.dto.ReviewDTO;
import com.varchar6.petcast.domain.review.query.mapper.ReviewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReviewServiceImplTests {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        reviewService = new ReviewServiceImpl(reviewMapper);
    }

    @Test
    void testFindReviewByCompany() {
        Integer companyId = 1;
        Integer lastReviewId = 0;

        Review review = new Review();
        review.setTitle("테스트용 조회될 title");
        review.setComment("테스트용 조회될 comment");
        review.setScore(2);
        review.setEventId(3);
        reviewRepository.save(review);

        List<ReviewDTO> reviews = reviewService.findReviewByCompany(companyId, lastReviewId);

        assertNotNull(reviews);
        assertEquals("테스트용 조회될 title", reviews.get(0).getTitle());
        assertEquals("테스트용 조회될 comment", reviews.get(0).getComment());
        assertEquals(2, reviews.get(0).getScore());
        assertEquals(3, reviews.get(0).getEventId());
    }

    @Test
    void testFindEventByCustomer() {
        Integer userId = 1;
        Integer lastReviewId = 0;

        Review review = new Review();
        review.setTitle("테스트용 조회될 title");
        review.setComment("테스트용 조회될 comment");
        review.setScore(2);
        review.setEventId(3);
        reviewRepository.save(review);

        List<ReviewDTO> reviews = reviewService.findEventByCustomer(userId, lastReviewId);

        assertNotNull(reviews);
        assertEquals("테스트용 조회될 title", reviews.get(0).getTitle());
        assertEquals("테스트용 조회될 comment", reviews.get(0).getComment());
        assertEquals(2, reviews.get(0).getScore());
        assertEquals(3, reviews.get(0).getEventId());
    }


}
