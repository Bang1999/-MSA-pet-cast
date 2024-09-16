package com.varchar6.petcast.domain.request.command.domain.repository;

import com.varchar6.petcast.domain.request.command.domain.aggregate.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Integer> {
}
