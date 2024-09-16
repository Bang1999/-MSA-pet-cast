package com.varchar6.petcast.domain.event.command.domain.aggregate.entity;

import com.varchar6.petcast.domain.event.command.domain.aggregate.EventCategoryId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_event_category")
@IdClass(EventCategoryId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCategory {

    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @Id
    @Column(name = "event_id")
    private Integer eventId;

}
