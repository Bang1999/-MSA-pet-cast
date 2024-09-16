package com.varchar6.petcast.domain.proposalandestimate.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class EstimateDTO {
    private int id;
    private int expectedCost;
    private EstimateStatus status;
    private String createdAt;
    private String updatedAt;
    private Boolean active;
    private int companyId;
    private int proposalId;
}
