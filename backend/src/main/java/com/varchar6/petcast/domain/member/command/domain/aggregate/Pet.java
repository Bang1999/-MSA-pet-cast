package com.varchar6.petcast.domain.member.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "tbl_pet")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "introduction")
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender = Gender.OTHERS;

    @Column(name = "image")
    private String image;

    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "created_at",nullable = false)
    private String createdAt;

    @Column(name = "updated_at",nullable = false)
    private String updatedAt;

    @Column(name = "active",nullable = false)
    private boolean active = true;

    @Column(name="member_id", unique = false)
    private int memberId;

}
