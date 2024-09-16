package com.varchar6.petcast.domain.company.command.domain.aggregate;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
@ToString
@Table(name = "tbl_company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "employee_number")
    private int employeeNumber;

    @Column(name = "career")
    private int career;

    @Column(name = "license")
    private String license;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "contactable_time")
    private String contactableTime;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "active")
    private boolean active;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "member_id")
    private int memberId;
}
