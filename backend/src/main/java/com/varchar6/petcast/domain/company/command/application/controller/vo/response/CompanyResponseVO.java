package com.varchar6.petcast.domain.company.command.application.controller.vo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompanyResponseVO {
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
}
