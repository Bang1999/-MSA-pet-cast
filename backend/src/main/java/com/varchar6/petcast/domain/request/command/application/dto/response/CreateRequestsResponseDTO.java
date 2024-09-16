package com.varchar6.petcast.domain.request.command.application.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRequestsResponseDTO {
    private int id;
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;


}
