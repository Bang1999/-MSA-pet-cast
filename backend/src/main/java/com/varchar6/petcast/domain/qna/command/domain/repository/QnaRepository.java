package com.varchar6.petcast.domain.qna.command.domain.repository;

import com.varchar6.petcast.domain.qna.command.domain.aggregate.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Integer> {

}
