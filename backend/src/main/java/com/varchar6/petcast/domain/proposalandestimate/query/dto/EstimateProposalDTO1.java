package com.varchar6.petcast.domain.proposalandestimate.query.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class EstimateProposalDTO1 {
    private int id;
    private int expectedCost;
    private EstimateStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;

    private List<ProposalDTO> proposal;
}
