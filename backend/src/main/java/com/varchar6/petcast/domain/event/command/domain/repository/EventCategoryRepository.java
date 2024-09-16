package com.varchar6.petcast.domain.event.command.domain.repository;

import com.varchar6.petcast.domain.event.command.domain.aggregate.entity.EventCategory;
import com.varchar6.petcast.domain.event.command.domain.aggregate.EventCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, EventCategoryId> {

}
