package com.varchar6.petcast.domain.request.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Generated
@ToString
@Builder
public class MemberAndRequestDTO {
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("requests")
    private List<RequestDTO> requests;
}
