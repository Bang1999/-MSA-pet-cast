package com.varchar6.petcast.domain.report.query.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private int id;
    private String reason;
    private String createdAt;
    private int reporterId;
    private int respondentId;
}
