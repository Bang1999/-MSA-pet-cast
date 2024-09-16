package com.varchar6.petcast.domain.company.command.application.dto.response;

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class CompanyResponseDTO {
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
