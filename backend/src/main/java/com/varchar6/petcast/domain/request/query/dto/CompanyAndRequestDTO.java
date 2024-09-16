package com.varchar6.petcast.domain.request.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Generated
@ToString
@Builder
public class CompanyAndRequestDTO {
    @JsonProperty("company")
    private String company;

    @JsonProperty("requests")
    private List<RequestDTO> requests;
}
