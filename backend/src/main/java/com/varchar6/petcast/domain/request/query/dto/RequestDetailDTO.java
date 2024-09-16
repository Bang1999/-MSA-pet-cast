package com.varchar6.petcast.domain.request.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Generated
@ToString
@Builder
public class RequestDetailDTO {
    @JsonProperty("id")
    private int id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("hopeCost")
    private int hopeCost;

    @JsonProperty("hopeLocation")
    private String hopeLocation;

    @JsonProperty("hopeTime")
    private String hopeTime;

    @JsonProperty("status")
    private RequestStatus status;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("companyId")
    private int companyId;

    @JsonProperty("memberId")
    private int memberId;
}
