package com.varchar6.petcast.domain.review.command.domain.repository;

import com.varchar6.petcast.domain.review.command.domain.aggregate.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Optional<Review> findById(int id);

    void deleteById(int id);

    @Query("SELECT r FROM Review r ORDER BY r.id DESC LIMIT 1")
    Review findLatestReview();
}
