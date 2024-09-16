package com.varchar6.petcast.domain.event.command.domain.repository;

import com.varchar6.petcast.domain.event.command.domain.aggregate.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM Event e ORDER BY e.id DESC LIMIT 1")
    Event findLatestEvent();
}
