package com.varchar6.petcast.domain.event.command.application.dto.request;

import com.varchar6.petcast.domain.event.command.domain.aggregate.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventSetStatusRequestDTO {
    private int id;
    private EventStatusEnum status;

}
