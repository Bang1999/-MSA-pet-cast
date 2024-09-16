package com.varchar6.petcast.domain.gather.command.domain.repository;

import com.varchar6.petcast.domain.gather.command.domain.aggregate.entity.Gather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatherRepository extends JpaRepository<Gather, Integer> {
}
