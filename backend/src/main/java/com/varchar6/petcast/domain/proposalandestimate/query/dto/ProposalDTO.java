package com.varchar6.petcast.domain.proposalandestimate.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ProposalDTO {
    private int id;
    private String content;
    private String hopeLocation;
    private String hopeTime;
    private int hopeCost;
    private ProposalStatus status;
    private String updatedAt;
    private String createdAt;
    private boolean active;
    private int memberId;
}
