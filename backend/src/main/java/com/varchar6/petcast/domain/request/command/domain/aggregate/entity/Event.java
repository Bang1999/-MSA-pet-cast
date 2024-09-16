package com.varchar6.petcast.domain.request.command.domain.aggregate.entity;

import com.varchar6.petcast.domain.request.command.domain.aggregate.EventsStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "requestEvent")
@Table(name = "tbl_event")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventsStatus status = EventsStatus.READY;

    @Column(name = "company_id", nullable = false)
    private int companyId;

    @Column(name = "member_id", nullable = false)
    private int memberId;
}
