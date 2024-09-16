package com.varchar6.petcast.domain.event.command.application.dto.request;

import com.varchar6.petcast.domain.event.command.domain.aggregate.EventStatusEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateRequestDTO {
    private String title;
    private String content;
    private EventStatusEnum status;
    private String image;
    private Integer companyId;
    private Integer memberId;
    private List<Integer> categoryIds;

}
