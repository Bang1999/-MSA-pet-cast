package com.varchar6.petcast.domain.company.command.application.controller.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class EnrollRequestVO {
    private String name;
    private String address;
    private Integer employeeNumber;
    private Integer career;
    private String license;
    private String introduction;
    private String contactableTime;
}
