package com.varchar6.petcast.domain.notice.command.domain.repository;

import com.varchar6.petcast.domain.notice.command.domain.aggregate.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
