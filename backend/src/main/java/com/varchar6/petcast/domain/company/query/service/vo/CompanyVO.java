package com.varchar6.petcast.domain.company.query.service.vo;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompanyVO {
    private int id;
    private String name;
    private String address;
    private int employeeNumber;
    private int career;
    private String license;
    private String introduction;
    private String contactableTime;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private boolean approved;
    private int memberId;
}
