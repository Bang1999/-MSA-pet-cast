package com.varchar6.petcast.domain.report.command.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportCreateRequestDTO {
    private String reason;
    private Integer reporterId;
    private Integer respondentId;
}
