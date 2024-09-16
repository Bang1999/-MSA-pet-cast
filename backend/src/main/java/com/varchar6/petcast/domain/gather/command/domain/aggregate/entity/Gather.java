package com.varchar6.petcast.domain.gather.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;

@Entity
@Table(name = "tbl_gather")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Gather {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "url")
    private String url;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "active", nullable = false)
    private boolean active = true;
}
