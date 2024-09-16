package com.varchar6.petcast.domain.request.command.application.dto.request;

import com.varchar6.petcast.domain.request.command.domain.aggregate.RequestsStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRequestsRequestDTO {
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;
    private RequestsStatus status;
    private int companyId;
}
