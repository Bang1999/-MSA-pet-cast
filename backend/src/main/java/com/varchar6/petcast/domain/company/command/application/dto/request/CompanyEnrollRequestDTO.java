package com.varchar6.petcast.domain.company.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEnrollRequestDTO {
    private String name;
    private String address;
    private Integer employeeNumber;
    private Integer career;
    private String license;
    private String introduction;
    private String contactableTime;
    private int memberId;
}
